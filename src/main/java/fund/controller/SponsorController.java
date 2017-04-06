package fund.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import fund.dto.Code;
import fund.dto.FileAttach;
import fund.dto.Sponsor;
import fund.dto.pagination.Pagination;
import fund.dto.pagination.PaginationSponsor;
import fund.mapper.CodeMapper;
import fund.mapper.FileAttachMapper;
import fund.mapper.SponsorMapper;
import fund.service.C;
import fund.service.LogService;
import fund.service.ReportBuilder;

@Controller
public class SponsorController extends BaseController {

    @Autowired SponsorMapper sponsorMapper;
    @Autowired CodeMapper codeMapper;
    @Autowired FileAttachMapper fileAttachMapper;
    @Autowired LogService logService;

    @RequestMapping("/sponsor/list.do")
    public String list(Model model, @ModelAttribute("pagination") PaginationSponsor pagination) {
        pagination.setRecordCount(sponsorMapper.selectCount(pagination));
        List<Sponsor> list = sponsorMapper.selectPage(pagination);
        List<Code> sponsorType1Codes = codeMapper.selectByCodeGroupId(1);
        List<Code> sponsorType2Codes = codeMapper.selectByCodeGroupId(2);
        model.addAttribute("list", list);
        model.addAttribute("sponsorType1Codes", sponsorType1Codes);
        model.addAttribute("sponsorType2Codes", sponsorType2Codes);
        return "sponsor/list";
    }

    @RequestMapping(value="/sponsor/edit.do", method=RequestMethod.GET)
    public String edit(@RequestParam("id") int id, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        model.addAttribute("sponsor", sponsorMapper.selectById(id));
        addCodesToModel(id, model);
        return "sponsor/edit";
    }

    private void addCodesToModel(int sponsorId, Model model) {
        model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분1));
        model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분2));
        model.addAttribute("churchList", codeMapper.selectByCodeGroupId(C.코드그룹ID_소속교회));
        model.addAttribute("files", fileAttachMapper.selectBySponsorId(sponsorId));
    }

    @RequestMapping(value="/sponsor/edit.do", method=RequestMethod.POST, params="cmd=save")
    public String edit(Sponsor sponsor, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        try {
            sponsorMapper.update(sponsor);
            model.addAttribute("successMsg", "저장했습니다.");
            return edit(sponsor.getId(), pagination, model);
        } catch (Exception e) {
            addCodesToModel(sponsor.getId(), model);
            return logService.logErrorAndReturn(model, e, "sponsor/edit");
        }
    }

    @RequestMapping(value="/sponsor/edit.do", method=RequestMethod.POST, params="cmd=delete")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String delete(@RequestParam("id") int id, @ModelAttribute("pagination") PaginationSponsor pagination) throws Exception {
        fileAttachMapper.deleteBySponsorId(id);
        sponsorMapper.delete(id);
        return "redirect:list.do?" + pagination.getQueryString();
    }

    @RequestMapping(value="/sponsor/create.do", method=RequestMethod.GET)
    public String create(@ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorNo(sponsorMapper.generateSponsorNo());
        model.addAttribute("sponsor", sponsor);
        addCodesToModel(0, model);
        return "sponsor/edit";
    }

    @RequestMapping(value="/sponsor/create.do", method=RequestMethod.POST, params="cmd=save")
    public String create(Sponsor sponsor, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        try {
            sponsor.setSponsorNo(sponsorMapper.generateSponsorNo());
            sponsorMapper.insert(sponsor);
            return "redirect:list.do";
        } catch (Exception e) {
            addCodesToModel(0, model);
            return logService.logErrorAndReturn(model, e, "sponsor/edit");
        }
    }

    @RequestMapping("/sponsor/excel.do")
    public void excel(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Sponsor> list = sponsorMapper.selectAll();
        String fname = "후원인목록.xlsx";
        ReportBuilder reportBuilder = new ReportBuilder("sponsorList", fname, req, res);
        reportBuilder.setCollection(list);
        reportBuilder.build("xlsx");
    }

    @RequestMapping(value="/sponsor/fileUp.do", method=RequestMethod.POST)
    public String fileUp(@RequestParam("id") int id, @ModelAttribute("pagination") PaginationSponsor pagination,
            @RequestParam("file") MultipartFile uploadedFile) throws IOException {
        if (uploadedFile.getSize() > 0 ) {
            FileAttach fileAttach = new FileAttach();
            fileAttach.setSponsorId(id);
            fileAttach.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
            fileAttach.setFileSize((int)uploadedFile.getSize());
            fileAttach.setData(uploadedFile.getBytes());
            fileAttachMapper.insert(fileAttach);
        }
        return "redirect:edit.do?id=" + id + "&" + pagination.getQueryString();
    }

    @RequestMapping("/sponsor/fileDown.do")
    public void fileDown(@RequestParam("id") int id, HttpServletResponse response) throws IOException {
        FileAttach file = fileAttachMapper.selectById(id);
       if (file == null) return;
        String fileName = URLEncoder.encode(file.getFileName(),"UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
        try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
            output.write(file.getData());
        }
    }

    @RequestMapping("/sponsor/fileDel.do")
    public String fileDel(Model model, @RequestParam("id") int id, @RequestParam("sid") int sid, @ModelAttribute("pagination") PaginationSponsor pagination) {
        fileAttachMapper.delete(id);
        return "redirect:edit.do?id=" + sid + "&" + pagination.getQueryString();
    }

    @RequestMapping("/sponsor/dm.do")
    public String sendDM(Model model, Pagination pagination) {
        if (StringUtils.isBlank(pagination.getStartDate()) == false) {
            pagination.setRecordCount(sponsorMapper.selectCountForDM(pagination));
            model.addAttribute("list", sponsorMapper.selectForDM(pagination));
        }
        return "sponsor/dm";
    }

    @RequestMapping("/sponsor/dmx.do")
    public void sendDMxlsx(Pagination pagination, HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (StringUtils.isBlank(pagination.getStartDate()) == false) {
            int count = sponsorMapper.selectCountForDM(pagination);
            pagination.setRecordCount(count);
            pagination.setPageSize(count);
            List<Sponsor> list = sponsorMapper.selectForDM(pagination);
            String fname = "우편발송_" + pagination.getStartDate() + "_" + pagination.getEndDate() + ".xlsx";
            ReportBuilder reportBuilder = new ReportBuilder("sendDM", fname, req, res);
            reportBuilder.setCollection(list);
            reportBuilder.build("xlsx");
        } else
            res.sendRedirect("dm.do");
    }



}
