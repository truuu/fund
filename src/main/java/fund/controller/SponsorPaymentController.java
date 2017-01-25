package fund.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.IregularPayment;
import fund.dto.PaginationSponsor;
import fund.dto.Payment;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.PaymentMapper;
import fund.mapper.SponsorMapper;

@Controller
public class SponsorPaymentController {

    @Autowired SponsorMapper sponsorMapper;
    @Autowired PaymentMapper paymentMapper;
    @Autowired DonationPurposeMapper donationPurposeMapper;

    @ModelAttribute
    void modelAttr1(@ModelAttribute("pagination") PaginationSponsor pagination) {
    }

    // 정기 납입
    @RequestMapping("/sponsor/paymentList1.do")
    public String paymentList1(@RequestParam("sid") int sid, Model model) {
        model.addAttribute("sponsor", sponsorMapper.selectById(sid));
        model.addAttribute("list", paymentMapper.selectPaymentList1(sid));
        return "sponsor/paymentList1";
    }

    // 비정기 납입
    @RequestMapping("/sponsor/paymentList2.do")
    public String paymentList2(Model model, @RequestParam("sid") int sid) {
        model.addAttribute("sponsor", sponsorMapper.selectById(sid));
        model.addAttribute("list", paymentMapper.selectPaymentList2(sid));
        return "sponsor/paymentList2";
    }

    // 비정기 납입 수정
    @RequestMapping(value="/sponsor/paymentEdit2.do", method=RequestMethod.GET)
    public String paymentEdit2(Model model, @RequestParam("id") int id) {

        return "sponsor/insertIrrgularPayment";
    }

    @RequestMapping(value = "/sponsor/insertIrrgularPayment.do", method = RequestMethod.POST)
    public String insertIrrgularPayment2(IregularPayment iregularPayment, Model model) throws Exception {
        Payment payment = new Payment();
        payment.setSponsorId(iregularPayment.getSponsorID());
        payment.setAmount(iregularPayment.getAmount());
        Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(iregularPayment.getPaymentDate());
        payment.setPaymentDate(date);
        payment.setPaymentMethodId(iregularPayment.getPaymentMethodID());
        payment.setDonationPurposeId(iregularPayment.getDonationPurposeID());
        payment.setEtc(iregularPayment.getEtc());

        paymentMapper.insertIrregularPayment(payment);
        return "redirect:/sponsor/paymentList2.do?id=" + iregularPayment.getSponsorID();
    }

}
