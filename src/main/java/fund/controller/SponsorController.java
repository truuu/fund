package fund.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.nio.file.Paths;
import java.io.BufferedOutputStream;
import java.net.URLEncoder;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fund.dto.*;
import fund.mapper.*;
import fund.service.UserService;


@Controller
public class SponsorController {
	@Autowired UserMapper userMapper;
	@Autowired FileAttachmentMapper fileAttachmentMapper;

    //회원관리 기본페이지
	@RequestMapping(value="/sponsor/user_m.do",method=RequestMethod.GET)
	public String userManage(Model model, Pagination pagination)throws Exception{
		
		 pagination.setRecordCount(userMapper.selectCount());
	        model.addAttribute("list", userMapper.selectPage(pagination));
		return "user/userManage";
	}
	

	
	//회원관리 검색기능 
	@RequestMapping(value="/sponsor/search.do",method=RequestMethod.GET)
	public String sponsorSearch(@RequestParam("codeName")String codeName,Model model, Pagination pagination)throws Exception{
		System.out.println("---------------------------");
		
		System.out.println("test "+codeName);
		
		System.out.println("---------------------------");
		
		String type=userMapper.sponsorTypeCheck(codeName);
		
		System.out.println(type);
		
		pagination.setCodeName(codeName);
		pagination.setType(type);
		
		pagination.setRecordCount(userMapper.searchCount(codeName));
		List<Sponsor> sponsorList=userMapper.sponsorSearch(pagination);
		model.addAttribute("list", sponsorList);
		return "user/userManage";
	}


	@RequestMapping(value="/sponsor/sponsor.do",method=RequestMethod.GET)
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
		String sponsorNo=oCalendar.get(Calendar.YEAR)+"-"+number;
		System.out.println("sponsorNo "+sponsorNo);
	
		//첨부파일리스트 임시테스트 -> 세션값으로 방식으로 바꾸어야함 
		model.addAttribute("files", fileAttachmentMapper.selectByArticleId(10));
		model.addAttribute("sponsorNo",sponsorNo);
		

		return "user/user";
	}
	
	//회원입력 insert
 	@RequestMapping(value="/sponsor/sponsorInsert.do",method=RequestMethod.POST)
 	public String sponsorRegister(HttpServletRequest request,Sponsor sponsor)throws Exception{
 		
 		
 		System.out.println(sponsor.getSponsorNo());
 		System.out.println(sponsor.getJuminNo());
 		System.out.println(sponsor.getName());
 		System.out.println(sponsor.getHomePhone());
 		System.out.println(sponsor.getSignUpDate());
 		System.out.println(sponsor.getMobilePhone());
 		System.out.println(sponsor.getRecommender());
 		System.out.println(sponsor.getEmail());
 		System.out.println(sponsor.getEtc());
 		System.out.println(sponsor.getChurch());
 		System.out.println(sponsor.getCompany());
 		System.out.println(sponsor.getDepartment());
 		System.out.println(sponsor.getPosition());
 		System.out.println(sponsor.getOfficePhone());
 		System.out.println(sponsor.getRecommenderRelation());
 		System.out.println(sponsor.getMailTo());
 		System.out.println(sponsor.isMailReceiving());
 		System.out.println(sponsor.getSponsorType1ID());
 		System.out.println(sponsor.getSponsorType2ID());
 		
 		System.out.println("id > "+userMapper.selectChurchCode(sponsor));
 		
 		sponsor.setChurchID(userMapper.selectChurchCode(sponsor));
 		
 		
 		System.out.println();
 		
 		String homeRoadAddress = request.getParameter("homeRoadAddress");
 		String homeDetailAddress = request.getParameter("homeDetailAddress");
 		String homePostCode = request.getParameter("homePostCode");
 	
 		
 		String officeRoadAddress = request.getParameter("officeRoadAddress");
 		String officeDetailAddress = request.getParameter("officeDetailAddress");
 		String officePostCode = request.getParameter("officePostCode");
 		
 		String homeAddress=homeRoadAddress+" "+homeDetailAddress+"/"+homePostCode;
 		
 		String officeAddress=officeRoadAddress+" "+officeDetailAddress+"/"+officePostCode;
 		System.out.println("집주소");
 		System.out.println(homeAddress);
 		
 		System.out.println("회사 주소");
 		System.out.println(officeAddress);
 		
 		System.out.println(sponsor.toString());
 		
 		userMapper.sponsorInsert(sponsor);
 		
 		 return "redirect:/sponsor/user_m.do";
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
	
	
	//회원입력 insert
	@RequestMapping(value="/sponsor/create.do",method=RequestMethod.POST)
	public String  sponsorCreate(Sponsor sponsor,Model model)throws Exception{
		
		
		
		return null;
	}
	
	//기간기준으로 DM발송 리스트 찾기
	@RequestMapping(value="/user/postSearch.do",method=RequestMethod.GET)
	public String postByDate(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String check = request.getParameter("check");
		
		List<Sponsor> postList=userMapper.postManage(startDate, endDate);
		
		
		
		System.out.println(postList);
		String temp,address,postCode;
		int middle;
		 for (Sponsor i : postList) {
	            if(i.getMailTo()==0){
	            temp=i.getHomeAddress();
	    		middle=temp.indexOf("/");
	    		address=temp.substring(0,middle-1);
	    		postCode=temp.substring(middle+1,temp.length());
	    		i.setAddress(address);
	    		i.setPostCode(postCode);
	            }
	            if(i.getMailTo()==1){
	                temp=i.getOfficeAddress();
		    		middle=temp.indexOf("/");
		    		address=temp.substring(0,middle-1);
		    		postCode=temp.substring(middle+1,temp.length());
		    		i.setAddress(address);
		    		i.setPostCode(postCode);
	            }
	        }
		 
		 if(check.equals("f")){
			 System.out.println("false");
	     model.addAttribute("postList", postList);
		 }
		 if(check.equals("t")){
			 System.out.println("true");
			 UserService.writeNoticeListToFile("post.xls", postList);
			 model.addAttribute("postList", postList);
		 }
		 
		 	
		return "user/post";
	}
	
	
	//파일업로드
	 @RequestMapping(value="/user/upload.do", method=RequestMethod.POST)
	    public String fileUpload(Model model,@RequestParam("file") MultipartFile uploadedFile) throws IOException {
	   
	            if (uploadedFile.getSize() > 0 ) {
	                FileAttachment file = new FileAttachment();
	                file.setSponsorID(10); // 나중에 조인해서 변경해야함
	                file.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
	                file.setFilesize((int)uploadedFile.getSize());
	                file.setData(uploadedFile.getBytes());
	                fileAttachmentMapper.insert(file);
	            }
	        
	        return "redirect:/user/user.do";
	    }
	 
	 
	 //파일 다운로드
	 @RequestMapping("/user/download.do")
	    public void download(@RequestParam("id") int id, HttpServletResponse response) throws IOException {
		 FileAttachment file= fileAttachmentMapper.selectById(id);
	       if (file == null) return;
	        String fileName = URLEncoder.encode(file.getFileName(),"UTF-8");
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
	        try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
	            output.write(file.getData());
	        }
	    }
	 
	 //파일삭제
	 @RequestMapping(value="user/fileDelete.do",method=RequestMethod.GET)
	 public String fileDelete(@RequestParam("id") int id)throws IOException{
		 System.out.println("id test sss >> "+id);
		 System.out.println();
		 fileAttachmentMapper.deleteById(id);
		 return "redirect:/user/user.do";
		 
		 
	 }


	
	





}
