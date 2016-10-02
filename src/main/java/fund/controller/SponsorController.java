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
import fund.service.ReportBuilder;
import fund.service.UserService;
import net.sf.jasperreports.engine.JRException;


@Controller
public class SponsorController {
	@Autowired SponsorMapper sponsorMapper;
	@Autowired FileAttachmentMapper fileAttachmentMapper;
	@Autowired PaymentMapper paymentMapper;


    //회원관리 기본페이지
	@RequestMapping(value="/sponsor/sponsor_m.do",method=RequestMethod.GET)
	public String userManage(Model model, Pagination pagination)throws Exception{
		System.out.println("sponsor_m.do 액션메소드");
		pagination.setRecordCount(sponsorMapper.selectCount());
	    model.addAttribute("list", sponsorMapper.selectPage(pagination));
		return "sponsor/sponsorManage";
	}
	

	
	//회원관리 검색기능 
	@RequestMapping(value="/sponsor/search.do",method=RequestMethod.GET)
	public String sponsorSearch(Model model, Pagination pagination)throws Exception{
		System.out.println("---------------------------");
		
		String codeName=pagination.getCodeName();
		System.out.println("test "+codeName);
		System.out.println("---------------------------");
		List<Sponsor> sponsorList=null;
		if(codeName.equals("이름")){
			String nameForSearch=pagination.getNameForSearch();
			System.out.println("name1 "+nameForSearch);
			sponsorList=sponsorMapper.nameSearch(pagination);
		}else{
			
			int type=sponsorMapper.sponsorTypeCheck(codeName);
			pagination.setType(type);
			pagination.setRecordCount(sponsorMapper.searchCount(codeName));
			sponsorList=sponsorMapper.sponsorSearch(pagination);
		}
		
		
		
		
		//String type=userMapper.sponsorTypeCheck(codeName);
		
		//System.out.println(type);
		
		//pagination.setCodeName(codeName);
		//pagination.setType(type);
		
		//pagination.setRecordCount(userMapper.searchCount(codeName));
		//List<Sponsor> sponsorList=userMapper.sponsorSearch(pagination);
		model.addAttribute("list", sponsorList);
		return "sponsor/sponsorManage";
	}

    //신규
	@RequestMapping(value="/sponsor/sponsor.do",method=RequestMethod.GET)
	public String userRegister(Model model,Sponsor sponsor)throws Exception{
		Integer num=sponsorMapper.ceateNumber();
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
		sponsor.setSponsorNo(sponsorNo);
	
		//첨부파일리스트 임시테스트 -> 세션값으로 방식으로 바꾸어야함 
		List<FileAttachment> list=fileAttachmentMapper.selectByArticleId(100);
		System.out.println(list);
		model.addAttribute("files", fileAttachmentMapper.selectByArticleId(100));
		model.addAttribute("sponsor",sponsor);
		

		return "sponsor/sponsor";
	}
	
	//회원입력 insert
 	@RequestMapping(value="/sponsor/sponsorInsert.do",method=RequestMethod.POST)
 	public String sponsorRegister(HttpServletRequest request,Sponsor sponsor)throws Exception{
 		 		
 		sponsor.setChurchID(sponsorMapper.selectChurchCode(sponsor));
 
 		String homeRoadAddress = request.getParameter("homeRoadAddress");
 		String homeDetailAddress = request.getParameter("homeDetailAddress");
 		String homePostCode = request.getParameter("homePostCode");
 	
 		String officeRoadAddress = request.getParameter("officeRoadAddress");
 		String officeDetailAddress = request.getParameter("officeDetailAddress");
 		String officePostCode = request.getParameter("officePostCode");
 		
 		String homeAddress=homeRoadAddress+"*"+homeDetailAddress+"*"+homePostCode;
 	    String officeAddress=officeRoadAddress+"*"+officeDetailAddress+"*"+officePostCode;
 	    
 	    
 	    sponsor.setHomeAddress(homeAddress);
 	    sponsor.setOfficeAddress(officeAddress);

 	    System.out.println("구분 "+sponsor.getSort());
 	    
 	    if(sponsor.getSort()==0){
 	    	System.out.println("입력");
 		sponsorMapper.sponsorInsert(sponsor);
 	    }
 	    if(sponsor.getSort()==1){
 	    	System.out.println("수정");
 	     sponsorMapper.updateSponsor(sponsor);
 	    }
 	    
 	    
 		 return "redirect:/sponsor/sponsor_m.do";
 	}
 	
 	
 	//후원자 정보보기
 	@RequestMapping(value="/sponsor/detail.do",method=RequestMethod.GET)
	public String sponsorDetail(@RequestParam("id")String sponsorNo,Model model)throws Exception{
        Sponsor sponsor=sponsorMapper.selectBySponsorNo(sponsorNo);
        String homeAddress=sponsor.getHomeAddress();
        String officeAddress=sponsor.getOfficeAddress();
        
        String[] home=homeAddress.split("\\*");
        String[] office=officeAddress.split("\\*");
        /*
        System.out.println(home[0]);
        System.out.println(home[1]);
        System.out.println(home[2]);
        
        System.out.println(office[0]);
        System.out.println(office[1]);
        System.out.println(office[2]);*/
        
        String homeRoadAddress=home[0];
        String homeDetailAddress=home[1];
        String homePostCode=home[2];
        
        String officeRoadAddress=office[0];
        String officeDetailAddress=office[1];
        String officePostCode=office[2];
        
        
        sponsor.setHomeRoadAddress(homeRoadAddress);
        sponsor.setHomeDetailAddress(homeDetailAddress);
        sponsor.setHomePostCode(homePostCode);
        
        sponsor.setOfficeRoadAddress(officeRoadAddress);
        sponsor.setOfficeDetailAddress(officeDetailAddress);
        sponsor.setOfficePostCode(officePostCode);
        
        
        model.addAttribute("sponsor", sponsor);
        int sponsorID=sponsor.getId();
        System.out.println("sponsorID>> "+sponsorID);
        List<Payment> paymentList = paymentMapper.selectPaymentRegular(sponsorID);
		model.addAttribute("paymentList", paymentList);
		List<Payment> paymentList2 = paymentMapper.selectPaymentIrregular(sponsorID);
		model.addAttribute("paymentList2", paymentList2);
    
        
        
		return "sponsor/sponsor";
	}
 	
 	//후원자 정보 삭제하기
 	 	@RequestMapping(value="/sponsor/delete.do",method=RequestMethod.GET)
 		public String sponsorDelete(@RequestParam("id")String sponsorNo,Model model)throws Exception{
 	 		
            sponsorMapper.removeSponsor(sponsorNo);
            return "redirect:/sponsor/sponsor_m.do";
 		}
 	
 	
 	
 	

	@RequestMapping(value="/user/member_r.do",method=RequestMethod.GET)
	public String memberRegister(Model model)throws Exception{

		return "user/memberRegister";
	}

	@RequestMapping(value="/sponsor/post.do",method=RequestMethod.GET)
	public String post(Model model)throws Exception{

		return "sponsor/post";
	}


	

	//소속교호찾기 자동완성
	@RequestMapping(value="/sponsor/autoList.do", produces="application/json;charset=UTF-8", method=RequestMethod.GET)
	public @ResponseBody List<Code> autoList(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("---------------acbbsds---------------");
		response.addHeader("Access-Control-Allow-Origin", "*");
		String input = request.getParameter("input");
		List<Code> resultList=sponsorMapper.selectAuto(input);
		return resultList;
	}
	
	
	
	//기간기준으로 DM발송 리스트 찾기
	@RequestMapping(value="/sponsor/postSearch.do",method=RequestMethod.GET)
	public String postByDate(HttpServletRequest request,HttpServletResponse response,Model model,Pagination  pagination)throws Exception{
		//String startDate = request.getParameter("startDate");
	//	String endDate = request.getParameter("endDate");
		String check = request.getParameter("check");
		
		 pagination.setRecordCount(sponsorMapper.countForDM(pagination));
		
		List<Sponsor> postList=sponsorMapper.postManage(pagination);
		
		
		
		System.out.println(postList);
		String temp,address,postCode;
		int middle;
		 for (Sponsor i : postList) {
	            if(i.getMailTo()==0){
	            temp=i.getHomeAddress();
	            System.out.println(temp);
	            String[] home=temp.split("\\*");
	            System.out.println(home[0]);
	            System.out.println(home[1]);
	            System.out.println(home[2]);
	            
	            address=home[0]+home[1];
	    		postCode=home[2];
	    		System.out.println(address);
	    		System.out.println(postCode);
	            
	    		//middle=temp.indexOf("/");
	    		//address=temp.substring(0,middle-1);
	    		//postCode=temp.substring(middle+1,temp.length());
	    		i.setAddress(address);
	    		i.setPostCode(postCode);
	            }
	            if(i.getMailTo()==1){
	                temp=i.getOfficeAddress();
	                System.out.println("1 "+temp);
	                String[] office=temp.split("\\*");
		            System.out.println(office[0]);
		            System.out.println(office[1]);
		            System.out.println(office[2]);
		            
		            address=office[0]+office[1];
		    		postCode=office[2];
		    		
		    		System.out.println(address);
		    		System.out.println(postCode);
		            
	                
	                
		    		//middle=temp.indexOf("/");
		    		//address=temp.substring(0,middle-1);
		    		//postCode=temp.substring(middle+1,temp.length());
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
		 
		 	
		return "sponsor/post";
	}
	
	
	//파일업로드
	 @RequestMapping(value="/sponsor/upload.do", method=RequestMethod.POST)
	    public String fileUpload(Model model,@RequestParam("file") MultipartFile uploadedFile) throws IOException {
	   
	            if (uploadedFile.getSize() > 0 ) {
	                FileAttachment file = new FileAttachment();
	                file.setSponsorID(100); // 나중에 조인해서 변경해야함
	                file.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
	                file.setFilesize((int)uploadedFile.getSize());
	                file.setData(uploadedFile.getBytes());
	                
	                fileAttachmentMapper.insert(file);
	            }
	        
	        return "redirect:/sponsor/sponsor.do";
	    }
	 
	 
	 //파일 다운로드
	 @RequestMapping("/sponsor/download.do")
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
	 @RequestMapping(value="sponsor/fileDelete.do",method=RequestMethod.GET)
	 public String fileDelete(@RequestParam("id") int id)throws IOException{
		 System.out.println("id test sss >> "+id);
		 System.out.println();
		 fileAttachmentMapper.deleteById(id);
		 return "redirect:/sponsor/sponsor.do";
		 
		 
	 }
	 
	 
	 
	 
	 //- 후원인구분2별 출연내역 -
	 
	 @RequestMapping(value="sponsor/cast.do",method=RequestMethod.GET)
	 public String cast(Model model)throws IOException{
		 int sum=0;//후원인구분2별 출연내역 금액
		 model.addAttribute("sum", sum);
		return "sponsor/castHistory"; 
	 }
	 
	 
	 @RequestMapping(value="sponsor/castList.do",method=RequestMethod.GET)
	 public String castList(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,Model model)throws IOException{
		 System.out.println(startDate +" "+endDate);
		 List<Sponsor> list=sponsorMapper.castBySponsorType2(startDate,endDate);
		 
		 
		    int sponsorCount=0; // 후원인구분2별 출연내역 회원수
			int castCount=0; // 후원인구분2별 출연내역 출연수
			int sum=0;//후원인구분2별 출연내역 금액
		 
		 for(Sponsor i:list){
			 sponsorCount=sponsorCount+i.getSponsorCount();
			 castCount=castCount+i.getCastCount();
			 sum=sum+i.getSum();
		 }
		 
		 
		
		 System.out.println("sponsor"+sponsorCount+"cast"+castCount+"sum"+ sum);
			model.addAttribute("sponsorCount", sponsorCount);
			model.addAttribute("castCount",castCount);
			model.addAttribute("sum", sum);
			model.addAttribute("list", list);
		
		return "sponsor/castHistory"; 
	 }
	 
	 
	 @RequestMapping(value="/sponsor/post.do", method=RequestMethod.POST, params="cmd=xlsx" )
	 public void excelDMReport(Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		System.out.println("엑셀파일 생성 sendDM");
		List<Sponsor> list = sponsorMapper.excelDM(pagination);
		ReportBuilder reportBuilder = new ReportBuilder("sendDM", list, "sendDM.xlsx",req,res);
		reportBuilder.build("xlsx");
	 }
	
	 @RequestMapping(value="/sponsor/sponsor_m.do.do", method=RequestMethod.POST, params="cmd=xlsx" )
	 public void sponsorList(Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		System.out.println("액션메소드를 찾아서");
		List<Sponsor> list = sponsorMapper.sponsorListExcel(pagination);
		ReportBuilder reportBuilder = new ReportBuilder("sponsorList", list, "sponsorList.xlsx",req,res);
		reportBuilder.build("xlsx");
	 }
	 
	



}