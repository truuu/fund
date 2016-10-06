package fund.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fund.BaseController;
import fund.dto.Code;
import fund.dto.Pagination;
import fund.mapper.*;

@Controller
public class CodeController extends BaseController{
	@Autowired CodeMapper codeMapper;
	

	/*기초정보관리 LIST*/
	   @RequestMapping("/code/codeList.do")
	   public String codeList(Model model,@RequestParam("CodeGroupID") int CodeGroupID) {
	      //HashMap<String, Object> map = new HashMap<String, Object>();
	      //map.put("CodeGroupID", CodeGroupID);
	      //map.put("pagination",pagination);
	      //pagination.setRecordCount(codeMapper.selectCount(map));
	      model.addAttribute("list", codeMapper.selectByCodeGroupID(CodeGroupID));
	      model.addAttribute("name",codeMapper.selectByName(CodeGroupID));
	      model.addAttribute("CodeGroupID",CodeGroupID);

	      return "code/codeList";
	   }

	/*기초정보관리추가*/
	@RequestMapping(value="/code/create.do", method=RequestMethod.GET)
	public String create(Model model,@RequestParam("CodeGroupID") int CodeGroupID) {
		model.addAttribute("name",codeMapper.selectByName(CodeGroupID));
		model.addAttribute("CodeGroupID",CodeGroupID);
		return "code/create";
	}


	@RequestMapping(value="/code/create.do", method=RequestMethod.POST)
	public String create(Model model,Code code,@RequestParam("CodeGroupID") int CodeGroupID) {
		codeMapper.insert(code);
		return "redirect:/code/codeList.do?CodeGroupID="+CodeGroupID;
	}
	
		
    @RequestMapping(value="/code/edit.do", method=RequestMethod.GET)
    public String edit(Model model, @RequestParam("ID") int ID) {
    	Code code = codeMapper.selectByID(ID);
        model.addAttribute("code", code);
        model.addAttribute("name",codeMapper.selectByName(code.getCodeGroupID()));
        return "code/edit";
    }

    @RequestMapping(value="/code/edit.do", method=RequestMethod.POST)
    public String edit(Model model, Code code, @RequestParam("CodeGroupID") int CodeGroupID)
            throws UnsupportedEncodingException {
        codeMapper.update(code);
        return "redirect:/code/codeList.do?CodeGroupID="+CodeGroupID;
    }
    
    @RequestMapping("/code/delete.do")
    public String delete(Model model, @RequestParam("ID") int ID) {
    	Code code = codeMapper.selectByID(ID);
        codeMapper.delete(ID);
        return "redirect:/code/codeList.do?CodeGroupID="+code.getCodeGroupID() ;
    }
	
}
