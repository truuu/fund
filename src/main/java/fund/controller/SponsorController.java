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
import org.springframework.dao.DataIntegrityViolationException;
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

import java.util.*;


@Controller
public class SponsorController extends BaseController{
	@Autowired SponsorMapper sponsorMapper;
	@Autowired FileAttachmentMapper fileAttachmentMapper;
	@Autowired PaymentMapper paymentMapper;
	@Autowired CodeMapper codeMapper;
	@Autowired DonationPurposeMapper donationPurposeMapper;
	@Autowired AES128UtilService cipherService; //�뼇諛⑺뼢 �븫�샇�솕 �꽌鍮꾩뒪
	@Autowired FileExtFilter fileExtFilter;


	//�썑�썝�옄愿�由� 湲곕낯�럹�씠吏�
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
			System.out.println("�뾾�쓬 寃��궗 codeName");
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
			System.out.println("�뾽�쓬 寃��궗 ");
			sponsor=new ArrayList<Sponsor>();
			return sponsor;
		}else{
			System.out.println("媛� �엳�떎  ");
			return sponsor;
		}


	}


	//�쉶�썝愿�由� 寃��깋湲곕뒫 
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

	//�떊洹�
	@RequestMapping(value="/sponsor/sponsor.do",method=RequestMethod.GET)
	public String userRegister(Model model,Sponsor sponsor)throws Exception{
		Calendar oCalendar = Calendar.getInstance( );  // �쁽�옱 �궇吏�/�떆媛� �벑�쓽 媛곸쥌 �젙蹂� �뼸湲�
		Integer num=sponsorMapper.ceateNumber();
		String[] year=sponsorMapper.ceateYear().split("-");
		int preYear=oCalendar.get(Calendar.YEAR);
		String number;
		if(num==null){//�썑�썝�옄瑜� 泥섏쓬 �벑濡앺븷�븣 0001 珥덇린�솕 
			number="0001";
			String sponsorNo=preYear+"-"+number;
			sponsor.setSponsorNo(sponsorNo);
		}else{
			if(Integer.parseInt(year[0])==preYear){//�뀈�룄媛� 蹂��븯吏� �븡�븯�쓣�븣 
				System.out.println("�뀈�룄 洹몃�濡� !!!!!!");
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
			if(Integer.parseInt(year[0])!=preYear){ // �뀈�룄媛� 蹂��뻽�쓣�븣 �썑�썝�옄 踰덊샇 �깮�꽦
				System.out.println("�뀈�룄 蹂��븿 !!!!!!!");
				number="0001";
				String sponsorNo=preYear+"-"+number;
				sponsor.setSponsorNo(sponsorNo);
			}
		}

		//泥⑤��뙆�씪由ъ뒪�듃 �엫�떆�뀒�뒪�듃 -> �꽭�뀡媛믪쑝濡� 諛⑹떇�쑝濡� 諛붽씀�뼱�빞�븿 
		//List<FileAttachment> list=fileAttachmentMapper.selectByArticleId(100);
		//model.addAttribute("files", fileAttachmentMapper.selectByArticleId(100));
		model.addAttribute("sponsor",sponsor);


		model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupID(1));  // �썑�썝�씤援щ텇1 紐⑸줉
		model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupID(2));  // �썑�썝�씤援щ텇1 紐⑸줉



		return "sponsor/sponsor";
	}

	//�쉶�썝�엯�젰 insert
	@RequestMapping(value="/sponsor/sponsorInsert.do",method=RequestMethod.POST)
	public String sponsorRegister(HttpServletRequest request,Model model,@Valid Sponsor sponsor,BindingResult result)throws Exception{


		if (result.hasErrors()) {
			// �뿉�윭 異쒕젰
			model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupID(1));  // �썑�썝�씤援щ텇1 紐⑸줉
			model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupID(2));  // �썑�썝�씤援щ텇2 紐⑸줉
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
		sponsor.setJuminNo(encryption); // �븫�샇�솕 �썑 ���옣


		sponsor.setHomeAddress(homeAddress);
		sponsor.setOfficeAddress(officeAddress);


		if(sponsor.getSort()==0){
			if(!sponsor.getChurch().equals("")){//�냼�냽援먰쉶瑜� �엯�젰�뻽�쓣 寃쎌슦
				sponsorMapper.sponsorInsert(sponsor);
			}
			if(sponsor.getChurch().equals("")){ //�냼�냽援먰쉶瑜� �엯�젰�븯吏� �븡�� 寃쎌슦 
				sponsorMapper.sponsorInsert2(sponsor);
			}
		}
		if(sponsor.getSort()==1){
			if(!sponsor.getChurch().equals("")){//�냼�냽援먰쉶瑜� 蹂�寃쏀븳 寃쎌슦
				sponsorMapper.updateSponsor(sponsor);
			}
			if(sponsor.getChurch().equals("")){//�냼�냽援먰쉶瑜� 吏��슫  寃쎌슦
				sponsorMapper.updateSponsor2(sponsor);
			}

		}


		return "redirect:/sponsor/sponsor_m.do";
	}


	//�썑�썝�옄 �젙蹂대낫湲�
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
			sponsor.setJuminNo(decoding);// 蹂듯샇�솕 �썑 ���옣
		}

	



		model.addAttribute("sponsor", sponsor);
		int sponsorID=sponsor.getId();
		model.addAttribute("sponsorType1List", codeMapper.selectByCodeGroupID(1));  // �썑�썝�씤援щ텇1 紐⑸줉
		model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupID(2));  // �썑�썝�씤援щ텇1 紐⑸줉
		model.addAttribute("files", file);//泥⑤��뙆�씪

		return "sponsor/sponsor";
	}

	@RequestMapping(value="/sponsor/paymentList.do",method=RequestMethod.GET)  // �젙湲� �궔�엯愿�由�
	public String paymentList(@RequestParam("id") int id, Model model){
		Sponsor sponsor=sponsorMapper.selectBySponsorNo(id);
		model.addAttribute("sponsor", sponsor);
		List<Payment> paymentList = paymentMapper.selectPaymentRegular(id);
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("sponsorID",id);
		model.addAttribute("sponsorNo",sponsorMapper.selectBySponsorNo2(id));
		return "sponsor/paymentList";
	}

	@RequestMapping(value="/sponsor/paymentList2.do", method=RequestMethod.GET)  // 鍮꾩젙湲� �궔�엯愿�由�
	public String paymentList2(Model model, @RequestParam("id") int id){
		Sponsor sponsor=sponsorMapper.selectBySponsorNo(id);
		model.addAttribute("sponsor", sponsor);
		List<Payment> paymentList2 = paymentMapper.selectPaymentIrregular(id);
		model.addAttribute("paymentList2", paymentList2);
		model.addAttribute("sponsorID",id);
		model.addAttribute("sponsorNo",sponsorMapper.selectBySponsorNo2(id));
		return "sponsor/paymentList2";
	}

	@RequestMapping(value="/sponsor/insertIrrgularPayment.do", method=RequestMethod.GET)  // 鍮꾩젙湲� �궔�엯�벑濡�
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

	//�썑�썝�옄 �젙蹂� �궘�젣�븯湲�
	@RequestMapping(value="/sponsor/delete.do",method=RequestMethod.GET)
	public String sponsorDelete(@RequestParam("id")int id,Model model,RedirectAttributes redirectAttributes){
		System.out.println("sponsor test >> "+id);
		 try{
			 sponsorMapper.removeSponsor(id);
	      }
	      catch(DataIntegrityViolationException e){
	         redirectAttributes.addFlashAttribute("errorMessage1","�빐�떦 �빟�젙�뿉 �궔�엯�궡�뿭�씠�굹 �빟�젙�긽�꽭媛� �엳�뼱 �궘�젣�븷 �닔 �뾾�뒿�땲�떎.");
	         return "redirect:/sponsor/detail.do?id="+id; 
	      }
	   

		
		//sponsorMapper.removeSponsor(sponsorNo);
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




	//�냼�냽援먰샇李얘린 �옄�룞�셿�꽦
	@RequestMapping(value="/sponsor/autoList.do", produces="application/json;charset=UTF-8", method=RequestMethod.GET)
	public @ResponseBody List<Code> autoList(HttpServletRequest request,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String input = request.getParameter("input");
		List<Code> resultList=sponsorMapper.selectAuto(input);
		return resultList;
	}



	//湲곌컙湲곗��쑝濡� DM諛쒖넚 由ъ뒪�듃 李얘린
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


	//�뙆�씪�뾽濡쒕뱶
	@RequestMapping(value="/sponsor/upload.do", method=RequestMethod.POST)
	public String fileUpload(Model model,@RequestParam("id") int id,@RequestParam("file") MultipartFile uploadedFile) throws IOException {

		System.out.println("�뙆�씪�뾽濡쒕뱶");

		if(fileExtFilter.badFileExtIsReturnBoolean(uploadedFile) == true){ // �뙆�씪 �솗�옣�옄 �븘�꽣留�.
			System.out.println("id test >> "+id);
			if (uploadedFile.getSize() > 0 ) {
				FileAttachment file = new FileAttachment();
				file.setSponsorID(id); // �굹以묒뿉 議곗씤�빐�꽌 蹂�寃쏀빐�빞�븿
				file.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
				file.setFilesize((int)uploadedFile.getSize());
				file.setData(uploadedFile.getBytes());

				fileAttachmentMapper.insert(file);
			}
		}

		return "redirect:/sponsor/detail.do?id="+id;
	}


	//�뙆�씪 �떎�슫濡쒕뱶
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

	//�뙆�씪�궘�젣
	@RequestMapping(value="sponsor/fileDelete.do",method=RequestMethod.GET)
	public String fileDelete(@RequestParam("id") int id)throws IOException{
		fileAttachmentMapper.deleteById(id);
		return "redirect:/sponsor/sponsor.do";


	}




	//- �썑�썝�씤援щ텇2蹂� 異쒖뿰�궡�뿭 -

	@RequestMapping(value="sponsor/cast.do",method=RequestMethod.GET)
	public String cast(Model model)throws IOException{
		int sum=0;//�썑�썝�씤援щ텇2蹂� 異쒖뿰�궡�뿭 湲덉븸
		model.addAttribute("sum", sum);
		return "sponsor/castHistory"; 
	}


	@RequestMapping(value="sponsor/castList.do")
	public String castList(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,Model model)throws IOException{
		List<Sponsor> list=sponsorMapper.castBySponsorType2(startDate,endDate);


		int sponsorCount=0; // �썑�썝�씤援щ텇2蹂� 異쒖뿰�궡�뿭 �쉶�썝�닔
		int castCount=0; // �썑�썝�씤援щ텇2蹂� 異쒖뿰�궡�뿭 異쒖뿰�닔
		int sum=0;//�썑�썝�씤援щ텇2蹂� 異쒖뿰�궡�뿭 湲덉븸

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
	//�쉶�썝援щ텇	蹂� 蹂닿퀬�꽌
	@RequestMapping(value="/sponsor/castList.do",params="cmd=pdf" )
	public void sponsorTypeReport(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		List<Sponsor> list = sponsorMapper.castBySponsorType2(startDate,endDate);
		ReportBuilder reportBuilder = new ReportBuilder("chartBySponsorType",list,"chartBySponsorType.pdf",req,res);
		reportBuilder.build("pdf");
	}
	//�쉶�썝援щ텇	蹂� �뿊��
	@RequestMapping(value="/sponsor/castList.do", params="cmd=xlsx" )
	public void sponsorTypeXlsx(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		List<Sponsor> list = sponsorMapper.castBySponsorType2(startDate,endDate);
		ReportBuilder reportBuilder = new ReportBuilder("chartBySponsorType",list,"chartBySponsorType.xlsx",req,res);
		reportBuilder.build("xlsx");
	}


	//DM諛쒖넚 �뿊��
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
	//�썑�썝�씤紐⑸줉
	@RequestMapping(value="/sponsor/sponsor_m.do", params="cmd=xlsx" )
	public void sponsorList(Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		List<Sponsor> list = sponsorMapper.sponsorListExcel(pagination);
		ReportBuilder reportBuilder = new ReportBuilder("sponsorList", list, "sponsorList.xlsx",req,res);
		reportBuilder.build("xlsx");
	}
}

