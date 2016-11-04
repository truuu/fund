package fund.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fund.BaseController;
import fund.dto.Code;
import fund.dto.Corporate;
import fund.dto.Pagination;
import fund.mapper.CorporateMapper;

@Controller
public class CorporateController extends BaseController{

	@Autowired CorporateMapper corporateMapper;

	/*Corporate list*/
	@Secured("ROLE_true")
	@RequestMapping("/code/corporateList.do")
	public String codeList(Model model,Pagination pagination) {
		pagination.setRecordCount(corporateMapper.selectCount());
		model.addAttribute("list",corporateMapper.selectPage(pagination));
		return "code/corporateList";
	}
	
	/*Corporate insert*/
	@Secured("ROLE_true")
	@RequestMapping(value="/code/corporateCreate.do", method=RequestMethod.GET)
	public String create(Model model) {
		return "code/corporateCreate";
	}

	@Secured("ROLE_true")
	@RequestMapping(value="/code/corporateCreate.do", method=RequestMethod.POST)
	public String create(Model model, Corporate corporate) {
		corporateMapper.insert(corporate);
		return "redirect:/code/corporateList.do";
	}
	
	/*Corporate edit*/
	@Secured("ROLE_true")
	@RequestMapping(value="/code/corporateEdit.do", method=RequestMethod.GET)
	public String edit(Model model,@RequestParam("ID") int ID) {
		Corporate corporate = corporateMapper.selectByID(ID);
        model.addAttribute("corporate",corporate);
		return "code/corporateEdit";
	}
	
	@Secured("ROLE_true")
	@RequestMapping(value="/code/corporateEdit.do", method=RequestMethod.POST)
    public String edit(Model model, Corporate corporate)
            throws UnsupportedEncodingException {
        corporateMapper.update(corporate);
        return "redirect:/code/corporateList.do";
    }
    
	@Secured("ROLE_true")
    @RequestMapping("/code/corporateDelete.do")
    public String delete(Model model, @RequestParam("ID") int ID) {
        corporateMapper.delete(ID);
        return "redirect:/code/corporateList.do";
    }

  

}
