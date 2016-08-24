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

	@RequestMapping(value="/finance/cms.do", method=RequestMethod.GET)
	public String cms(Model model) {
		return "finance/cms";
	}

	@RequestMapping(value="/finance/salary.do", method=RequestMethod.GET)
	public String salary(Model model) {
		return "finance/salary";
	}
}
