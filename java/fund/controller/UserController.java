package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fund.BaseController;
import fund.dto.Pagination;
import fund.dto.Sponsor;
import fund.dto.User;
import fund.mapper.UserMapper;
import fund.service.ReportBuilder;
import fund.service.UserService;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class UserController extends BaseController{

	@Autowired UserMapper userMapper;
	@Autowired UserService userService;
	

    //사용자 중복 확인
	@RequestMapping(value="/user/repeatCheck.do",method=RequestMethod.GET)
	public @ResponseBody User repeatCheck(HttpServletRequest request,HttpServletResponse response)throws Exception{
		response.addHeader("Access-Control-Allow-Origin", "*");
		String loginName = request.getParameter("loginName");
		User user=userMapper.repeatCheck(loginName);
		if(user==null){
			user=new User();
		}
	
		return user;
	}

	//사용자 생성 페이지
	@RequestMapping(value="/user/user_r.do",method=RequestMethod.GET)
	public String userRegister(Model model)throws Exception{
		User user=new User();
		model.addAttribute("user",user);
		return "user/userRegister";
	}

	//사용자 계정 추가
	@RequestMapping(value="/user/userInsert.do",method=RequestMethod.POST)
	public String userInsert(Model model,@Valid User user,BindingResult result)throws Exception{
		
		System.out.println("넘오옴!!!");
		
		if (result.hasErrors()) {
            // validation error!!
			System.out.println("에러 발생");
			model.addAttribute("user", user);
			return "user/userRegister";
           
        }
		
		System.out.println(user.getPassword());
		user.setPassword(userService.encryptPasswd(user.getPassword())); //단방향 암호화
		userMapper.userInsert(user);

		return "redirect:/user/user_m.do";
	}

	//로그인
	@RequestMapping(value="/user/login.do",method=RequestMethod.POST)
	public String login(User user)throws Exception{
		return "user/userRegister";
	}
	
	//로그인 실패시
	@RequestMapping("/user/loginfail.do")
    public String loginFail(Model model) {
    	User user=new User();
    	user.setLoginCheck(false);
        
    	model.addAttribute("user", user);
    	
    	return "home/index";
    	
    }
    

	//회원관리 기본페이지
	@RequestMapping(value="/user/user_m.do",method=RequestMethod.GET)
	public String userManage(Model model, Pagination pagination)throws Exception{
		pagination.setRecordCount(userMapper.userSelectCount());
		model.addAttribute("list", userMapper.userSelectPage(pagination));
		return "user/userManage";
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

		for(Sponsor s:list){
			total=total+s.getSum();


		}
		model.addAttribute("total", total);
		model.addAttribute("list", list);

		return "user/church";
	}

	@RequestMapping(value="/user/churchSearch.do", params="cmd=xlsx")
	public void churchXlsx(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		Pagination pagination = new Pagination();
		pagination.setStartDate(startDate);
		pagination.setEndDate(endDate);
		List<Sponsor> list=userMapper.churchSum(pagination);
		pagination.setRecordCount(list.size());
		list=userMapper.churchSum2(pagination);
		ReportBuilder reportBuilder = new ReportBuilder("ByChurch",list,"church.xlsx",req,res);
		reportBuilder.build("xlsx");
	} 

}