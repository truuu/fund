package fund.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fund.BaseController;
import fund.dto.Corporate;
import fund.dto.DonationPurpose;
import fund.dto.Pagination;
import fund.mapper.CodeMapper;
import fund.mapper.CorporateMapper;
import fund.mapper.DonationPurposeMapper;

@Controller
public class DonationPurposeController extends BaseController{

	@Autowired DonationPurposeMapper donationPurposeMapper;
	@Autowired CorporateMapper corporateMapper;
	@Autowired CodeMapper codeMapper;
	
	/*donationPurpose List*/
	@RequestMapping("/code/donationPurposeList.do")
	public String codeList(Model model,Pagination pagination) {
		pagination.setRecordCount(donationPurposeMapper.selectCount());
		model.addAttribute("list",donationPurposeMapper.selectPage(pagination));
		return "code/donationPurposeList";
	}
	
	/*donationPurpose insert*/
	@RequestMapping(value="/code/donationPurposeCreate.do", method=RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("corporateList",corporateMapper.selectCorporate());
		model.addAttribute("organizationList",codeMapper.selectByCodeGroupName("기관종류"));
		return "code/donationPurposeCreate";
	}

	@RequestMapping(value="/code/donationPurposeCreate.do", method=RequestMethod.POST)
	public String create(Model model, DonationPurpose donationPurpose) {
		donationPurposeMapper.insert(donationPurpose);
		return "redirect:/code/donationPurposeList.do";
	}
	
	/*donationPurpose edit*/
	@RequestMapping(value="/code/donationPurposeEdit.do", method=RequestMethod.GET)
	public String edit(Model model,@RequestParam("ID") int ID) {
		DonationPurpose donationPurpose = donationPurposeMapper.selectByID(ID);
		model.addAttribute("corporateList",corporateMapper.selectCorporate());
		model.addAttribute("organizationList",codeMapper.selectByCodeGroupName("기관종류"));
        model.addAttribute("donationPurpose",donationPurpose);
  
		return "code/donationPurposeEdit";
	}
	
	@RequestMapping(value="/code/donationPurposeEdit.do", method=RequestMethod.POST)
    public String edit(Model model, DonationPurpose donationPurpose)
            throws UnsupportedEncodingException {
		donationPurposeMapper.update(donationPurpose);
        return "redirect:/code/donationPurposeList.do";
    }
    
	/*donationPurpose delete*/
    @RequestMapping("/code/donationPurposeDelete.do")
    public String delete(Model model, @RequestParam("ID") int ID) {
    	donationPurposeMapper.delete(ID);
        return "redirect:/code/donationPurposeList.do";
    }
    
    /*donationPurpose table*/
    @RequestMapping(value="/code/send.do", method=RequestMethod.GET)
    public String send(Model model) {
        model.addAttribute("donationPurposeList", donationPurposeMapper.selectDonationPurpose());
        return "code/send";
    }
}
