package fund.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import fund.dto.Commitment;
import fund.dto.Payment;
import fund.dto.Salary;
import fund.dto.XferResult;
import fund.mapper.CommitmentMapper;
import fund.mapper.PaymentMapper;
import fund.service.C;
import fund.service.FileExtFilter;

@Controller
public class FinanceController extends BaseController{
	@Autowired CommitmentMapper commitmentMapper;
	@Autowired PaymentMapper paymentMapper;
	@Autowired FileExtFilter fileExtFilter;

	@RequestMapping(value="/finance/uploadXferResult.do",method=RequestMethod.GET)
	public String uploadXferResult(Model model) throws Exception{
		return "finance/uploadXferResult";
	}
	@RequestMapping(value="/finance/uploadXferResult.do",method=RequestMethod.POST)
	public String uploadXferResult(Model model,@RequestParam("file") MultipartFile uploadedFile,HttpSession session) throws Exception{
		if(fileExtFilter.correctFileExtIsReturnBoolean(uploadedFile) == true){ // ���� Ȯ���� ���͸�.
			if (uploadedFile.getSize() > 0 ) {
				byte[] bytes = uploadedFile.getBytes();

				String fileName = "d:/temp/"+uploadedFile.getOriginalFilename();

				File tempFile = new File(fileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tempFile));
				stream.write(bytes);
				stream.close();

				List<XferResult> xferResultList = ReadExcelFileToList.readExcelData(fileName);
				if(xferResultList.size() == 0) model.addAttribute("errorMsg", "�ùٸ��� ���� ������ Excel�����Դϴ�.");

				model.addAttribute("xferResultList",xferResultList);
				session.setAttribute("xferResult", xferResultList);
				return "finance/saveXferResult";
			}
		}else{
			model.addAttribute("errorMsg", "Excel������ ���ε� �� �ּ���.");
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
	@SuppressWarnings("null")
	@RequestMapping(value="/finance/uploadXferResult.do" ,method = RequestMethod.POST, params="cmd=saveCommitmentNo")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveCommitmentNo( @RequestParam("index") int[] indexes, @RequestParam("commitmentNo") String[] commitmentNos,HttpSession session,Model model) throws IOException, ParseException {
		List<XferResult> list = (List<XferResult>)session.getAttribute("xferResult");
		if (list == null) return "redirect:saveXferResult1.do";
		//SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Payment> paymentList = new ArrayList<Payment>();
		for (int i : indexes) {
			XferResult x = list.get(i);
			String commitmentNo = commitmentNos[i];

			Commitment commitment = commitmentMapper.selectByCommitmentNo(commitmentNo);

			Payment payment = new Payment();
			payment.setSponsorId(commitment.getSponsorId());
			payment.setCommitmentId(commitment.getId());
			payment.setCommitmentNo(commitmentNo);
			//Date pDate = transFormat.parse(x.getPaymentDate());
			//payment.setPaymentDate(pDate);
			payment.setPaymentDate(x.getPaymentDate());
			payment.setAmount(Integer.parseInt(x.getAmount()));
			payment.setDonationPurposeId(commitment.getDonationPurposeId());
			payment.setPaymentMethodId(commitment.getPaymentMethodId());
			paymentMapper.insertXferResult(payment);
			paymentList.add(payment);

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
		if(fileExtFilter.correctFileExtIsReturnBoolean(uploadedFile) == true){
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
		}else{
			model.addAttribute("errorMsg", "Excel������ ���ε� �� �ּ���.");
		}
		return "finance/uploadSalaryResult";
	}

	@SuppressWarnings("null")
	@RequestMapping(value="/finance/uploadSalaryResult.do",method=RequestMethod.POST, params="cmd=salaryToPayment")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String salaryToPayment(HttpSession session,Model model) throws ParseException{
		List<Salary> list = (List<Salary>)session.getAttribute("salaryListSession");
		if (list == null) return "redirect:uploadSalaryResult.do";
		//SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Payment> paymentList = new ArrayList<Payment>();
		for (int i=0; i<list.size(); i++) {
			Salary x = list.get(i);
			String sponsorNo = x.getSponsorNo();
			Commitment commitment = commitmentMapper.selectBySponsorAndPaymentMethod(sponsorNo, C.코드ID_급여공제);
			if(commitment == null) return "redirect:financeError.do";

			Payment payment = new Payment();
			payment.setSponsorId(commitment.getSponsorId());
			payment.setCommitmentId(commitment.getId());
			payment.setCommitmentNo(commitment.getCommitmentNo());
			//Date pDate = transFormat.parse(x.getPaymentDate());
            //payment.setPaymentDate(pDate);
			payment.setPaymentDate(x.getPaymentDate());
			payment.setAmount(Integer.parseInt(x.getAmount()));
			payment.setDonationPurposeId(commitment.getDonationPurposeId());
			payment.setPaymentMethodId(commitment.getPaymentMethodId());
			paymentMapper.insertSalaryResult(payment);
			paymentList.add(payment);

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

	@RequestMapping(value="/finance/financeError.do", method=RequestMethod.GET)
	public String financeError(Model model) throws Exception{

		return "finance/financeError";
	}


}
