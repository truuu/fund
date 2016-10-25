package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fund.dto.XferResult;
import fund.dto.Salary;
import fund.BaseController;
import fund.dto.Commitment;
import fund.dto.Files;
import fund.dto.Payment;
import fund.mapper.CommitmentMapper;
import fund.mapper.PaymentMapper;

@Controller
public class FinanceController extends BaseController{
	@Autowired CommitmentMapper commitmentMapper;
	@Autowired PaymentMapper paymentMapper;

	@RequestMapping(value="/finance/uploadXferResult.do",method=RequestMethod.GET)
	public String uploadXferResult(Model model) throws Exception{    
		return "finance/uploadXferResult";
	}
	@RequestMapping(value="/finance/uploadXferResult.do",method=RequestMethod.POST)
	public String uploadXferResult(Model model,@RequestParam("file") MultipartFile uploadedFile,HttpSession session) throws Exception{    
		if (uploadedFile.getSize() > 0 ) {
			byte[] bytes = uploadedFile.getBytes();

			String fileName = "/Users/parkeunsun/Documents/"+uploadedFile.getOriginalFilename();

			File tempFile = new File(fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tempFile));
			stream.write(bytes);
			stream.close();

			List<XferResult> xferResultList = ReadExcelFileToList.readExcelData(fileName);
			model.addAttribute("xferResultList",xferResultList);
			session.setAttribute("xferResult", xferResultList);
			return "finance/saveXferResult";
		}

		return "finance/uploadXferResult";
	}

	@RequestMapping(value="/finance/saveXferResult.do",method=RequestMethod.GET)
	public String saveXferResult(Model model) throws Exception{    

		return "finance/saveXferResult";
	}
	@RequestMapping(value="/finance/saveXferResult2.do",method=RequestMethod.GET)
	public String saveXferResult2(Model model) throws Exception{    


		return "finance/saveXferResult2";
	}

	@RequestMapping(value="/finance/uploadXferResult.do" ,method = RequestMethod.POST, params="cmd=saveCommitmentNo")
	public String saveCommitmentNo( @RequestParam("index") int[] indexes, @RequestParam("commitmentNo") String[] commitmentNos,HttpSession session,Model model) throws IOException, ParseException {
		List<XferResult> list = (List<XferResult>)session.getAttribute("xferResult");
		if (list == null) return "redirect:saveXferResult1.do";
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Payment> paymentList = new ArrayList<Payment>();
		for (int i : indexes) {
			System.out.println(i);
			XferResult x = list.get(i);
			String commitmentNo = commitmentNos[i];

			Commitment commitment = commitmentMapper.selectByCommitmentNo(commitmentNo);
			if(commitment == null){
				model.addAttribute("errorMsg", "파일에 약정 등록이 되지 않은 후원인이 존재합니다. 확인 후 다시 시도해주세요.");
			}else{
				Payment payment = new Payment();
				payment.setSponsorID(commitment.getSponsorID());
				payment.setCommitmentID(commitment.getID());
				payment.setCommitmentNo(commitmentNo);
				Date pDate = transFormat.parse(x.getPaymentDate());
				payment.setPaymentDate(pDate);
				payment.setAmount(Integer.parseInt(x.getAmount()));
				payment.setDonationPurposeID(commitment.getDonationPurposeID());
				payment.setPaymentMethodID(commitment.getPaymentMethodID());
				paymentMapper.insertXferResult(payment);
				paymentList.add(payment);
			}
		}
		model.addAttribute("paymentList", paymentList);

		return "finance/saveXferResult2";
	}
	
	@RequestMapping(value="/finance/uploadSalaryResult.do",method=RequestMethod.GET)
	public String uploadSalaryResult(Model model) throws Exception{    
		return "finance/uploadSalaryResult";
	}
	@RequestMapping(value="/finance/uploadSalaryResult.do",method=RequestMethod.POST)
	public String uploadSalaryResult(Model model,@RequestParam("file") MultipartFile uploadedFile,HttpSession session) throws Exception{    
		if (uploadedFile.getSize() > 0 ) {
			byte[] bytes = uploadedFile.getBytes();
			String fileName = "/Users/parkeunsun/Documents/"+uploadedFile.getOriginalFilename();
			File tempFile = new File(fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tempFile));
			stream.write(bytes);
			stream.close();

			List<Salary> salaryList = ReadExcelSalaryToList.readExcelData(fileName);
			model.addAttribute("salaryList",salaryList);
			session.setAttribute("salaryListSession", salaryList);
			return "finance/salary";
		}
		return "finance/uploadSalaryResult";
	}

	@RequestMapping(value="/finance/uploadSalaryResult.do",method=RequestMethod.POST, params="cmd=salaryToPayment")
	public String salaryToPayment(HttpSession session,Model model) throws ParseException{
		List<Salary> list = (List<Salary>)session.getAttribute("salaryListSession");
		if (list == null) return "redirect:uploadSalaryResult.do";
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Payment> paymentList = new ArrayList<Payment>();
		for (int i=0; i<list.size(); i++) {
			Salary x = list.get(i);
			String sponsorNo = x.getSponsorNo();
			Commitment commitment = commitmentMapper.selectIDBySponsorNo(sponsorNo); 
			
			if(commitment == null){
				model.addAttribute("errorMsg", "파일에 약정 등록이 되지 않은 후원인이 존재합니다. 확인 후 다시 시도해주세요.");
			}else{
				Payment payment = new Payment();
				payment.setSponsorID(commitment.getSponsorID());
				payment.setCommitmentID(commitment.getID());
				payment.setCommitmentNo(commitment.getCommitmentNo());
				Date pDate = transFormat.parse(x.getPaymentDate());
				payment.setPaymentDate(pDate);
				payment.setAmount(Integer.parseInt(x.getAmount()));
				payment.setDonationPurposeID(commitment.getDonationPurposeID());
				payment.setPaymentMethodID(commitment.getPaymentMethodID());
				paymentMapper.insertSalaryResult(payment);
				paymentList.add(payment);
			}
		}
		model.addAttribute("paymentList", paymentList);
		return "finance/salary";
	}
	@RequestMapping(value="/finance/salary.do", method=RequestMethod.GET)
	public String salary(Model model) throws Exception{
		List<Salary> salaryList = ReadExcelSalaryToList.readExcelData("/Users/parkeunsun/Documents/salary_sample_1.xlsx");
		model.addAttribute("salaryList",salaryList);

		return "finance/salary";
	}
}
