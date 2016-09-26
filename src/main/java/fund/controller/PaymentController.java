package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fund.mapper.PaymentMapper;


@Controller
public class PaymentController {
	
	@Autowired PaymentMapper paymentMapper;


}
