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
		model.addAttribute("list", commitmentMapper.selectBySponsorID(90)); // 12 test
		String name="정기 납입방법";
		model.addAttribute("paymentMethodList",codeMapper.selectByPaymentMethod(name));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String bank="은행";
		model.addAttribute("bankList",codeMapper.selectByBank(bank));
		model.addAttribute("sponsorID",90);//SPONSORID 12번 TEST
		
		
		return "sponsor/commitment";
	}

	/*commitment insert*/
	@RequestMapping(value="/sponsor/commitment.do", method=RequestMethod.POST, params="cmd=create")
		public String commitment(Model model,CommitmentCreate commitmentCreate){
		Commitment commitment = new Commitment();  //약정 
		
		commitmentMapper.selectCountCommitment(commitmentCreate.getSponsorID());  // 해당 후원자의 약정 갯수 구하기
		
		commitment.setStartDate(commitmentCreate.getCommitmentStartDate());
		commitment.setSponsorID(commitmentCreate.getSponsorID());
		commitment.setDonationPurposeID(commitmentCreate.getDonationPurposeID());
		commitment.setPaymentMethodID(commitmentCreate.getPaymentMethodID());
		commitment.setEtc(commitmentCreate.getCommitmentEtc());
		
		commitment.setCommitmentDate(commitmentCreate.getCommitmentDate());
		commitment.setStartDate(commitmentCreate.getCommitmentStartDate());
		commitment.setEndDate(commitmentCreate.getEndDate());
		System.out.println("1회:"+commitmentCreate.getAmountPerMonth());
		System.out.println("약정날짜"+commitmentCreate.getCommitmentDate());
		System.out.println("약정시작일"+commitmentCreate.getCommitmentStartDate());
		System.out.println("약정종료일"+commitmentCreate.getEndDate());
		
		commitmentMapper.insert(commitment);  // 약정 먼저 insert
		
		CommitmentDetail commitmentDetail = new CommitmentDetail();  // 약정상세 
		
		commitmentDetail.setCommitmentID(commitmentDetailMapper.selectCommitmentID());
		System.out.println(commitmentDetail.getCommitmentID());
		commitmentDetail.setAmountPerMonth(commitmentCreate.getAmountPerMonth());
		commitmentDetail.setBankID(commitmentCreate.getBankID());
		commitmentDetail.setPaymentDay(commitmentCreate.getPaymentDay());
		commitmentDetail.setAccountHolder(commitmentCreate.getAccountHolder());
		commitmentDetail.setAccountNo(commitmentCreate.getAccountNo());
		commitmentDetail.setStartDate(commitmentCreate.getCommitmentStartDate());
		
		commitmentDetailMapper.insert(commitmentDetail);  // 약정 상세 insert
		
		model.addAttribute("list", commitmentMapper.selectBySponsorID(90));  // 12번 test 나중에 바꿔야 함.
		return "sponsor/commitment";
		}
	
	
	
	
	
	
	
	


}