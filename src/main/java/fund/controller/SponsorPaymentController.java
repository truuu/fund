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
import fund.dto.Sponsor;
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
    public String paymentList1(@RequestParam("id") int id, Model model) {
        model.addAttribute("sponsor", sponsorMapper.selectById(id));
        model.addAttribute("list", paymentMapper.selectPaymentList1(id));
        return "sponsor/paymentList1";
    }

    // 비정기 납입
    @RequestMapping("/sponsor/paymentList2.do")
    public String paymentList2(Model model, @RequestParam("id") int id) {
        model.addAttribute("sponsor", sponsorMapper.selectById(id));
        model.addAttribute("list", paymentMapper.selectPaymentList2(id));
        return "sponsor/paymentList2";
    }

    @RequestMapping(value = "/sponsor/insertIrrgularPayment.do", method = RequestMethod.GET)
    public String insertIrrgularPayment1(Model model, @RequestParam("id") int id) {
        Sponsor sponsor = sponsorMapper.selectById(id);
        model.addAttribute("sponsor", sponsor);
        model.addAttribute("sponsorID", id);
        model.addAttribute("donationPurposeList", donationPurposeMapper.selectAll());

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
