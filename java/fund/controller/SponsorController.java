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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fund.BaseController;
import fund.dto.*;
import fund.mapper.*;
import fund.service.ReportBuilder;
import fund.service.UserService;
import fund.service.AES128UtilService;
import fund.service.FileExtFilter;
import net.sf.jasperreports.engine.JRException;



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
		return "sponsor/sponsorManage";
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
			pagination.setRecordCount(sponsorMapper.searchCount(codeName));
			sponsorList=sponsorMapper.sponsorSearch(pagination);
		}
		model.addAttribute("list", sponsorList);
		return "sponsor/sponsorManage";
	}

	//신규
	@RequestMapping(value="/sponsor/sponsor.do",method=RequestMethod.GET)
	public String userRegister(Model model,Sponsor sponsor)throws Exception{
		Integer num=sponsorMapper.ceateNumber();
		String number;


		if(num==null){
			number="0001";
		}else{
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


		model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupID(1));  // 후원인구분1 목록
		model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupID(2));  // 후원인구분1 목록




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
		sponsor.setChurchID(sponsorMapper.selectChurchCode(sponsor));

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
			sponsorMapper.sponsorInsert(sponsor);
		}
		if(sponsor.getSort()==1){
			sponsorMapper.updateSponsor(sponsor);
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
			String homeRoadAddress=home[0];
			String homeDetailAddress=home[1];
			String homePostCode=home[2];
			sponsor.setHomeRoadAddress(homeRoadAddress);
			sponsor.setHomeDetailAddress(homeDetailAddress);
			sponsor.setHomePostCode(homePostCode);

		}
		if(!officeAddress.equals("")){
			String[] office=officeAddress.split("\\*");
			String officeRoadAddress=office[0];
			String officeDetailAddress=office[1];
			String officePostCode=office[2];
			sponsor.setOfficeRoadAddress(officeRoadAddress);
			sponsor.setOfficeDetailAddress(officeDetailAddress);
			sponsor.setOfficePostCode(officePostCode);
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

	@RequestMapping(value="/sponsor/insertIrrgularPayment.do", method=RequestMethod.GET)  // 비정기 납입 등록
	public String insertIrrgularPayment1(Model model,@RequestParam("id") int id) {
		Sponsor sponsor = sponsorMapper.selectBySponsorNo(id);
		model.addAttribute("sponsor", sponsor);
		model.addAttribute("sponsorID",id);
		model.addAttribute("sponsorNo",sponsorMapper.selectBySponsorNo2(id));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());

		return "sponsor/insertIrrgularPayment";
	}

	@RequestMapping(value="/sponsor/insertIrrgularPayment.do", method=RequestMethod.POST)
	public String insertIrrgularPayment2(@Valid IregularPayment iregularPayment,BindingResult result,Model model) throws Exception {
		if (result.hasErrors()) {
			// validation error!!
			return "redirect:/sponsor/insertIrrgularPayment.do?id="+iregularPayment.getSponsorID();
		}
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
	
	@RequestMapping(value="/sponsor/editIrrgularPayment.do", method=RequestMethod.GET)  // 비정기 납입 수정
	public String editIrrgularPayment(Model model,@RequestParam("id") int id,@RequestParam("sponsorID") int sponsorID,RedirectAttributes redirectAttributes) {
		Payment payment = paymentMapper.selectIrregular(id);
		model.addAttribute("payment",payment);
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		return "sponsor/editIrrgularPayment";
	}
	
	@RequestMapping(value="/sponsor/editIrrgularPayment.do", method=RequestMethod.POST)  // 비정기 납입 수정
	public String editIrrgularPayment(Payment payment, Model model) {
		paymentMapper.updateIrregular(payment);
		return "redirect:/sponsor/paymentList2.do?id="+payment.getSponsorID();
	}
	
	@RequestMapping(value="/sponsor/deleteIrrgularPayment.do")
	public String deleteIrrgularPayment(Model model, @RequestParam("id") int id,@RequestParam("sponsorID") int sponsorID) {
		System.out.println(id);
		paymentMapper.deleteIrregular(id);
		return "redirect:/sponsor/paymentList2.do?id="+sponsorID;
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

