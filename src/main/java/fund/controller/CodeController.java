package fund.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fund.BaseController;
import fund.dto.Code;
import fund.mapper.CodeMapper;
import fund.service.C;
import fund.service.CodeService;

@Controller
public class CodeController extends BaseController{

	@Autowired CodeMapper codeMapper;
	@Autowired CodeService codeService;

    @RequestMapping(value = "/code/churchList.do", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody List<Code> churchList(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        return codeMapper.selectByCodeGroupId(C.코드그룹ID_소속교회);
    }

	@RequestMapping("/code/codeList.do")
	public String codeList(Model model,@RequestParam("CodeGroupID") int codeGroupId) {

		model.addAttribute("list", codeMapper.selectByCodeGroupId(codeGroupId));
		model.addAttribute("name", codeMapper.selectCodeGroupById(codeGroupId).getName());
		model.addAttribute("CodeGroupID",codeGroupId);

		return "code/codeList";
	}

	@RequestMapping(value="/code/create.do", method=RequestMethod.GET)
	public String create(Model model,@RequestParam("CodeGroupID") int codeGroupId) {
		model.addAttribute("name",codeMapper.selectCodeGroupById(codeGroupId).getName());
		model.addAttribute("CodeGroupID",codeGroupId);
		return "code/create";
	}

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

	@RequestMapping(value="/code/edit.do", method=RequestMethod.GET)
	public String edit(Model model, @RequestParam("ID") int ID) {
		Code code = codeMapper.selectById(ID);
		model.addAttribute("code", code);
		model.addAttribute("name",codeMapper.selectCodeGroupById(code.getCodeGroupId()).getName());
		return "code/edit";
	}

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

        return "redirect:/code/edit.do?ID="+code.getId();
	}

	@RequestMapping("/code/delete.do")
	public String delete(Model model, @RequestParam("ID") int ID) {
		Code code = codeMapper.selectById(ID);
		codeMapper.delete(ID);
		return "redirect:/code/codeList.do?CodeGroupID="+code.getCodeGroupId() ;
	}

}
