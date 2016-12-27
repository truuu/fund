package fund.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fund.BaseController;
import fund.dto.Code;
import fund.mapper.*;
import fund.service.CodeService;

@Controller
public class CodeController extends BaseController{

	@Autowired CodeMapper codeMapper;
	@Autowired CodeService codeService;

	@Secured("ROLE_true")
	@RequestMapping("/code/codeList.do")
	public String codeList(Model model,@RequestParam("CodeGroupID") int CodeGroupID) {

		model.addAttribute("list", codeMapper.selectByCodeGroupID(CodeGroupID));
		model.addAttribute("name",codeMapper.selectByName(CodeGroupID));
		model.addAttribute("CodeGroupID",CodeGroupID);

		return "code/codeList";
	}

	@Secured("ROLE_true")
	@RequestMapping(value="/code/create.do", method=RequestMethod.GET)
	public String create(Model model,@RequestParam("CodeGroupID") int CodeGroupID) {
		model.addAttribute("name",codeMapper.selectByName(CodeGroupID));
		model.addAttribute("CodeGroupID",CodeGroupID);
		return "code/create";
	}

	@Secured("ROLE_true")
	@RequestMapping(value="/code/create.do", method=RequestMethod.POST)   
	public String create(Code code, @RequestParam("CodeGroupID") int CodeGroupID, RedirectAttributes redirectAttributes) {
		String message = codeService.validate(code);
		if(message==null){
			codeMapper.insert(code);
			return "redirect:/code/codeList.do?CodeGroupID="+CodeGroupID;
		}

		else
			redirectAttributes.addFlashAttribute("error", message);  // codeName blank
       
        return "redirect:/code/create.do?CodeGroupID="+CodeGroupID;
	}

	@Secured("ROLE_true")
	@RequestMapping(value="/code/edit.do", method=RequestMethod.GET)
	public String edit(Model model, @RequestParam("ID") int ID) {
		Code code = codeMapper.selectByID(ID);
		model.addAttribute("code", code);
		model.addAttribute("name",codeMapper.selectByName(code.getCodeGroupID()));
		return "code/edit";
	}
	
	@Secured("ROLE_true")
	@RequestMapping(value="/code/edit.do", method=RequestMethod.POST)
	public String edit(Model model, Code code, @RequestParam("CodeGroupID") int CodeGroupID, RedirectAttributes redirectAttributes)
			throws UnsupportedEncodingException {
		
		String message = codeService.validate(code);
		if(message==null){
			codeMapper.update(code);
			return "redirect:/code/codeList.do?CodeGroupID="+CodeGroupID;
		}
		else
			redirectAttributes.addFlashAttribute("error", message);  // codeName blank
       
        return "redirect:/code/edit.do?ID="+code.getID();
	}
	
	@Secured("ROLE_true")
	@RequestMapping("/code/delete.do")
	public String delete(Model model, @RequestParam("ID") int ID) {
		Code code = codeMapper.selectByID(ID);
		codeMapper.delete(ID);
		return "redirect:/code/codeList.do?CodeGroupID="+code.getCodeGroupID() ;
	}

}
