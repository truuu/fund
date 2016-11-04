package fund.controller;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;

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
import fund.mapper.*;

@Controller
public class CodeController extends BaseController{

	@Autowired CodeMapper codeMapper;

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
	@RequestMapping(value="/code/create.do", method=RequestMethod.POST)   // spring form validation (code insert)
	public String create(@Valid Code code, @RequestParam("CodeGroupID") int CodeGroupID, BindingResult bindingResult,Model model) {

		System.out.println("넘어오나요");
		
		if (bindingResult.hasErrors()) {
			System.out.println("ssss");
			return "code/create";
		}

		codeMapper.insert(code);
		return "redirect:/code/codeList.do?CodeGroupID="+CodeGroupID;
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
	public String edit(Model model, Code code, @RequestParam("CodeGroupID") int CodeGroupID)
			throws UnsupportedEncodingException {
		codeMapper.update(code);
		return "redirect:/code/codeList.do?CodeGroupID="+CodeGroupID;
	}
	
	@Secured("ROLE_true")
	@RequestMapping("/code/delete.do")
	public String delete(Model model, @RequestParam("ID") int ID) {
		Code code = codeMapper.selectByID(ID);
		codeMapper.delete(ID);
		return "redirect:/code/codeList.do?CodeGroupID="+code.getCodeGroupID() ;
	}

}
