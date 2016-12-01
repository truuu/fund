package fund.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedOutputStream;
import java.net.URLEncoder;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fund.BaseController;
import fund.dto.*;
import fund.mapper.*;
import fund.service.ReportBuilder;
import fund.service.UserService;
import fund.service.AES128UtilService;
import fund.service.FileExtFilter;
import net.sf.jasperreports.engine.JRException;

import java.util.*;


@Controller
public class SponsorController extends BaseController{
	@Autowired SponsorMapper sponsorMapper;
	@Autowired FileAttachmentMapper fileAttachmentMapper;
	@Autowired PaymentMapper paymentMapper;
	@Autowired CodeMapper codeMapper;
	@Autowired DonationPurposeMapper donationPurposeMapper;
	@Autowired AES128UtilService cipherService; //양방향 암호화 서비스
	@Autowired FileExtFilter fileExtFilter;


	//후원자관리 기본페이지
	@RequestMapping(value="/sponsor/sponsor_m.do",method=RequestMethod.GET)
	public String userManage(Model model, Pagination pagination)throws Exception{
		pagination.setRecordCount(sponsorMapper.selectCount());
		model.addAttribute("list", sponsorMapper.selectPage(pagination));
		List<Code> code1=codeMapper.selectByCodeGroupID(1);
		List<Code> code2=codeMapper.selectByCodeGroupID(2);

		code1.addAll(code2);

		model.addAttribute("sponsorType", code1);
		return "sponsor/sponsorManage";
	}

	//codeName check 
	@RequestMapping(value="/sponsor/codeNameCheck.do",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> codeNameCheck(@RequestParam("codeName")String codeName,HttpServletRequest request,HttpServletResponse response)throws Exception{
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println("codeName >> "+codeName);
		List<Sponsor> sponsor=sponsorMapper.codeNameCheck(codeName);
		if(sponsor==null){
			System.out.println("없음 검사 codeName");
			sponsor=new ArrayList<Sponsor>();
		}
		System.out.println(sponsor.toString());
		map.put("sponsor", sponsor);
		return map;
	}

	//name check 
	@RequestMapping(value="/sponsor/nameCheck.do",method=RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody List<Sponsor> nameCheck(@RequestParam("nameForSearch")String nameForSearch,HttpServletRequest request,HttpServletResponse response)throws Exception{
		response.addHeader("Access-Control-Allow-Origin", "*");	
		String name=nameForSearch;
		List<Sponsor> sponsor=sponsorMapper.nameCheck(name);
		if(sponsor==null){
			System.out.println("업음 검사 ");
			sponsor=new ArrayList<Sponsor>();
			return sponsor;
		}else{
			System.out.println("값 있다  ");
			return sponsor;
		}


	}


	//회원관리 검색기능 
	@RequestMapping(value="/sponsor/search.do",method=RequestMethod.GET)
	public String sponsorSearch(Model model, Pagination pagination)throws Exception{
		String codeName=pagination.getCodeName();
		List<Sponsor> sponsorList=null;
		if(codeName.equals("이름")){
			String nameForSearch=pagination.getNameForSearch();
			sponsorList=sponsorMapper.nameSearch(pagination);
		}else{

			int type=sponsorMapper.sponsorTypeCheck(codeName);
			pagination.setType(type);
			pagination.setRecordCount(sponsorMapper.searchCount(pagination));
			sponsorList=sponsorMapper.sponsorSearch(pagination);
		}

		List<Code> code1=codeMapper.selectByCodeGroupID(1);
		List<Code> code2=codeMapper.selectByCodeGroupID(2);
		code1.addAll(code2);

		model.addAttribute("sponsorType", code1);
		model.addAttribute("list", sponsorList);
		return "sponsor/sponsorManage";
	}

	//신규
	@RequestMapping(value="/sponsor/sponsor.do",method=RequestMethod.GET)
	public String userRegister(Model model,Sponsor sponsor)throws Exception{
		Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
		Integer num=sponsorMapper.ceateNumber();
		String[] year=sponsorMapper.ceateYear().split("-");
		int preYear=oCalendar.get(Calendar.YEAR);
		String number;
		if(num==null){//후원자를 처음 등록할때 0001 초기화 
			number="0001";
			String sponsorNo=preYear+"-"+number;
			sponsor.setSponsorNo(sponsorNo);
		}else{
			if(Integer.parseInt(year[0])==preYear){//년도가 변하지 않았을때 
				System.out.println("년도 그대로 !!!!!!");
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

				String sponsorNo=preYear+"-"+number;
				sponsor.setSponsorNo(sponsorNo);

			}
			if(Integer.parseInt(year[0])!=preYear){ // 년도가 변했을때 후원자 번호 생성
				System.out.println("년도 변함 !!!!!!!");
				number="0001";
				String sponsorNo=preYear+"-"+number;
				sponsor.setSponsorNo(sponsorNo);
			}
		}

		//첨부파일리스트 임시테스트 -> 세션값으로 방식으로 바꾸어야함 
		//List<FileAttachment> list=fileAttachmentMapper.selectByArticleId(100);
		//model.addAttribute("files", fileAttachmentMapper.selectByArticleId(100));
		model.addAttribute("sponsor",sponsor);


		model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupID(1));  // 후원인구분1 목록
		model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupID(2));  // 후원인구분1 목록



		return "sponsor/sponsor";
	}

	//회원입력 insert
	@RequestMapping(value="/sponsor/sponsorInsert.do",method=RequestMethod.POST)
	public String sponsorRegister(HttpServletRequest request,Model model,@Valid Sponsor sponsor,BindingResult result)throws Exception{


		if (result.hasErrors()) {
			// 에러 출력
			model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupID(1));  // 후원인구분1 목록
			model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupID(2));  // 후원인구분2 목록
			return "sponsor/sponsor";

		}

		if(!sponsor.getChurch().equals("")){
			sponsor.setChurchID(sponsorMapper.selectChurchCode(sponsor));
		}



		String homeRoadAddress = request.getParameter("homeRoadAddress");
		String homeDetailAddress = request.getParameter("homeDetailAddress");
		String homePostCode = request.getParameter("homePostCode");

		String officeRoadAddress = request.getParameter("officeRoadAddress");
		String officeDetailAddress = request.getParameter("officeDetailAddress");
		String officePostCode = request.getParameter("officePostCode");

		String homeAddress=homeRoadAddress+"*"+homeDetailAddress+"*"+homePostCode;
		String officeAddress=officeRoadAddress+"*"+officeDetailAddress+"*"+officePostCode;

		String encryption=cipherService.encAES(sponsor.getJuminNo());//jumin encryption
		sponsor.setJuminNo(encryption); // 암호화 후 저장


		sponsor.setHomeAddress(homeAddress);
		sponsor.setOfficeAddress(officeAddress);


		if(sponsor.getSort()==0){
			if(!sponsor.getChurch().equals("")){//소속교회를 입력했을 경우
				sponsorMapper.sponsorInsert(sponsor);
			}
			if(sponsor.getChurch().equals("")){ //소속교회를 입력하지 않은 경우 
				sponsorMapper.sponsorInsert2(sponsor);
			}
		}
		if(sponsor.getSort()==1){
			if(!sponsor.getChurch().equals("")){//소속교회를 변경한 경우
				sponsorMapper.updateSponsor(sponsor);
			}
			if(sponsor.getChurch().equals("")){//소속교회를 지운  경우
				sponsorMapper.updateSponsor2(sponsor);
			}

		}


		return "redirect:/sponsor/sponsor_m.do";
	}


	//후원자 정보보기
	@RequestMapping(value="/sponsor/detail.do",method=RequestMethod.GET)
	public String sponsorDetail(@RequestParam("id")int id,Model model)throws Exception{
		Sponsor sponsor=sponsorMapper.selectBySponsorNo(id);
		List<FileAttachment> file=fileAttachmentMapper.selectBySponosrId(id);
		String homeAddress=sponsor.getHomeAddress();
		String officeAddress=sponsor.getOfficeAddress();

		if(!homeAddress.equals("")){
			String[] home=homeAddress.split("\\*");
			System.out.println("size >> "+home.length);
			for(int i=0;i<home.length;i++){
				if(i==0){
					String homeRoadAddress=home[0];
					sponsor.setHomeRoadAddress(homeRoadAddress);
				}
				if(i==1){
					String homeDetailAddress=home[1];
					sponsor.setHomeDetailAddress(homeDetailAddress);
				}if(i==2){
					String homePostCode=home[2];
					sponsor.setHomePostCode(homePostCode);
				}
			}
			/*String homeRoadAddress=home[0];
			String homeDetailAddress=home[1];
			String homePostCode=home[2];
			sponsor.setHomeRoadAddress(homeRoadAddress);
			sponsor.setHomeDetailAddress(homeDetailAddress);
			sponsor.setHomePostCode(homePostCode);*/

		}
		if(!officeAddress.equals("")){
			String[] office=officeAddress.split("\\*");
			
			for(int i=0;i<office.length;i++){
				if(i==0){
					String officeRoadAddress=office[0];
					sponsor.setOfficeRoadAddress(officeRoadAddress);
				}
				if(i==1){
					String officeDetailAddress=office[1];
					sponsor.setOfficeDetailAddress(officeDetailAddress);
				}if(i==2){
					String officePostCode=office[2];
					sponsor.setOfficePostCode(officePostCode);
				}
			}
			
			/*String officeRoadAddress=office[0];
			String officeDetailAddress=office[1];
			String officePostCode=office[2];
			sponsor.setOfficeRoadAddress(officeRoadAddress);
			sponsor.setOfficeDetailAddress(officeDetailAddress);
			sponsor.setOfficePostCode(officePostCode);*/
		}

		String decoding=cipherService.decAES(sponsor.getJuminNo());//jumin decoding
		sponsor.setJuminNo(decoding);// 복호화 후 저장



		model.addAttribute("sponsor", sponsor);
		int sponsorID=sponsor.getId();
		model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupID(1));  // 후원인구분1 목록
		model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupID(2));  // 후원인구분1 목록
		model.addAttribute("files", file);//첨부파일

		return "sponsor/sponsor";
	}

	@RequestMapping(value="/sponsor/paymentList.do",method=RequestMethod.GET)  // 정기 납입관리
	public String paymentList(@RequestParam("id") int id, Model model){
		Sponsor sponsor=sponsorMapper.selectBySponsorNo(id);
		model.addAttribute("sponsor", sponsor);
		List<Payment> paymentList = paymentMapper.selectPaymentRegular(id);
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("sponsorID",id);
		model.addAttribute("sponsorNo",sponsorMapper.selectBySponsorNo2(id));
		return "sponsor/paymentList";
	}

	@RequestMapping(value="/sponsor/paymentList2.do", method=RequestMethod.GET)  // 비정기 납입관리
	public String paymentList2(Model model, @RequestParam("id") int id){
		Sponsor sponsor=sponsorMapper.selectBySponsorNo(id);
		model.addAttribute("sponsor", sponsor);
		List<Payment> paymentList2 = paymentMapper.selectPaymentIrregular(id);
		model.addAttribute("paymentList2", paymentList2);
		model.addAttribute("sponsorID",id);
		model.addAttribute("sponsorNo",sponsorMapper.selectBySponsorNo2(id));
		return "sponsor/paymentList2";
	}

	@RequestMapping(value="/sponsor/insertIrrgularPayment.do", method=RequestMethod.GET)  // 비정기 납입등록
	public String insertIrrgularPayment1(Model model,@RequestParam("id") int id) {
		Sponsor sponsor=sponsorMapper.selectBySponsorNo(id);
		model.addAttribute("sponsor", sponsor);
		model.addAttribute("sponsorID",id);
		model.addAttribute("sponsorNo",sponsorMapper.selectBySponsorNo2(id));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());

		return "sponsor/insertIrrgularPayment";
	}

	@RequestMapping(value="/sponsor/insertIrrgularPayment.do", method=RequestMethod.POST)
	public String insertIrrgularPayment2(IregularPayment iregularPayment,Model model) throws Exception {
		Payment payment = new Payment();
		payment.setSponsorID(iregularPayment.getSponsorID());
		payment.setAmount(iregularPayment.getAmount());
		Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(iregularPayment.getPaymentDate());
		payment.setPaymentDate(date);
		payment.setPaymentMethodID(iregularPayment.getPaymentMethodID());
		payment.setDonationPurposeID(iregularPayment.getDonationPurposeID());
		payment.setEtc(iregularPayment.getEtc());

		paymentMapper.insertIrregularPayment(payment);

		return "redirect:/sponsor/paymentList2.do?id="+iregularPayment.getSponsorID();
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
		response.addHeader("Access-Control-Allow-Origin", "*");
		String input = request.getParameter("input");
		List<Code> resultList=sponsorMapper.selectAuto(input);
		return resultList;
	}



	//기간기준으로 DM발송 리스트 찾기
	@RequestMapping(value="/sponsor/postSearch.do")
	public String postByDate(HttpServletRequest request,HttpServletResponse response,Model model,Pagination  pagination)throws Exception{
		String check = request.getParameter("check");	
		pagination.setRecordCount(sponsorMapper.countForDM(pagination));
		List<Sponsor> postList=sponsorMapper.postManage(pagination);

		String temp,address,postCode;
		int middle;
		for (Sponsor i : postList) {
			if(i.getMailTo()==0){
				temp=i.getHomeAddress();
				String[] home=temp.split("\\*");
				address=home[0]+home[1];
				postCode=home[2];
				i.setAddress(address);
				i.setPostCode(postCode);
			}
			if(i.getMailTo()==1){
				temp=i.getOfficeAddress();
				String[] office=temp.split("\\*");
				address=office[0]+office[1];
				postCode=office[2];

				i.setAddress(address);
				i.setPostCode(postCode);
			}
		}

		if(check.equals("f")){
			model.addAttribute("postList", postList);
		}
		if(check.equals("t")){
			UserService.writeNoticeListToFile("post.xls", postList);
			model.addAttribute("postList", postList);
		}

		return "sponsor/post";
	}


	//파일업로드
	@RequestMapping(value="/sponsor/upload.do", method=RequestMethod.POST)
	public String fileUpload(Model model,@RequestParam("id") int id,@RequestParam("file") MultipartFile uploadedFile) throws IOException {

		System.out.println("파일업로드");

		if(fileExtFilter.badFileExtIsReturnBoolean(uploadedFile) == true){ // 파일 확장자 필터링.
			System.out.println("id test >> "+id);
			if (uploadedFile.getSize() > 0 ) {
				FileAttachment file = new FileAttachment();
				file.setSponsorID(id); // 나중에 조인해서 변경해야함
				file.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
				file.setFilesize((int)uploadedFile.getSize());
				file.setData(uploadedFile.getBytes());

				fileAttachmentMapper.insert(file);
			}
		}

		return "redirect:/sponsor/detail.do?id="+id;
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


	@RequestMapping(value="sponsor/castList.do")
	public String castList(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,Model model)throws IOException{
		List<Sponsor> list=sponsorMapper.castBySponsorType2(startDate,endDate);


		int sponsorCount=0; // 후원인구분2별 출연내역 회원수
		int castCount=0; // 후원인구분2별 출연내역 출연수
		int sum=0;//후원인구분2별 출연내역 금액

		for(Sponsor i:list){
			sponsorCount=sponsorCount+i.getSponsorCount();
			castCount=castCount+i.getCastCount();
			sum=sum+i.getSum();
		}



		model.addAttribute("sponsorCount", sponsorCount);
		model.addAttribute("castCount",castCount);
		model.addAttribute("sum", sum);
		model.addAttribute("list", list);
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);


		return "sponsor/castHistory"; 
	}
	//회원구분	별 보고서
	@RequestMapping(value="/sponsor/castList.do",params="cmd=pdf" )
	public void sponsorTypeReport(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		List<Sponsor> list = sponsorMapper.castBySponsorType2(startDate,endDate);
		ReportBuilder reportBuilder = new ReportBuilder("chartBySponsorType",list,"chartBySponsorType.pdf",req,res);
		reportBuilder.build("pdf");
	}
	//회원구분	별 엑셀
	@RequestMapping(value="/sponsor/castList.do", params="cmd=xlsx" )
	public void sponsorTypeXlsx(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		List<Sponsor> list = sponsorMapper.castBySponsorType2(startDate,endDate);
		ReportBuilder reportBuilder = new ReportBuilder("chartBySponsorType",list,"chartBySponsorType.xlsx",req,res);
		reportBuilder.build("xlsx");
	}


	//DM발송 엑셀
	@RequestMapping(value="/sponsor/postSearch.do",params="cmd=xlsx" )
	public void excelDMReport(Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		List<Sponsor> list = sponsorMapper.excelDM(pagination);
		String temp,address,postCode;
		int middle;
		for (Sponsor i : list) {
			if(i.getMailTo()==0){
				temp=i.getHomeAddress();
				String[] home=temp.split("\\*");
				address=home[0]+home[1];
				postCode=home[2];
				i.setAddress(address);
				i.setPostCode(postCode);
			}
			if(i.getMailTo()==1){
				temp=i.getOfficeAddress();
				String[] office=temp.split("\\*");
				address=office[0]+office[1];
				postCode=office[2];

				i.setAddress(address);
				i.setPostCode(postCode);
			}
		}

		ReportBuilder reportBuilder = new ReportBuilder("sendDM", list, "sendDM.xlsx",req,res);
		reportBuilder.build("xlsx");
	}
	//후원인목록
	@RequestMapping(value="/sponsor/sponsor_m.do", params="cmd=xlsx" )
	public void sponsorList(Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		List<Sponsor> list = sponsorMapper.sponsorListExcel(pagination);
		ReportBuilder reportBuilder = new ReportBuilder("sponsorList", list, "sponsorList.xlsx",req,res);
		reportBuilder.build("xlsx");
	}
}

