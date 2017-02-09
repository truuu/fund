package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fund.dto.Certificate;
import fund.dto.pagination.Pagination;
import fund.mapper.CertificateMapper;

@Controller
public class CertificationController extends BaseController {

    @Autowired CertificateMapper certificateMapper;

    @RequestMapping("/certificate/{type}/list.do")
    public String list(Model model, @PathVariable("type") int type, Pagination pagination) {
        pagination.setOd(type);
        pagination.setRecordCount(certificateMapper.selectCount(pagination));
        model.addAttribute("list", certificateMapper.selectPage(pagination));
        return "certificate/list" + type;
    }

    @RequestMapping("/certificate/{type}/detail.do")
    public String edit(Model model, @PathVariable("type") int type, @RequestParam("id") int id, Pagination pagination) {
        model.addAttribute("certificate", certificateMapper.selectById(id));
        return "certificate/detail" + type;
    }

    @RequestMapping("/certificate/{type}/delete.do")
    public String delete(RedirectAttributes ra, @PathVariable("type") int type, @RequestParam("id") int id, Pagination pagination) {
        // TODO: 현재 사용자 확인
        certificateMapper.delete(id);
        ra.addFlashAttribute("successMsg", "삭제했습니다.");
        return "redirect:list.do?" + pagination.getQueryString();
    }

    @RequestMapping(value = "/certificate/{type}/create.do", method = RequestMethod.GET)
    public String insert(Model model, @PathVariable("type") int type, Pagination pagination) {
        Certificate certificate = new Certificate();
        certificate.setCertificateNo(certificateMapper.generateCertificateNo());
        certificate.setUserName("현재사용자"); // TODO: 현재 사용자
        model.addAttribute("certificate", certificate);
        return "certificate/edit" + type;
    }

    @RequestMapping(value = "/certificate/{type}/create.do", method = RequestMethod.POST)
    public String insert(@PathVariable("type") int type, Certificate certificate, Pagination pagination) {
        certificate.setType(type);
        certificate.setCertificateNo(certificateMapper.generateCertificateNo());
        certificate.setUserId(1); // TODO: 현재 사용자
        certificateMapper.insert(certificate);
        return "redirect:detail.do?id=" + certificate.getId() + "&" + pagination.getQueryString();
    }

}
