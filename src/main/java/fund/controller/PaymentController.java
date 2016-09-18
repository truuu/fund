package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
}
