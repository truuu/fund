package fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import fund.mapper.CodeMapper;

public class BaseController {
	@Autowired
	CodeMapper codeMapper;

	@ModelAttribute
	public void addCodeGroupList(Model model) {
		model.addAttribute("codeGroupList", codeMapper.selectCodeGroup());
	}

}
