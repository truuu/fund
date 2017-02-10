package fund.controller;

import java.io.IOException;
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
import fund.dto.Code;
import fund.dto.Sponsor;
import fund.dto.pagination.Pagination;
import fund.dto.pagination.PaginationSponsor;
import fund.mapper.CodeMapper;
import fund.mapper.SponsorMapper;
import fund.service.C;
import fund.service.ReportBuilder4;
import net.sf.jasperreports.engine.JRException;

@Controller
public class SponsorController extends BaseController {

    @Autowired SponsorMapper sponsorMapper;
    @Autowired CodeMapper codeMapper;

    // 후원인 목록
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

    // 후원인 수정
    @RequestMapping(value="/sponsor/edit.do", method=RequestMethod.GET)
    public String edit(@RequestParam("id") int id, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        model.addAttribute("sponsor", sponsorMapper.selectById(id));
        model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분1));
        model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분2));
        model.addAttribute("churchList", codeMapper.selectByCodeGroupId(C.코드그룹ID_소속교회));
        return "sponsor/edit";
    }

    // 후원인 저장
    @RequestMapping(value="/sponsor/edit.do", method=RequestMethod.POST, params="cmd=save")
    public String edit(Sponsor sponsor, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        sponsorMapper.update(sponsor);
        model.addAttribute("successMsg", "저장했습니다.");
        return edit(sponsor.getId(), pagination, model);
    }

    // 후원인 삭제
    @RequestMapping(value="/sponsor/edit.do", method=RequestMethod.POST, params="cmd=delete")
    public String delete(@RequestParam("id") int id, @ModelAttribute("pagination") PaginationSponsor pagination) throws Exception {
        sponsorMapper.delete(id);
        return "redirect:list.do?" + pagination.getQueryString();
    }

    // 후원인 생성
    @RequestMapping(value="/sponsor/create.do", method=RequestMethod.GET)
    public String create(@ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorNo(sponsorMapper.generateSponsorNo());
        model.addAttribute("sponsor", sponsor);
        model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분1));
        model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분2));
        model.addAttribute("churchList", codeMapper.selectByCodeGroupId(C.코드그룹ID_소속교회));
        return "sponsor/create";
    }

    // 후원인 저장
    @RequestMapping(value="/sponsor/create.do", method=RequestMethod.POST, params="cmd=save")
    public String create(Sponsor sponsor, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) throws Exception {
        sponsor.setSponsorNo(sponsorMapper.generateSponsorNo());
        sponsorMapper.insert(sponsor);
        return "redirect:list.do";
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
    public void sendDMxlsx(Pagination pagination, HttpServletRequest req, HttpServletResponse res) throws JRException, IOException {
        if (StringUtils.isBlank(pagination.getStartDate()) == false) {
            int count = sponsorMapper.selectCountForDM(pagination);
            pagination.setRecordCount(count);
            pagination.setPageSize(count);
            List<Sponsor> list = sponsorMapper.selectForDM(pagination);
            String fname = "sendDM_" + pagination.getStartDate() + "_" + pagination.getEndDate() + ".xlsx";
            ReportBuilder4 reportBuilder = new ReportBuilder4("sendDM", fname, req, res);
            reportBuilder.setCollection(list);
            reportBuilder.build("xlsx");
        } else
            res.sendRedirect("dm.do");
    }

}
