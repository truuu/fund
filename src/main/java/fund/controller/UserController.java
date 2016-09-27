package fund.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fund.BaseController;

@Controller
public class UserController extends BaseController{
	
	
	
	 @RequestMapping(value="/user/user_m.do",method=RequestMethod.GET)
	 public String userManage(Model model)throws Exception{
		 
		 
		return "user/userManage";
	 }
	 
	 
	 @RequestMapping(value="/user/user.do",method=RequestMethod.GET)
	 public String userRegister(Model model)throws Exception{
		 
		return "user/user";
	 }
	 
	 @RequestMapping(value="/user/member_r.do",method=RequestMethod.GET)
	 public String memberRegister(Model model)throws Exception{
		 
		return "user/memberRegister";
	 }
	 
	 @RequestMapping(value="/user/post.do",method=RequestMethod.GET)
	 public String post(Model model)throws Exception{
		 
		return "user/post";
	 }
	 
	 
	 @RequestMapping(value="/user/temp_p.do",method=RequestMethod.GET)
	 public String tempPassword(Model model)throws Exception{
		 
		return "user/tempPassword";
	 }
	 
	 
	 // 보류
	 @RequestMapping(value="/user/church.do",method=RequestMethod.GET)
	 public String church(Model model)throws Exception{
		 
		return "user/church";
	 }
	 
	 
	 
	 
	 

}
