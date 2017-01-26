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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import fund.BaseController;
import fund.dto.Code;
import fund.dto.FileAttachment;
import fund.dto.Pagination;
import fund.dto.PaginationSponsor;
import fund.dto.Sponsor;
import fund.mapper.CodeMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.FileAttachmentMapper;
import fund.mapper.PaymentMapper;
import fund.mapper.SponsorMapper;
import fund.service.C;
import fund.service.CodeService;
import fund.service.FileExtFilter;
import fund.service.ReportBuilder;
import fund.service.SponsorService;
import net.sf.jasperreports.engine.JRException;

@Controller
public class SponsorController extends BaseController {

    @Autowired SponsorService sponsorService;
    @Autowired SponsorMapper sponsorMapper;
    @Autowired FileAttachmentMapper fileAttachmentMapper;
    @Autowired PaymentMapper paymentMapper;
    @Autowired CodeService codeService;
    @Autowired CodeMapper codeMapper;
    @Autowired DonationPurposeMapper donationPurposeMapper;
    @Autowired FileExtFilter fileExtFilter;

    // 후원인 목록
    @RequestMapping("/sponsor/list.do")
    public String list(Model model, @ModelAttribute("pagination") PaginationSponsor pagination) {
        List<Sponsor> list = sponsorService.selectPage(pagination);
        List<Code> sponsorType1Codes = codeMapper.selectByCodeGroupId(1);
        List<Code> sponsorType2Codes = codeMapper.selectByCodeGroupId(2);
        model.addAttribute("list", list);
        model.addAttribute("sponsorType1Codes", sponsorType1Codes);
        model.addAttribute("sponsorType2Codes", sponsorType2Codes);
        return "sponsor/list";
    }

    // 후원인 수정
    @RequestMapping(value="/sponsor/sponsorEdit.do", method=RequestMethod.GET)
    public String sponsorEdit(@RequestParam("id") int id, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        model.addAttribute("sponsor", sponsorService.selectById(id));
        model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분1));
        model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분2));
        model.addAttribute("files", fileAttachmentMapper.selectBySponosrId(id));
        model.addAttribute("churchList", codeMapper.selectByCodeGroupId(C.코드그룹ID_소속교회));
        return "sponsor/sponsorEdit";
    }

    // 후원인 저장
    @RequestMapping(value="/sponsor/sponsorEdit.do", method=RequestMethod.POST, params="cmd=save")
    public String sponsorEdit(Sponsor sponsor, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        sponsorService.update(sponsor);
        model.addAttribute("successMsg", "저장했습니다.");
        return sponsorEdit(sponsor.getId(), pagination, model);
    }

    // 후원인 삭제
    @RequestMapping(value="/sponsor/sponsorEdit.do", method=RequestMethod.POST, params="cmd=delete")
    public String sponsorDelete(@RequestParam("id") int id, @ModelAttribute("pagination") PaginationSponsor pagination) throws Exception {
        sponsorService.delete(id);
        return "redirect:list.do?" + pagination.getQueryString();
    }

    // 후원인 생성
    @RequestMapping(value="/sponsor/sponsorNew.do", method=RequestMethod.GET)
    public String sponsorNew(@ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorNo(sponsorMapper.generateSponsorNo());
        model.addAttribute("sponsor", sponsor);
        model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분1));
        model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분2));
        model.addAttribute("churchList", codeMapper.selectByCodeGroupId(C.코드그룹ID_소속교회));
        return "sponsor/sponsorEdit";
    }

    // 후원인 저장
    @RequestMapping(value="/sponsor/sponsorNew.do", method=RequestMethod.POST, params="cmd=save")
    public String sponsorNew(Sponsor sponsor, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        sponsor.setSponsorNo(sponsorMapper.generateSponsorNo());
        sponsorService.insert(sponsor);
        return "redirect:list.do";
    }

    // 주민등록번호 암호화 되지 않은 후원인 암호화
    @RequestMapping("/sponsor/encryptNo.do")
    public String encryptNo(Model model) throws Exception {
        model.addAttribute("list", sponsorService.encryptJuminNo());
        return "sponsor/encryptNo";
    }

    @RequestMapping("/sponsor/sendDM.do")
    public String sendDM(Model model, Pagination pagination) {
        if (StringUtils.isBlank(pagination.getStartDate()) == false) {
            pagination.setRecordCount(sponsorMapper.selectCountForDM(pagination));
            model.addAttribute("list", sponsorMapper.selectForDM(pagination));
        }
        return "sponsor/sendDM";
    }

    @RequestMapping("/sponsor/sendDMxlsx.do")
    public void sendDMxlsx(Pagination pagination, HttpServletRequest req, HttpServletResponse res) throws JRException, IOException {
        if (StringUtils.isBlank(pagination.getStartDate()) == false) {
            int count = sponsorMapper.selectCountForDM(pagination);
            pagination.setRecordCount(count);
            pagination.setPageSize(count);
            List<Sponsor> list = sponsorMapper.selectForDM(pagination);
            String fname = "sendDM_" + pagination.getStartDate() + "_" + pagination.getEndDate() + ".xlsx";
            ReportBuilder reportBuilder = new ReportBuilder("sendDM", list, fname, req, res);
            reportBuilder.build("xlsx");
        } else
            res.sendRedirect("sendDM.do");
    }











    // 파일업로드
    @RequestMapping(value = "/sponsor/upload.do", method = RequestMethod.POST)
    public String fileUpload(Model model, @RequestParam("sid") int sid,
            @RequestParam("file") MultipartFile uploadedFile) throws IOException {
        if (uploadedFile.getSize() > 0) {
            FileAttachment file = new FileAttachment();
            file.setSponsorID(sid); // 나중에 조인해서 변경해야함
            file.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
            file.setFilesize((int) uploadedFile.getSize());
            file.setData(uploadedFile.getBytes());
            fileAttachmentMapper.insert(file);
        }
        return "redirect:/sponsor/sponsorEdit.do?id=" + sid;
    }

    // 파일 다운로드
    @RequestMapping("/sponsor/download.do")
    public void download(@RequestParam("id") int id, HttpServletResponse response) throws IOException {
        FileAttachment file = fileAttachmentMapper.selectById(id);
        if (file == null)
            return;
        String fileName = URLEncoder.encode(file.getFileName(), "UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
        try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
            output.write(file.getData());
        }
    }

    // 파일삭제
    @RequestMapping(value = "sponsor/fileDelete.do", method = RequestMethod.GET)
    public String fileDelete(@RequestParam("id") int id, @RequestParam("sid") int sid) throws IOException {
        fileAttachmentMapper.deleteById(id);
        return "redirect:/sponsor/sponsorEdit.do?id=" + sid;
    }

    // - 후원인구분2별 출연내역 -
    @RequestMapping(value = "sponsor/cast.do", method = RequestMethod.GET)
    public String cast(Model model) throws IOException {
        int sum = 0;// 후원인구분2별 출연내역 금액
        model.addAttribute("sum", sum);
        return "sponsor/castHistory";
    }

    @RequestMapping(value = "sponsor/castList.do")
    public String castList(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
            Model model) throws IOException {
        List<Sponsor> list = sponsorMapper.castBySponsorType2(startDate, endDate);

        int sponsorCount = 0; // 후원인구분2별 출연내역 회원수
        int castCount = 0; // 후원인구분2별 출연내역 출연수
        int sum = 0;// 후원인구분2별 출연내역 금액

        double result; // 반올림되지 않은 % information
        double persent;// % information
        double totalPercent = 0.0; // total % information

        for (Sponsor i : list) {
            sponsorCount = sponsorCount + i.getSponsorCount();
            castCount = castCount + i.getCastCount();
            sum = sum + i.getSum();
        }
        for (int i = 0; i < list.size(); i++) {
            result = (double) list.get(i).getSum() / (double) sum * 100;
            persent = Double.parseDouble(String.format("%.2f", result));
            list.get(i).setPersent(persent);
            totalPercent += result;

        }
        model.addAttribute("totalPercent", totalPercent);
        model.addAttribute("sponsorCount", sponsorCount);
        model.addAttribute("castCount", castCount);
        model.addAttribute("sum", sum);
        model.addAttribute("list", list);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "sponsor/castHistory";
    }

    // 회원구분 별 보고서
    @RequestMapping(value = "/sponsor/castList.do", params = "cmd=pdf")
    public void sponsorTypeReport(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
            Pagination pagination, HttpServletRequest req, HttpServletResponse res) throws JRException, IOException {
        List<Sponsor> list = sponsorMapper.castBySponsorType2(startDate, endDate);
        ReportBuilder reportBuilder = new ReportBuilder("chartBySponsorType", list, "chartBySponsorType.pdf", req, res);
        reportBuilder.build("pdf");
    }

    // 회원구분 별 엑셀
    @RequestMapping(value = "/sponsor/castList.do", params = "cmd=xlsx")
    public void sponsorTypeXlsx(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
            Pagination pagination, HttpServletRequest req, HttpServletResponse res) throws JRException, IOException {
        List<Sponsor> list = sponsorMapper.castBySponsorType2(startDate, endDate);
        ReportBuilder reportBuilder = new ReportBuilder("chartBySponsorType", list, "chartBySponsorType.xlsx", req,
                res);
        reportBuilder.build("xlsx");
    }


    // 후원인목록
    @RequestMapping(value = "/sponsor/sponsor_m.do", params = "cmd=xlsx")
    public void sponsorList(Pagination pagination, HttpServletRequest req, HttpServletResponse res)
            throws JRException, IOException {
        List<Sponsor> list = sponsorMapper.sponsorListExcel(pagination);
        ReportBuilder reportBuilder = new ReportBuilder("sponsorList", list, "sponsorList.xlsx", req, res);
        reportBuilder.build("xlsx");
    }

}
