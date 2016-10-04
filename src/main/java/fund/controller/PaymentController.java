package fund.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import fund.BaseController;
import fund.dto.Payment;
import fund.dto.PaymentRecordStats;
import fund.mapper.CodeMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.PaymentMapper;
import fund.mapper.SponsorMapper;
import fund.dto.EB13_CommitmentDetail;

@Controller
public class PaymentController extends BaseController{
	@Autowired PaymentMapper paymentMapper;
	@Autowired CodeMapper codeMapper;
	@Autowired DonationPurposeMapper donationPurposeMapper;
	@Autowired SponsorMapper sponsorMapper;
	
	@RequestMapping(value="/dataPrint/donationPurposeStats.do", method=RequestMethod.GET)  
	public String donationPurposeStats(Model model) {     

		return "dataPrint/donationPurposeStats";
	}

	@RequestMapping(value="/dataPrint/donationPurposeStats.do", method=RequestMethod.POST)  
	public String donationPurposeStats(Model model,@RequestParam String startDate,@RequestParam String endDate) {     
		List<Payment> list = paymentMapper.selectComparePaymentDate(startDate, endDate);
		int totalSponsor=0;
		int totalDonationPurpose=0;
		int totalSum=0;
		for(int i=0 ; i<list.size() ; i++){
			totalSponsor+=list.get(i).getCount1();
			totalDonationPurpose+=list.get(i).getCount2();
			totalSum+=list.get(i).getSum();


		}
		double totalPercent = 0.0;

		double result;
		double per;
		for(int i=0 ; i<list.size() ; i++){
			result= (double)list.get(i).getSum()/(double)totalSum *100;
			per=Double.parseDouble(String.format("%.2f",result));
			list.get(i).setPercent(per);
			totalPercent+=result;

		}
		model.addAttribute("totalSponsor",totalSponsor);
		model.addAttribute("totalDonationPurpose",totalDonationPurpose);
		model.addAttribute("totalSum",totalSum);
		model.addAttribute("totalPercent",totalPercent);

		model.addAttribute("list",list);
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);

		return "dataPrint/donationPurposeStats";
	}

	@RequestMapping(value="/dataPrint/paymentRecordStats.do", method=RequestMethod.GET) 
	public String paymentRecordStats(Model model) {     
		model.addAttribute("sponsorType2List",codeMapper.selectSponsorType2("후원인구분2"));
		model.addAttribute("churchList",codeMapper.selectChurch("소속교회"));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String name1="정기 납입방법";
		String name2="비정기 납입방법";
		model.addAttribute("paymentMethodList",codeMapper.selectAllPaymentMethod(name1,name2));
		return "dataPrint/paymentRecordStats";
	}

	@RequestMapping(value="/dataPrint/paymentRecordStats.do", method=RequestMethod.POST) //납입 내역 조회
	public String paymentRecordStats(Model model, PaymentRecordStats paymentRecordStats) {
		
		model.addAttribute("startDate",paymentRecordStats.getStartDate());
		model.addAttribute("endDate",paymentRecordStats.getEndDate());
		if(paymentRecordStats.getSrchType1()!=null){
			if(paymentRecordStats.getSrchType1()==1)
				model.addAttribute("gubun","정기");
			else if(paymentRecordStats.getSrchType1()==2)
				model.addAttribute("gubun","비정기");
		}
		if(paymentRecordStats.getSrchType2()!=null){
			model.addAttribute("donationPurpose",donationPurposeMapper.selectDonationPurpose2(paymentRecordStats.getSrchType2()));
			model.addAttribute("corporateName",donationPurposeMapper.selectCoporateName(paymentRecordStats.getSrchType2()));
		}
		if(paymentRecordStats.getSrchType3()!=null)	
			model.addAttribute("church",codeMapper.selectCodeName(paymentRecordStats.getSrchType3()));
		if(paymentRecordStats.getSrchType4()!=null)
			model.addAttribute("paymentMethod",codeMapper.selectCodeName(paymentRecordStats.getSrchType4()));
		if(paymentRecordStats.getSrchType5()!=null)
			model.addAttribute("sponsorType",codeMapper.selectCodeName(paymentRecordStats.getSrchType5()));
		if(paymentRecordStats.getSponsorName()!=null)
			model.addAttribute("sponsorName",paymentRecordStats.getSponsorName());
		
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = transFormat.format(date);
		model.addAttribute("time",date2);
		
		List<Payment> list = paymentMapper.selectPaymentRecord(paymentRecordStats);
		int total=0;
		int count=0;
		for(int i=0 ; i<list.size() ; i++){
			total+=list.get(i).getAmount();
			count++;
		}
		model.addAttribute("total",total);
		model.addAttribute("count",count);	
		model.addAttribute("list",list);
		
		return "dataPrint/paymentRecordStats";
	}
	
	@RequestMapping(value="/dataPrint/paymentTotalStats.do", method=RequestMethod.GET) // 납입 총계 조회
	public String paymentTotalStats(Model model) { 
		model.addAttribute("sponsorType2List",codeMapper.selectSponsorType2("후원인구분2"));
		model.addAttribute("churchList",codeMapper.selectChurch("소속교회"));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String name1="정기 납입방법";
		String name2="비정기 납입방법";
		model.addAttribute("paymentMethodList",codeMapper.selectAllPaymentMethod(name1,name2));
	
		return "dataPrint/paymentTotalStats";
	}
	
	@RequestMapping(value="/dataPrint/paymentTotalStats.do", method=RequestMethod.POST) 
	public String paymentTotalStats(Model model,PaymentRecordStats paymentRecordStats) { 
		System.out.println("기부목적번호"+paymentRecordStats.getSrchType2());
		System.out.println("후원인번호"+paymentRecordStats.getSrchType5());
		System.out.println("후원인이름"+paymentRecordStats.getSponsorName());
		model.addAttribute("startDate",paymentRecordStats.getStartDate());
		model.addAttribute("endDate",paymentRecordStats.getEndDate());
		if(paymentRecordStats.getSrchType1()!=0){
			if(paymentRecordStats.getSrchType1()==1)
				model.addAttribute("gubun","정기");
			
			else if(paymentRecordStats.getSrchType1()==2)
				model.addAttribute("gubun","비정기");
		}
		if(paymentRecordStats.getSrchType2()!=null){
			model.addAttribute("donationPurpose",donationPurposeMapper.selectDonationPurpose2(paymentRecordStats.getSrchType2()));
			model.addAttribute("corporateName",donationPurposeMapper.selectCoporateName(paymentRecordStats.getSrchType2()));
		}
		if(paymentRecordStats.getSrchType3()!=null)	
			model.addAttribute("church",codeMapper.selectCodeName(paymentRecordStats.getSrchType3()));
		if(paymentRecordStats.getSrchType4()!=null)
			model.addAttribute("paymentMethod",codeMapper.selectCodeName(paymentRecordStats.getSrchType4()));
		if(paymentRecordStats.getSrchType5()!=null)
			model.addAttribute("sponsorType",codeMapper.selectCodeName(paymentRecordStats.getSrchType5()));
	
		if(paymentRecordStats.getSponsorName()!=null)
			model.addAttribute("sponsorName",paymentRecordStats.getSponsorName());
		
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = transFormat.format(date);
		model.addAttribute("time",date2);
		List<Payment> list = paymentMapper.selectPaymentTotal(paymentRecordStats);
		int total=0;
		int count=0;
		int total2=0;
		for(int i=0 ; i<list.size() ; i++){
			total+=list.get(i).getTotalSum();
			total2+=list.get(i).getTotalCount();
			count++;
		}
		model.addAttribute("total",total);
		model.addAttribute("total2",total2);
		model.addAttribute("count",count);	
		model.addAttribute("list",list);
		
		return "dataPrint/paymentTotalStats";
	}
	

}
