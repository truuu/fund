package fund.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fund.BaseController;
import fund.dto.Code;
import fund.dto.FileAttachment;
import fund.dto.IregularPayment;
import fund.dto.Pagination;
import fund.dto.PaginationSponsor;
import fund.dto.Payment;
import fund.dto.Sponsor;
import fund.mapper.CodeMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.FileAttachmentMapper;
import fund.mapper.PaymentMapper;
import fund.mapper.SponsorMapper;
import fund.service.AES128UtilService;
import fund.service.FileExtFilter;
import fund.service.ReportBuilder;
import fund.service.UserService;
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


	@RequestMapping("/sponsor/list.do")
	public String list(Model model, @ModelAttribute("pagination") PaginationSponsor pagination) {
	    List<Sponsor> list = sponsorMapper.selectPage(pagination);
	    pagination.setRecordCount(sponsorMapper.selectCount(pagination));
	    List<Code> sponsorType1Codes = codeMapper.selectByCodeGroupID(1);
        List<Code> sponsorType2Codes = codeMapper.selectByCodeGroupID(2);
	    model.addAttribute("list", list);
	    model.addAttribute("sponsorType1Codes", sponsorType1Codes);
        model.addAttribute("sponsorType2Codes", sponsorType2Codes);
	    return "sponsor/list";
	}
	
	//codeName check 
	@RequestMapping(value="/sponsor/codeNameCheck.do",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> codeNameCheck(@RequestParam("codeName")String codeName,HttpServletRequest request,HttpServletResponse response)throws Exception{
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Sponsor> sponsor=sponsorMapper.codeNameCheck(codeName);
		if(sponsor.isEmpty()){
			sponsor=new ArrayList<Sponsor>();
		}
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
			sponsor=new ArrayList<Sponsor>();
			return sponsor;
		}else{
			return sponsor;
		}
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
	
		System.out.println("controller mail >> "+sponsor.getMailTo());//int		
		System.out.println("controller post >> "+sponsor.isMailReceiving());// boolean 발송여부

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
	
		}
		
		if(!sponsor.getJuminNo().equals("")){
			String decoding=cipherService.decAES(sponsor.getJuminNo());//jumin decoding
			sponsor.setJuminNo(decoding);// 복호화 후 저장
		}
		model.addAttribute("sponsor", sponsor);
		int sponsorID=sponsor.getId();
		model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupID(1));  // 후원인구분1 목록
		model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupID(2));  // 후원인구분1 목록
		model.addAttribute("files", file);//첨부파일

		return "sponsor/edit";
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
	public String sponsorDelete(@RequestParam("id")int id,Model model,RedirectAttributes redirectAttributes){		
        try{
        	 sponsorMapper.removeSponsor(id);
        }
        catch(DataIntegrityViolationException e){
             redirectAttributes.addFlashAttribute("errorMsg","해당 약정에 납입내역이나 약정상세가 있어 삭제할 수 없습니다.");
             return "redirect:/sponsor/detail.do?id="+id; 
        }
		//sponsorMapper.removeSponsor(sponsorNo);
		return "redirect:/sponsor/list.do";
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
	public String fileUpload(Model model,@RequestParam("sid") int sid, @RequestParam("file") MultipartFile uploadedFile) throws IOException {
		if (uploadedFile.getSize() > 0 ) {
			FileAttachment file = new FileAttachment();
			file.setSponsorID(sid); // 나중에 조인해서 변경해야함
			file.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
			file.setFilesize((int)uploadedFile.getSize());			file.setData(uploadedFile.getBytes());
			fileAttachmentMapper.insert(file);
		}
		return "redirect:/sponsor/detail.do?id=" + sid;
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
	public String fileDelete(@RequestParam("id") int id, @RequestParam("sid") int sid)throws IOException{
		fileAttachmentMapper.deleteById(id);
		return "redirect:/sponsor/detail.do?id=" + sid;
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
		
		double result; //  반올림되지 않은 % information
		double persent;//% information 
		double totalPercent = 0.0; // total % information

		for(Sponsor i:list){
			sponsorCount=sponsorCount+i.getSponsorCount();
			castCount=castCount+i.getCastCount();
			sum=sum+i.getSum();
		}		
		for(int i=0 ; i<list.size() ; i++){
			result=(double)list.get(i).getSum()/(double)sum *100;
			persent=Double.parseDouble(String.format("%.2f",result));
			list.get(i).setPersent(persent);
			totalPercent+=result;
		
		}
		model.addAttribute("totalPercent",totalPercent);
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
	
	@RequestMapping("/sponsor/encryptNo.do") 
	public String encryptNo(Model model) throws Exception {
	    System.out.println("a");
	    List<Sponsor> list = sponsorMapper.selectNotEncrypted();
        System.out.println(list.size());
	    for (Sponsor sponsor : list) {
	        String s = sponsor.getJuminNo();
	        s = cipherService.encAES(s);
	        sponsor.setJuminNo(s);
	        sponsorMapper.updateJuminNo(sponsor);
	        System.out.println(sponsor.getId() + " " + s);
	    }
	    model.addAttribute("list", list);
	    return "sponsor/encryptNo";
	}
}

