package fund.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.Commitment;
import fund.dto.CommitmentCreate;
import fund.dto.CommitmentDetail;
import fund.mapper.CodeMapper;
import fund.mapper.CommitmentDetailMapper;
import fund.mapper.CommitmentMapper;
import fund.mapper.DonationPurposeMapper;

@Controller
public class CommitmentController {

	@Autowired CommitmentMapper commitmentMapper;
	@Autowired CommitmentDetailMapper commitmentDetailMapper;
	@Autowired CodeMapper codeMapper;
	@Autowired DonationPurposeMapper donationPurposeMapper;
	
	
	/*commitment list*/
	@RequestMapping(value="/sponsor/commitment.do", method=RequestMethod.GET)  
	public String commitment(Model model) {       // sponsor랑 합치면 @RequestParam추가하기
		model.addAttribute("list", commitmentMapper.selectBySponsorID(12)); // 12 test
		String name="정기 납입방법";
		model.addAttribute("paymentMethodList",codeMapper.selectByPaymentMethod(name));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String bank="은행";
		model.addAttribute("bankList",codeMapper.selectByBank(bank));
		model.addAttribute("sponsorID",12);//SPONSORID 12번 TEST
		return "sponsor/commitment";
	}

	/*commitment insert*/
	@RequestMapping(value="/sponsor/commitment.do", method=RequestMethod.POST, params="cmd=create")
		public String commitment(Model model,CommitmentCreate commitmentCreate){
		System.out.println("1");
		System.out.println(commitmentCreate.getAmountPerMonth());
		Commitment commitment = new Commitment();
		CommitmentDetail commitmentDetail = new CommitmentDetail();
		
		//int sponsorID;                 //  commitment에만
		//int donationPurposeID;
		//int paymentMethodID;
		//Date commitmentDate;
		//Date endDate;
		//commitment.setStartDate(commitmentCreate.getCommitmentStartDate());
		commitment.setSponsorID(commitmentCreate.getSponsorID());
		commitment.setDonationPurposeID(commitmentCreate.getDonationPurposeID());
		commitment.setPaymentMethodID(commitmentCreate.getPaymentMethodID());
		//commitment.setEtc(commitmentCreate.getCommitmentEtc());
		
		//commitmentMapper.insert(commitment);
		//commitmentDetail.setCommitmentID(commitment.getID());
		
		//int commitmentID;              //  commitmentDetail에만
		//int amountPerMonth;
		//int paymentDay;
		//int bankID;
		//String accountNo;
		//String accountHolder;
		
		int amount=Integer.parseInt(commitmentCreate.getAmountPerMonth());
		System.out.println("aaa");
		commitmentDetail.setAmountPerMonth(amount);
		/*
		commitmentDetail.setPaymentDay(commitmentCreate.getPaymentDay());
		commitmentDetail.setBankID(commitmentCreate.getBankID());
		commitmentDetail.setAccountNo(commitmentCreate.getAccountNo());
		commitmentDetail.setAccountHolder(commitmentCreate.getAccountHolder());
		
		commitmentDetailMapper.insert(commitmentDetail);*/
		
		model.addAttribute("list", commitmentMapper.selectBySponsorID(12));  // 12번 test 나중에 바꿔야 함.
		return "sponsor/commitment";
		}
	
	
	
	
	
	
	
	


}