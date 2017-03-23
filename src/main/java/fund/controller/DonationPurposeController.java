package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.DonationPurpose;
import fund.mapper.CodeMapper;
import fund.mapper.CorporateMapper;
import fund.mapper.DonationPurposeMapper;
import fund.service.C;
import fund.service.LogService;

@Controller
public class DonationPurposeController extends BaseController{

	@Autowired DonationPurposeMapper donationPurposeMapper;
	@Autowired CorporateMapper corporateMapper;
	@Autowired CodeMapper codeMapper;
    @Autowired LogService logService;

	@RequestMapping("/donationPurpose/list.do")
	public String list(Model model) {
		model.addAttribute("list", donationPurposeMapper.selectAll());
		return "donationPurpose/list";
	}

	@RequestMapping(value="/donationPurpose/create.do", method=RequestMethod.GET)
	public String create(Model model) {
	    model.addAttribute("donationPurpose", new DonationPurpose());
		model.addAttribute("corporateList", corporateMapper.selectAll());
		model.addAttribute("organizationList",codeMapper.selectByCodeGroupId(C.코드그룹ID_기관종류));
		return "donationPurpose/edit";
	}

	@RequestMapping(value="/donationPurpose/create.do", method=RequestMethod.POST)
	public String create(DonationPurpose donationPurpose, Model model) {
	    try {
    	    donationPurposeMapper.insert(donationPurpose);
    		return "redirect:/donationPurpose/list.do";
        } catch (Exception e) {
            return logService.logErrorAndReturn(model, e, "donationPurpose/edit");
        }
	}

	@RequestMapping(value="/donationPurpose/edit.do", method=RequestMethod.GET)
	public String edit(Model model,@RequestParam("id") int id) {
		model.addAttribute("corporateList", corporateMapper.selectAll());
		model.addAttribute("organizationList", codeMapper.selectByCodeGroupId(C.코드그룹ID_기관종류));
		model.addAttribute("donationPurpose", donationPurposeMapper.selectById(id));
		return "donationPurpose/edit";
	}

	@RequestMapping(value="/donationPurpose/edit.do", method=RequestMethod.POST, params="cmd=save")
	public String edit(DonationPurpose donationPurpose, Model model) {
	    try {
    	    donationPurposeMapper.update(donationPurpose);
    		return "redirect:/donationPurpose/list.do";
        } catch (Exception e) {
            return logService.logErrorAndReturn(model, e, "donationPurpose/edit");
        }
	}

    @RequestMapping(value="/donationPurpose/edit.do", method=RequestMethod.POST, params="cmd=delete")
	public String delete(Model model, @RequestParam("id") int id) {
		donationPurposeMapper.delete(id);
		return "redirect:/donationPurpose/list.do";
	}

}
