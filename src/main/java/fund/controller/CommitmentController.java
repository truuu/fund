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

import fund.BaseController;
import fund.dto.Commitment;
import fund.dto.CommitmentCreate;
import fund.dto.CommitmentDetail;
import fund.dto.Sponsor;
import fund.mapper.CodeMapper;
import fund.mapper.CommitmentDetailMapper;
import fund.mapper.CommitmentMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.SponsorMapper;

@Controller
public class CommitmentController extends BaseController{

	@Autowired CommitmentMapper commitmentMapper;
	@Autowired CommitmentDetailMapper commitmentDetailMapper;
	@Autowired CodeMapper codeMapper;
	@Autowired DonationPurposeMapper donationPurposeMapper;
	@Autowired SponsorMapper sponsorMapper;

	/*약정목록*/
	@RequestMapping(value="/sponsor/commitment.do", method=RequestMethod.GET)  
	public String commitment(Model model,@RequestParam("id")int id) {   
		Sponsor sponsor=sponsorMapper.selectBySponsorNo(id);
		 model.addAttribute("sponsor", sponsor);
		model.addAttribute("list", commitmentMapper.selectBySponsorID(id)); 
		String name="정기 납입방법";
		model.addAttribute("paymentMethodList",codeMapper.selectByPaymentMethod(name));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String bank="은행";
		model.addAttribute("bankList",codeMapper.selectByBank(bank));
		model.addAttribute("sponsorID",id);
		model.addAttribute("sponsorNo",sponsorMapper.selectBySponsorNo2(id));

		return "sponsor/commitment";
	}

	/*약정 생성*/
	@RequestMapping(value="/sponsor/commitment.do", method=RequestMethod.POST, params="cmd=create")
	public String commitment(Model model, CommitmentCreate commitmentCreate) throws ParseException{
	
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
		System.out.println("약정 후");

		CommitmentDetail commitmentDetail = new CommitmentDetail();  // 약정상세 

		commitmentDetail.setCommitmentID(commitmentDetailMapper.selectCommitmentID());
		commitmentDetail.setAmountPerMonth(commitmentCreate.getAmountPerMonth());
		commitmentDetail.setBankID(commitmentCreate.getBankID());
		commitmentDetail.setPaymentDay(commitmentCreate.getPaymentDay());
		commitmentDetail.setAccountHolder(commitmentCreate.getAccountHolder());
		commitmentDetail.setAccountNo(commitmentCreate.getAccountNo());
		commitmentDetail.setStartDate(commitmentCreate.getCommitmentStartDate());

		commitmentDetailMapper.insert(commitmentDetail);  // 약정 상세 insert
		return "redirect:/sponsor/commitment.do?id="+commitmentCreate.getSponsorID();
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

	@RequestMapping(value="/sponsor/commitmentDetailDelete.do") // 약정 상세 삭제
		public String commitmentDetailDelete(Model model, int commitmentDetailID, int commitmentID) {
			commitmentDetailMapper.delete(commitmentDetailID);
		
		return "redirect:/sponsor/commitmentEdit.do?ID="+commitmentID; 
	}
	
	@RequestMapping(value="/sponsor/commitmentDelete.do") // 약정 삭제
	public String commitmentDelete(Model model, int commitmentID, int sponsorID) {
		commitmentMapper.delete(commitmentID);
	
	return "redirect:/sponsor/commitment.do?id="+sponsorID;   
	}
	
	@RequestMapping(value="/sponsor/commitmentEnd.do") // 약정 종료
	public String commitmentEnd(Model model, int ID, int sponsorID) {
		commitmentMapper.updateEndDate(ID);
	
	return "redirect:/sponsor/commitment.do?id="+sponsorID;  
	

	}
	
	
}