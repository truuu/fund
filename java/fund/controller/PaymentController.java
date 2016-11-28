package fund.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import fund.BaseController;
import fund.dto.Payment;
import fund.dto.PaymentRecordStats;
import fund.dto.PaymentSummary1;
import fund.mapper.CodeMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.PaymentMapper;
import fund.mapper.SponsorMapper;
import fund.service.ReportBuilder;
import fund.service.ReportBuilder3;
import net.sf.jasperreports.engine.JRException;
import fund.dto.EB13_CommitmentDetail;

@Controller
public class PaymentController extends BaseController{
	@Autowired PaymentMapper paymentMapper;
	@Autowired CodeMapper codeMapper;
	@Autowired DonationPurposeMapper donationPurposeMapper;
	@Autowired SponsorMapper sponsorMapper;
	@Autowired SimpleDriverDataSource dataSource;

	@RequestMapping(value="/dataPrint/donationPurposeStats.do", method=RequestMethod.GET)  
	public String donationPurposeStats(Model model) {     

		return "dataPrint/donationPurposeStats";
	}

	@RequestMapping(value="/dataPrint/donationPurposeStats.do",method=RequestMethod.POST)  
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

	//기부목적별 보고서
	@RequestMapping(value="/dataPrint/donationPurposeStats.do", method=RequestMethod.POST, params="cmd=pdf")
	public void donationPurposeStatsReport(Model model,@RequestParam String startDate,@RequestParam String endDate,HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		List<Payment> list = paymentMapper.selectComparePaymentDate(startDate, endDate);
		ReportBuilder reportBuilder = new ReportBuilder("chartBydonationPurpose",list,"chartBydonationPurpose.pdf",req,res);
		reportBuilder.build("pdf");
	}

	//기부목적별 엑셀
	@RequestMapping(value="/dataPrint/donationPurposeStats.do", method=RequestMethod.POST, params="cmd=xlsx")
	public void donationPurposeStatsXlsx(Model model,@RequestParam String startDate,@RequestParam String endDate,HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
		List<Payment> list = paymentMapper.selectComparePaymentDate(startDate, endDate);
		ReportBuilder reportBuilder = new ReportBuilder("chartBydonationPurpose",list,"chartBydonationPurpose.xlsx",req,res);
		reportBuilder.build("xlsx");
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

	@RequestMapping(value="/dataPrint/paymentRecordStats.do",method=RequestMethod.POST) //납입 내역 조회
	public String paymentRecordStats(Model model,PaymentRecordStats paymentRecordStats) {
		
		model.addAttribute("sponsorType2List",codeMapper.selectSponsorType2("후원인구분2"));
		model.addAttribute("churchList",codeMapper.selectChurch("소속교회"));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String name1="정기 납입방법";
		String name2="비정기 납입방법";
		model.addAttribute("paymentMethodList",codeMapper.selectAllPaymentMethod(name1,name2));

		model.addAttribute("startDate",paymentRecordStats.getStartDate());
		model.addAttribute("endDate",paymentRecordStats.getEndDate());
		model.addAttribute("gu1",paymentRecordStats.getSrchType1());
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
		if(paymentRecordStats.getSrchType3()!=null)	{
			model.addAttribute("church",codeMapper.selectCodeName(paymentRecordStats.getSrchType3()));
			model.addAttribute("churchID",paymentRecordStats.getSrchType3());
		}
		if(paymentRecordStats.getSrchType4()!=null){
			model.addAttribute("paymentMethod",codeMapper.selectCodeName(paymentRecordStats.getSrchType4()));
			model.addAttribute("paymentMethodID",paymentRecordStats.getSrchType4());
		}
		if(paymentRecordStats.getSrchType5()!=null){
			model.addAttribute("sponsorType",codeMapper.selectCodeName(paymentRecordStats.getSrchType5()));
			model.addAttribute("sponsorTypeID",paymentRecordStats.getSrchType5());
		}
		if(paymentRecordStats.getSponsorName()!=null){
			model.addAttribute("sponsorName",paymentRecordStats.getSponsorName());

		}

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

	@RequestMapping(value="/dataPrint/paymentRecordStats.do",method=RequestMethod.POST, params="cmd=report")
	public void reportPaymentRecordStats(Model model,
			HttpServletRequest request, HttpServletResponse response, PaymentRecordStats paymentRecordStats) throws JRException, IOException, SQLException {
		String[] condition = new String[9];
		if(paymentRecordStats.getSrchType1()!=null){
			if(paymentRecordStats.getSrchType1()==1)
				condition[0]="정기";
			else if(paymentRecordStats.getSrchType1()==2)
				condition[0]="비정기";
		}
		if(paymentRecordStats.getSrchType2()!=null){
			condition[3]=donationPurposeMapper.selectDonationPurpose2(paymentRecordStats.getSrchType2());
			condition[5]=donationPurposeMapper.selectCoporateName(paymentRecordStats.getSrchType2());
		}
		if(paymentRecordStats.getSrchType3()!=null)
			condition[4]=codeMapper.selectCodeName(paymentRecordStats.getSrchType3());		
		if(paymentRecordStats.getSrchType4()!=null)
			condition[7]=codeMapper.selectCodeName(paymentRecordStats.getSrchType4());
		if(paymentRecordStats.getSrchType5()!=null)
			condition[6]=codeMapper.selectCodeName(paymentRecordStats.getSrchType5());

		condition[1]=paymentRecordStats.getStartDate();
		condition[2]=paymentRecordStats.getEndDate();
		condition[8]=paymentRecordStats.getSponsorName();
		List<Payment> list = paymentMapper.selectPaymentRecord(paymentRecordStats);
		ReportBuilder3 reportBuilder = new ReportBuilder3("payment1_list",list,"paymentRecord_List.pdf",condition,request,response);
		reportBuilder.build("pdf");

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
		model.addAttribute("sponsorType2List",codeMapper.selectSponsorType2("후원인구분2"));
		model.addAttribute("churchList",codeMapper.selectChurch("소속교회"));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String name1="정기 납입방법";
		String name2="비정기 납입방법";
		model.addAttribute("paymentMethodList",codeMapper.selectAllPaymentMethod(name1,name2));

		model.addAttribute("startDate",paymentRecordStats.getStartDate());
		model.addAttribute("endDate",paymentRecordStats.getEndDate());
		model.addAttribute("gu1",paymentRecordStats.getSrchType1());

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
		if(paymentRecordStats.getSrchType3()!=null)	{
			model.addAttribute("church",codeMapper.selectCodeName(paymentRecordStats.getSrchType3()));
			model.addAttribute("churchID",paymentRecordStats.getSrchType3());
		}
		if(paymentRecordStats.getSrchType4()!=null){
			model.addAttribute("paymentMethod",codeMapper.selectCodeName(paymentRecordStats.getSrchType4()));
			model.addAttribute("paymentMethodID",paymentRecordStats.getSrchType4());
		}
		if(paymentRecordStats.getSrchType5()!=null){
			model.addAttribute("sponsorType",codeMapper.selectCodeName(paymentRecordStats.getSrchType5()));
			model.addAttribute("sponsorTypeID",paymentRecordStats.getSrchType5());
		}

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

	@RequestMapping(value="/dataPrint/paymentTotalStats.do",method=RequestMethod.POST, params="cmd=report")
	public void reportPaymentTotalStats(Model model,
			HttpServletRequest request, HttpServletResponse response, PaymentRecordStats paymentRecordStats) throws JRException, IOException, SQLException {
		String[] condition = new String[9];
		if(paymentRecordStats.getSrchType1()!=null){
			if(paymentRecordStats.getSrchType1()==1)
				condition[0]="정기";
			else if(paymentRecordStats.getSrchType1()==2)
				condition[0]="비정기";
		}
		if(paymentRecordStats.getSrchType2()!=null){
			condition[3]=donationPurposeMapper.selectDonationPurpose2(paymentRecordStats.getSrchType2());
			condition[5]=donationPurposeMapper.selectCoporateName(paymentRecordStats.getSrchType2());
		}
		if(paymentRecordStats.getSrchType3()!=null)
			condition[4]=codeMapper.selectCodeName(paymentRecordStats.getSrchType3());
		if(paymentRecordStats.getSrchType4()!=null)
			condition[7]=codeMapper.selectCodeName(paymentRecordStats.getSrchType4());
		if(paymentRecordStats.getSrchType5()!=null)
			condition[6]=codeMapper.selectCodeName(paymentRecordStats.getSrchType5());
		condition[1]=paymentRecordStats.getStartDate();
		condition[2]=paymentRecordStats.getEndDate();
		condition[8]=paymentRecordStats.getSponsorName();
		List<Payment> list = paymentMapper.selectPaymentTotal(paymentRecordStats);
		ReportBuilder3 reportBuilder = new ReportBuilder3("payment2_list",list,"paymentTotal_List.pdf",condition,request,response);
		reportBuilder.build("pdf");

	}

	@RequestMapping(value="/dataPrint/monthPerDonationPurposePayment.do", method=RequestMethod.GET) // 월별 기부목적별 납입현황
	public String monthPerDonationPurposePayment(Model model) { 
		
		return "dataPrint/monthPerDonationPurposePayment";
	}
	
	@RequestMapping(value="/dataPrint/monthPerDonationPurposePayment.do",method=RequestMethod.POST) 
	public String monthPerDonationPurposePayment1(@RequestParam String startDate,@RequestParam String endDate,Model model) { 
		
		List<PaymentSummary1> list = paymentMapper.selectMonthDonationPurposePayment(startDate, endDate);
		
		String corp = list.get(0).getCorporate();
		for(int i=0 ; i<list.size() ; i++){
			if(i>0){
				if(corp.equals(list.get(i).getCorporate()))
					list.get(i).setCorporate(" ");
				else
					corp=list.get(i).getCorporate();
			}
		}
		
		String organ = list.get(0).getOrganization();
		for(int i=0 ; i<list.size() ; i++){
			if(i>0){
				if(organ.equals(list.get(i).getOrganization()))
					list.get(i).setOrganization(" ");
				else
					organ=list.get(i).getOrganization();
			}
		}
		
		model.addAttribute("list",list);
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		
		String s1 = startDate.substring(5, 7);
		String s2 = endDate.substring(5, 7);
		
		int from = Integer.parseInt(s1);
		int to = Integer.parseInt(s2);
		
		model.addAttribute("from",from);
		model.addAttribute("to",to);
		
		
		
		return "dataPrint/monthPerDonationPurposePayment";
	}

}
