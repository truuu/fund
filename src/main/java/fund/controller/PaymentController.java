package fund.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fund.dto.EB13_CommitmentDetail;
import fund.dto.Payment;
import fund.mapper.PaymentMapper;

@Controller
public class PaymentController {
	@Autowired PaymentMapper paymentMapper;
	
	@RequestMapping(value="/sponsor/insertIrrgularPayment.do", method=RequestMethod.GET)
	public String insertIrrgularPayment1(Payment payment,Model model) {	
		return "sponsor/insertIrrgularPayment";
	}

	@RequestMapping(value="/sponsor/insertIrrgularPayment.do", method=RequestMethod.POST)
	public String insertIrrgularPayment2(Payment payment,Model model) {
		paymentMapper.insertIrregularPayment(payment);
		return "sponsor/insertIrrgularPayment";
	}
	
	@RequestMapping(value="/sponsor/paymentList.do", method=RequestMethod.GET)
	public String paymentList(Model model){
		int sponsorID=109;
		List<Payment> paymentList = paymentMapper.selectPaymentRegular(sponsorID);
		model.addAttribute("paymentList", paymentList);
		return "sponsor/paymentList";
	}
	
	@RequestMapping(value="/sponsor/paymentList2.do", method=RequestMethod.GET)
	public String paymentList2(Model model){
		int sponsorID=109;
		List<Payment> paymentList2 = paymentMapper.selectPaymentIrregular(sponsorID);
		model.addAttribute("paymentList2", paymentList2);
		return "sponsor/paymentList2";
	}
}
