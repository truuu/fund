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
		model.addAttribute("list", commitmentMapper.selectBySponsorID(15)); // 12 test
		String name="정기 납입방법";
		model.addAttribute("paymentMethodList",codeMapper.selectByPaymentMethod(name));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String bank="은행";
		model.addAttribute("bankList",codeMapper.selectByBank(bank));
		model.addAttribute("sponsorID",15);//SPONSORID 12번 TEST 학교서버는 90번으로


		return "sponsor/commitment";
	}

	/*commitment insert*/
	@RequestMapping(value="/sponsor/commitment.do", method=RequestMethod.POST, params="cmd=create")
	public String commitment(Model model,CommitmentCreate commitmentCreate){
		Commitment commitment = new Commitment();  //약정 
		System.out.println("1");
		//commitmentMapper.selectCountCommitment(commitmentCreate.getSponsorID());  // 해당 후원자의 약정 갯수 구하기
		System.out.println("2");
		commitment.setStartDate(commitmentCreate.getCommitmentStartDate());
		System.out.println("3");
		commitment.setSponsorID(commitmentCreate.getSponsorID());
		System.out.println("4");
		commitment.setDonationPurposeID(commitmentCreate.getDonationPurposeID());
		System.out.println("5");
		commitment.setPaymentMethodID(commitmentCreate.getPaymentMethodID());
		System.out.println("6");
		commitment.setEtc(commitmentCreate.getCommitmentEtc());
		System.out.println("7");
		//Date date= new Date(); 
		////date = (Date)commitmentCreate.getCommitmentDate();
		commitment.setCommitmentDate(commitmentCreate.getCommitmentDate());
		System.out.println("8");
		commitment.setStartDate(commitmentCreate.getCommitmentStartDate());
		System.out.println("9");
		commitment.setEndDate(commitmentCreate.getEndDate());
		System.out.println("10");

		commitmentMapper.insert(commitment);  // 약정 먼저 insert
		System.out.println("11");

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

		model.addAttribute("list", commitmentMapper.selectBySponsorID(15));  // 12번 test 나중에 바꿔야 함.
		return "redirect:/sponsor/commitment.do";
	}

	@RequestMapping(value="/sponsor/commitmentEdit.do", method=RequestMethod.GET)
	public String edit(Model model, @RequestParam("ID") int ID) {
		Commitment commitment = commitmentMapper.selectByID(ID); // 해당 약정 내용 가져옴
		CommitmentDetail commitmentDetail = commitmentDetailMapper.selectByCommitmentID2(ID); // 해당 약정 상세 가져옴

		model.addAttribute("commitment",commitment);
		model.addAttribute("commitmentDetail",commitmentDetail);
		
		return "sponsor/commitmentEdit";
	}// 수정할때 약정이랑 약정상세에서 가져온내용을 약정생성 모델에 다 넣고 submit








}