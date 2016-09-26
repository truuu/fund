package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fund.mapper.PaymentMapper;
import fund.dto.*;

@Controller
public class PaymentController {
	
	@Autowired PaymentMapper paymentMapper;


}
