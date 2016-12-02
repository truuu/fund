package fund.controller;

import java.io.UnsupportedEncodingException;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fund.BaseController;
import fund.dto.Corporate;
import fund.dto.Pagination;
import fund.mapper.CorporateMapper;
import fund.service.CodeService;

@Controller
public class CorporateController extends BaseController{

	@Autowired CorporateMapper corporateMapper;
	@Autowired CodeService codeService;
	
	
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
	public String create(Model model,@Valid Corporate corporate, BindingResult result, @RequestParam("postNum") String postNum, 
			@RequestParam("address1") String address1, @RequestParam("address2") String address2) {
		
		if(codeService.validate(postNum)==true){
			String address = address1+"*"+address2+"*"+postNum;  
			corporate.setAddress(address);
		}
		
		if(codeService.validate(corporate)==true)
		{
			corporateMapper.insert(corporate);
			
		}	
		else if(result.hasErrors()) {
			model.addAttribute("corporate", corporate);
			return "code/corporateCreate";
           
        }
		
		return "redirect:/code/corporateList.do";
		
	}
	
	/*Corporate edit*/
	@Secured("ROLE_true")
	@RequestMapping(value="/code/corporateEdit.do", method=RequestMethod.GET)
	public String edit(Model model,@RequestParam("ID") int ID) {
		Corporate corporate = corporateMapper.selectByID(ID);
		
	    String[] array;  
	    array = (corporate.getAddress()).split("\\*");
	  
	    model.addAttribute("post",array[2]);
	    model.addAttribute("address1",array[1]);
	    model.addAttribute("address2",array[0]);

        model.addAttribute("corporate",corporate);
		return "code/corporateEdit";
	}
	
	@Secured("ROLE_true")
	@RequestMapping(value="/code/corporateEdit.do", method=RequestMethod.POST)
    public String edit(Model model, @Valid Corporate corporate, BindingResult result, 
    		@RequestParam("postNum") String postNum, @RequestParam("address1") String address1, @RequestParam("address2") String address2)
            throws UnsupportedEncodingException {
		
		String address = address1+"*"+address2+"*"+postNum;  
		corporate.setAddress(address);
		
		/*if(codeService.validate(postNum)==true){
			String address = address1+"*"+address2+"*"+postNum;  
			corporate.setAddress(address);
		}*/
		
		if(codeService.validate(corporate)==true)
		{
			corporateMapper.update(corporate);
			
		}	
		else if(result.hasErrors()) {
			model.addAttribute("corporate", corporate);
			model.addAttribute("post",postNum);
		    model.addAttribute("address1",address1);
		    model.addAttribute("address2",address2);
			return "code/corporateEdit";
           
        }
        return "redirect:/code/corporateList.do";
    }
    
	@Secured("ROLE_true")
    @RequestMapping("/code/corporateDelete.do")
    public String delete(Model model, @RequestParam("ID") int ID) {
        corporateMapper.delete(ID);
        return "redirect:/code/corporateList.do";
    }

  

}
