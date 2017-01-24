package fund.controller;

import java.io.UnsupportedEncodingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.BaseController;
import fund.dto.DonationPurpose;
import fund.mapper.CodeMapper;
import fund.mapper.CorporateMapper;
import fund.mapper.DonationPurposeMapper;
import fund.service.CodeService;

@Controller
public class DonationPurposeController extends BaseController{

	@Autowired DonationPurposeMapper donationPurposeMapper;
	@Autowired CorporateMapper corporateMapper;
	@Autowired CodeMapper codeMapper;
	@Autowired CodeService codeService;

	/*donationPurpose List*/
	@Secured("ROLE_true")
	@RequestMapping("/code/donationPurposeList.do")
	public String codeList(Model model) {
		model.addAttribute("list",donationPurposeMapper.selectAll());
		return "code/donationPurposeList";
	}

	/*donationPurpose insert*/
	@Secured("ROLE_true")
	@RequestMapping(value="/code/donationPurposeCreate.do", method=RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("corporateList",corporateMapper.selectCorporate());
		model.addAttribute("organizationList",codeMapper.selectByCodeGroupID(7));
		return "code/donationPurposeCreate";
	}

	@Secured("ROLE_true")
	@RequestMapping(value="/code/donationPurposeCreate.do", method=RequestMethod.POST)
	public String create(@Valid DonationPurpose donationPurpose, BindingResult result,Model model) {
		if(codeService.validate(donationPurpose)==true)
		{
			donationPurposeMapper.insert(donationPurpose);

		}
		else if(result.hasErrors()) {

			model.addAttribute("corporateList",corporateMapper.selectCorporate());
			model.addAttribute("organizationList",codeMapper.selectByCodeGroupID(7));

			return "code/donationPurposeCreate";

		}

		return "redirect:/code/donationPurposeList.do";
	}

	/*donationPurpose edit*/
	@Secured("ROLE_true")
	@RequestMapping(value="/code/donationPurposeEdit.do", method=RequestMethod.GET)
	public String edit(Model model,@RequestParam("ID") int ID) {
		DonationPurpose donationPurpose = donationPurposeMapper.selectByID(ID);
		model.addAttribute("corporateList",corporateMapper.selectCorporate());
		model.addAttribute("organizationList",codeMapper.selectByCodeGroupID(7));
		model.addAttribute("donationPurpose",donationPurpose);

		return "code/donationPurposeEdit";
	}

	@Secured("ROLE_true")
	@RequestMapping(value="/code/donationPurposeEdit.do", method=RequestMethod.POST)
	public String edit(@Valid DonationPurpose donationPurpose,BindingResult result,Model model)
			throws UnsupportedEncodingException {

		if(codeService.validate(donationPurpose)==true)
		{
			donationPurposeMapper.update(donationPurpose);

		}
		else if(result.hasErrors()) {

			model.addAttribute("corporateList",corporateMapper.selectCorporate());
			model.addAttribute("organizationList",codeMapper.selectByCodeGroupID(7));

			return "code/donationPurposeEdit";

		}

		return "redirect:/code/donationPurposeList.do";
	}

	/*donationPurpose delete*/
	@Secured("ROLE_true")
	@RequestMapping("/code/donationPurposeDelete.do")
	public String delete(Model model, @RequestParam("ID") int ID) {
		donationPurposeMapper.delete(ID);
		return "redirect:/code/donationPurposeList.do";
	}


}
