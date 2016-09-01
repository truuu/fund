package fund.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fund.dto.User;
import fund.mapper.UserMapper;
import fund.dto.Code;
import fund.dto.Sponsor;

@Controller
public class UserController {
	@Autowired UserMapper userMapper;

    //회원관리 기본페이지
	@RequestMapping(value="/user/user_m.do",method=RequestMethod.GET)
	public String userManage(Model model)throws Exception{
		
		List<Sponsor> sponsorList=userMapper.sponsorManage();
		model.addAttribute("sponsorList", sponsorList);
		return "user/userManage";
	}


	@RequestMapping(value="/user/user.do",method=RequestMethod.GET)
	public String userRegister(Model model)throws Exception{
		Integer num=userMapper.ceateNumber();
		String number;
		if(num==null){
			System.out.println("11");
			number="0001";
		}else{
			System.out.println("22");
			number=String.valueOf((int)num+1);
			if(number.length()==1){
				number="000"+number;
			}
			if(number.length()==2){
				number="00"+number;
			}
			if(number.length()==3){
				number="0"+number;
			}
			
		}
		Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
		//System.out.println("생성번호: " +  oCalendar.get(Calendar.YEAR)+"-"+number);
		String sponsorNo=oCalendar.get(Calendar.YEAR)+"-"+number;
		System.out.println("sponsorNo "+sponsorNo);
		

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

	//소속교호찾기 자동완성
	@RequestMapping(value="/user/autoList.do", produces="application/json;charset=UTF-8", method=RequestMethod.GET)
	public @ResponseBody List<Code> autoList(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("---------------acbbsds---------------");
		response.addHeader("Access-Control-Allow-Origin", "*");
		String input = request.getParameter("input");
		List<Code> resultList=userMapper.selectAuto(input);
		return resultList;
	}
	
	//기간기준으로 DM발송 리스트 찾기
	@RequestMapping(value="/user/postSearch.do",method=RequestMethod.GET)
	public String postByDate(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		System.out.println(startDate+" "+endDate);
		
		List<Sponsor> postList=userMapper.postManage(startDate, endDate);
		
		
		
		return "user/post";
	}






}
