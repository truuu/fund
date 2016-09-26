package fund.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
		model.addAttribute("list", commitmentMapper.selectBySponsorID(106)); // 12 test
		String name="정기 납입방법";
		model.addAttribute("paymentMethodList",codeMapper.selectByPaymentMethod(name));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String bank="은행";
		model.addAttribute("bankList",codeMapper.selectByBank(bank));
		model.addAttribute("sponsorID",106);//SPONSORID 12번 TEST 학교서버는 90번으로

		return "sponsor/commitment";
	}

	/*commitment insert*/
	@RequestMapping(value="/sponsor/commitment.do", method=RequestMethod.POST, params="cmd=create")
	public String commitment(Model model,CommitmentCreate commitmentCreate) throws ParseException{
		Commitment commitment = new Commitment();  //약정 

		//commitmentMapper.selectCountCommitment(commitmentCreate.getSponsorID());  // 해당 후원자의 약정 갯수 구하기
		commitment.setStartDate(commitmentCreate.getCommitmentStartDate());
		commitment.setSponsorID(commitmentCreate.getSponsorID());
		commitment.setDonationPurposeID(commitmentCreate.getDonationPurposeID());
		commitment.setPaymentMethodID(commitmentCreate.getPaymentMethodID());
		commitment.setEtc(commitmentCreate.getCommitmentEtc());
		commitment.setCommitmentDate(commitmentCreate.getCommitmentDate());

		commitment.setStartDate(commitmentCreate.getCommitmentStartDate());

		if(commitmentCreate.getEndDate()!=null)
		{
			commitment.setEndDate(commitmentCreate.getEndDate());
		}
		if(commitmentCreate.getEndDate()==null || commitmentCreate.getEndDate()=="" ){
			commitment.setEndDate(null);
		}

		commitmentMapper.insert(commitment);  // 약정 먼저 insert

		CommitmentDetail commitmentDetail = new CommitmentDetail();  // 약정상세 

		commitmentDetail.setCommitmentID(commitmentDetailMapper.selectCommitmentID());
		commitmentDetail.setAmountPerMonth(commitmentCreate.getAmountPerMonth());
		commitmentDetail.setBankID(commitmentCreate.getBankID());
		commitmentDetail.setPaymentDay(commitmentCreate.getPaymentDay());
		commitmentDetail.setAccountHolder(commitmentCreate.getAccountHolder());
		commitmentDetail.setAccountNo(commitmentCreate.getAccountNo());
		commitmentDetail.setStartDate(commitmentCreate.getCommitmentStartDate());

		commitmentDetailMapper.insert(commitmentDetail);  // 약정 상세 insert

		model.addAttribute("list", commitmentMapper.selectBySponsorID(106));  // 12번 test 나중에 바꿔야 함.
		return "redirect:/sponsor/commitment.do";
	}

	@RequestMapping(value="/sponsor/commitmentEdit.do", method=RequestMethod.GET)  // 약정수정페이지
	public String commitmentEdit(Model model, @RequestParam("ID") int ID) throws ParseException {
		Commitment commitment = commitmentMapper.selectByID(ID); // 해당 약정 내용 가져옴
		List<CommitmentDetail> commitmentDetail = commitmentDetailMapper.selectByCommitmentID2(ID); // 해당 약정 상세 가져옴

		model.addAttribute("commitment",commitment);
		model.addAttribute("commitmentDetails",commitmentDetail);
		model.addAttribute("commitmentID",commitment.getID());  // 새 약정상세 추가 위해
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String bank="은행";
		String begin = commitment.getEndDate();
		String end = commitment.getStartDate();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    Date beginDate = formatter.parse(begin);
	    Date endDate = formatter.parse(end);
	 
	    long diff = endDate.getTime() - beginDate.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	    System.out.println(diffDays);
		model.addAttribute("month",diffDays);
		model.addAttribute("bankList",codeMapper.selectByBank(bank));
		return "sponsor/commitmentEdit";
	}

	@RequestMapping(value="/sponsor/commitmentUpdate.do", method=RequestMethod.POST)  // 약정수정
	public String commitmentEdit(Model model, Commitment commitment) {
		if(commitment.getEndDate()==null || commitment.getEndDate()=="" ){
			commitment.setEndDate(null);
		}
		commitmentMapper.update(commitment);
		
		return "redirect:/sponsor/commitmentEdit.do?ID="+commitment.getID(); 

	}
	
	@RequestMapping(value="/sponsor/commitmentDetailSave.do", method=RequestMethod.POST)  // 약정상세수정
	public String commitmentDetailEdit(Model model, CommitmentDetail commitmentDetail) {
		if (commitmentDetail.getID() == 0)
			commitmentDetailMapper.insert(commitmentDetail);
		else
			commitmentDetailMapper.update(commitmentDetail);
		
		return "redirect:/sponsor/commitmentEdit.do?ID="+commitmentDetail.getCommitmentID(); 
	}

	@RequestMapping(value="/sponsor/commitmentDetailDelete.do", method=RequestMethod.POST)
		public String commitmentDetailEdit(Model model, @RequestParam int ID, @RequestParam int commitmentID) {
			commitmentDetailMapper.delete(ID);
		
		return "redirect:/sponsor/commitmentEdit.do?ID="+commitmentID; 
	}
	

	

}