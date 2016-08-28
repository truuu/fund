package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fund.dto.Pagination;
import fund.mapper.CorporateMapper;

@Controller
public class CorporateController {

	@Autowired CorporateMapper corporateMapper;

	/*기관목록*/
	@RequestMapping("/code/corporateList.do")
	public String codeList(Model model,Pagination pagination) {
		pagination.setRecordCount(corporateMapper.selectCount());
		model.addAttribute("list",corporateMapper.selectPage(pagination));
		return "code/corporateList";
	}


}
