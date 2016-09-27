package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.BaseController;

import fund.dto.Pagination;
import fund.dto.Sponsor;
import fund.dto.User;
import fund.mapper.UserMapper;

import java.util.*;

public class UserController extends BaseController{

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
	 
	 
	 
	 @RequestMapping(value="/user/church.do",method=RequestMethod.GET)
	 public String church(Model model)throws Exception{
		
	     
		return "user/church";
	 }
	 
	 
	 @RequestMapping(value="/user/churchSearch.do",method=RequestMethod.GET)
	 public String churchSearch(Pagination  pagination,Model model)throws Exception{
		 
		  long total=0;
		  List<Sponsor> list=userMapper.churchSum(pagination);
		  pagination.setRecordCount(list.size());
			
			
		
		 list=userMapper.churchSum2(pagination);
		 //System.out.println(list.get(0).getChurch()+" "+list.get(0).getSum());
		// System.out.println(list.get(1).getChurch()+" "+list.get(1).getSum());
		 
		 
		 for(Sponsor s:list){
			System.out.println(s.getSum());
			total=total+s.getSum();
			
			 
		 }
		 System.out.println(" total "+total);
		 model.addAttribute("total", total);
		 model.addAttribute("list", list);
		 
		 
		 
		 
		return "user/church";
	 }
	 
	 
	 
	 

}
