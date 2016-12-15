package fund.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@Secured("ROLE_true")
	@RequestMapping(value="/sponsor/commitment.do", method=RequestMethod.GET)  
	public String commitment(Model model,@RequestParam("id")int id) {   
		Sponsor sponsor=sponsorMapper.selectBySponsorNo(id);
		model.addAttribute("sponsor", sponsor);
		model.addAttribute("list", commitmentMapper.selectBySponsorID(id)); 
		String name="정기 납입방법";
		model.addAttribute("paymentMethodList",codeMapper.selectByCodeGroupName(name));
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String bank="은행";
		model.addAttribute("bankList",codeMapper.selectByCodeGroupName(bank));
		model.addAttribute("sponsorID",id);
		model.addAttribute("sponsorNo",sponsorMapper.selectBySponsorNo2(id));

		return "sponsor/commitment";
	}

	/*약정 생성*/
	@Secured("ROLE_true")
	@RequestMapping(value="/sponsor/commitment.do", method=RequestMethod.POST, params="cmd=create")
	public String commitment(Model model, CommitmentCreate commitmentCreate, 
			RedirectAttributes redirectAttributes) throws ParseException{
		
		int count=0;
		
		if (StringUtils.isBlank(commitmentCreate.getCommitmentDate())){
			redirectAttributes.addFlashAttribute("e1","약정일을 선택하세요.");
			count++;
		}
		if(StringUtils.isBlank(commitmentCreate.getCommitmentStartDate())){
			redirectAttributes.addFlashAttribute("e2","시작일을 선택하세요.");
			count++;
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar c1 = Calendar.getInstance();
	        String today = sdf.format(c1.getTime());
	  
			if(commitmentCreate.getCommitmentStartDate().compareTo(today)<0){
				redirectAttributes.addFlashAttribute("e5","시작일은 과거날짜를 선택할 수 없습니다.");
				count++;
			}
		}
		if(commitmentCreate.getDonationPurposeID()==null){
			redirectAttributes.addFlashAttribute("e3","기부목적을 선택하세요.");
			count++;
		}
		if(commitmentCreate.getAmountPerMonth()==null){
			redirectAttributes.addFlashAttribute("e4","1회납입액을 입력하세요.");
			count++;
		}
		if(StringUtils.isBlank(commitmentCreate.getEndDate())==false){
		
			if(commitmentCreate.getEndDate().compareTo(commitmentCreate.getCommitmentStartDate())<0){
				redirectAttributes.addFlashAttribute("e6","종료일은 시작일보다 작아야합니다.");
				count++;
			}
		}
		if(count!=0){
			redirectAttributes.addFlashAttribute("commitmentCreate",commitmentCreate);
			redirectAttributes.addFlashAttribute("tableShow","tableShow");
			return "redirect:/sponsor/commitment.do?id="+commitmentCreate.getSponsorID();
		}
	
		Commitment commitment = new Commitment();  //약정 

		commitmentMapper.selectCountCommitment(commitmentCreate.getSponsorID());  // 해당 후원자의 약정 갯수 구하기
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
		return "redirect:/sponsor/commitment.do?id="+commitmentCreate.getSponsorID();
	}

	@Secured("ROLE_true")
	@RequestMapping(value="/sponsor/commitmentEdit.do", method=RequestMethod.GET)  // 약정수정페이지
	public String commitmentEdit(Model model, @RequestParam("ID") int ID) throws ParseException {
		Commitment commitment = commitmentMapper.selectByID(ID); // 해당 약정 내용 가져옴
		List<CommitmentDetail> commitmentDetail = commitmentDetailMapper.selectByCommitmentID2(ID); // 해당 약정 상세 가져옴

		model.addAttribute("commitment",commitment);
		model.addAttribute("commitmentDetails",commitmentDetail);
		model.addAttribute("commitmentID",commitment.getID());  // 새 약정상세 추가 위해
		model.addAttribute("donationPurposeList",donationPurposeMapper.selectDonationPurpose());
		String bank="은행";
	
		model.addAttribute("bankList",codeMapper.selectByCodeGroupName(bank));
		return "sponsor/commitmentEdit";
	}

	@Secured("ROLE_true")
	@RequestMapping(value="/sponsor/commitmentUpdate.do", method=RequestMethod.POST)  // 약정수정
	public String commitmentEdit(Model model, Commitment commitment) {
		
		if(commitment.getEndDate()==null || commitment.getEndDate()=="" ){
			commitment.setEndDate(null);
		}
		commitmentMapper.update(commitment);
		
		return "redirect:/sponsor/commitmentEdit.do?ID="+commitment.getID(); 

	}
	
	@Secured("ROLE_true")
	@RequestMapping(value="/sponsor/commitmentDetailSave.do", method=RequestMethod.POST)  // 약정상세수정, 신규
	public String commitmentDetailEdit(Model model,CommitmentDetail commitmentDetail,RedirectAttributes redirectAttributes) {
		if (commitmentDetail.getID() == 0){
			int count=0;
			
			if (StringUtils.isBlank(commitmentDetail.getStartDate())){
				redirectAttributes.addFlashAttribute("err1","시작일을 선택하세요.");
				count++;
			}
			
			if(commitmentDetail.getAmount2()==null){
				redirectAttributes.addFlashAttribute("err2","1회납입액을 입력하세요.");
				count++;
			}
			if(count!=0){
				redirectAttributes.addFlashAttribute("newCommitmentDetail",commitmentDetail);
				return "redirect:/sponsor/commitmentEdit.do?ID="+commitmentDetail.getCommitmentID(); 
			}
			commitmentDetail.setAmountPerMonth(commitmentDetail.getAmount2());
			commitmentDetailMapper.insert(commitmentDetail);
		}
		else
			commitmentDetailMapper.update(commitmentDetail);
		
		return "redirect:/sponsor/commitmentEdit.do?ID="+commitmentDetail.getCommitmentID(); 
	}

	@Secured("ROLE_true")
	@RequestMapping(value="/sponsor/commitmentDetailDelete.do") // 약정 상세 삭제
		public String commitmentDetailDelete(Model model, int commitmentDetailID, int commitmentID, RedirectAttributes redirectAttributes) {
		try{
			commitmentDetailMapper.delete(commitmentDetailID);
		}
		catch(DataIntegrityViolationException e){
			redirectAttributes.addFlashAttribute("errorMessage2","해당 약정상세는 eb파일이 생성되어 삭제할 수 없습니다.");
			return "redirect:/sponsor/commitmentEdit.do?ID="+commitmentID;
		}
		
		
		return "redirect:/sponsor/commitmentEdit.do?ID="+commitmentID; 
	}
	
	@Secured("ROLE_true")
	@RequestMapping(value="/sponsor/commitmentDelete.do") // 약정 삭제
	public String commitmentDelete(Model model, int commitmentID, int sponsorID, RedirectAttributes redirectAttributes) {
		try{
			commitmentMapper.delete(commitmentID);
		}
		catch(DataIntegrityViolationException e){
			redirectAttributes.addFlashAttribute("errorMessage1","해당 약정에 납입내역이나 약정상세가 있어 삭제할 수 없습니다.");
			return "redirect:/sponsor/commitmentEdit.do?ID="+commitmentID; 
		}
	
	return "redirect:/sponsor/commitment.do?id="+sponsorID;   
	}
	
	@Secured("ROLE_true")
	@RequestMapping(value="/sponsor/commitmentEnd.do") // 약정 종료
	public String commitmentEnd(Model model, int ID, int sponsorID) {
		commitmentMapper.updateEndDate(ID);
	
	return "redirect:/sponsor/commitment.do?id="+sponsorID;  
	

	}
	
	
}