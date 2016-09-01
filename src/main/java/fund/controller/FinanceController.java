package fund.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FinanceController {
	@RequestMapping(value="/finance/automation.do", method=RequestMethod.GET)
	public String automation(Model model) {
		return "finance/automation";
	}

	@RequestMapping(value="/finance/eb13.do", method=RequestMethod.GET)
	public String eb13(Model model) {
		return "finance/eb13";
	}
	
	@RequestMapping(value="/finance/eb14.do", method=RequestMethod.GET)
	public String eb14(Model model) {
		return "finance/eb14";
	}
	
	@RequestMapping(value="/finance/eb21.do", method=RequestMethod.GET)
	public String eb21(Model model) {
		return "finance/eb21";
	}
	
	@RequestMapping(value="/finance/eb22.do", method=RequestMethod.GET)
	public String eb22(Model model) {
		return "finance/eb22";
	}

	@RequestMapping(value="/finance/salary.do", method=RequestMethod.GET)
	public String salary(Model model) {
		return "finance/salary";
	}
}
