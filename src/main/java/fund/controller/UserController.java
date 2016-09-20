package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fund.dto.User;
import fund.mapper.UserMapper;

@Controller
public class UserController {
	@Autowired UserMapper userMapper;
	


	 //사용자 생성 페이지
	 @RequestMapping(value="/user/user_r.do",method=RequestMethod.GET)
	 public String userRegister(Model model)throws Exception{
		 
		return "user/userRegister";
	 }
	 
	 //사용자 계정 추가
	 @RequestMapping(value="/user/userInsert.do",method=RequestMethod.POST)
	 public String userInsert(User user)throws Exception{
		 userMapper.userInsert(user);

		return "user/userRegister";
	 }
	 
	 //로그인
	 @RequestMapping(value="/user/login.do",method=RequestMethod.POST)
	 public String login(User user)throws Exception{
		 

		return "user/userRegister";
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
