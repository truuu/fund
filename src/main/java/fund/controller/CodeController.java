package fund.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fund.dto.Code;
import fund.dto.Pagination;
import fund.mapper.*;

@Controller
public class CodeController {
	@Autowired CodeMapper codeMapper;
	
	/*기초정보관리*/
	 @RequestMapping("/code/codeList.do")
	    public String codeList(Model model,@RequestParam("codeGroup") String codeGroup, Pagination pagination) {
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("codeGroup", codeGroup);
		 map.put("pagination",pagination);
		 pagination.setRecordCount(codeMapper.selectCount(map));
		 model.addAttribute("list", codeMapper.selectByCodeGroup(map));
	     model.addAttribute("codeGroup",codeGroup);
	        return "code/codeList";
	    }
	 
	 	/*기초정보관리추가*/
	    @RequestMapping(value="/code/create.do", method=RequestMethod.GET)
	    public String create(Model model,@RequestParam("codeGroup") String codeGroup) {
	    	model.addAttribute("codeGroup",codeGroup);
	        return "code/create";
	    }
	    
	    /*
	    @RequestMapping(value="/code/create.do", method=RequestMethod.POST)
	    public String create(Model model,Code code,@RequestParam("codeGroup") String codeGroup) {
	        codeMapper.insert(code);
	        String c=codeGroup;
	        System.out.println("출력:"+c); //// redirect 더 알아보기 검색해서
	        return "redirect:/code/codeList.do?codeGroup="+c+"";
	    }*/


}
