package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.Payment;
import fund.dto.pagination.PaginationSponsor;
import fund.mapper.CodeMapper;
import fund.mapper.CommitmentMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.PaymentMapper;
import fund.mapper.SponsorMapper;
import fund.service.C;

@Controller
public class SponsorPaymentController extends BaseController {

    @Autowired CodeMapper codeMapper;
    @Autowired SponsorMapper sponsorMapper;
    @Autowired PaymentMapper paymentMapper;
    @Autowired CommitmentMapper commitmentMapper;
    @Autowired DonationPurposeMapper donationPurposeMapper;

    @ModelAttribute
    void modelAttr1(Model model, @RequestParam("sid") int sid, @ModelAttribute("pagination") PaginationSponsor pagination) throws Exception {
        model.addAttribute("sponsor", sponsorMapper.selectById(sid));
    }

    static final String[] orderBy = new String[] { "paymentDate DESC", "paymentDate", "ID DESC", "ID" };

    // 정기 납입
    @RequestMapping("/sponsor/payment/list1.do")
    public String list1(@RequestParam("sid") int sid, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) {
        model.addAttribute("commitments", commitmentMapper.selectBySponsorId(sid));
        return "sponsor/payment/list1";
    }

    @RequestMapping(value="/sponsor/payment/list1ajax.do", method=RequestMethod.POST)
    public String list1ajax(@RequestParam("commitmentId") int commitmentId, Model model) {
        model.addAttribute("list", paymentMapper.selectPaymentList1(commitmentId));
        return "sponsor/payment/list1ajax/ajax";
    }

    // 비정기 납입
    @RequestMapping("/sponsor/payment/list2.do")
    public String list2(@RequestParam("sid") int sid, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) {
        model.addAttribute("list", paymentMapper.selectPaymentList2(sid));
        return "sponsor/payment/list2";
    }

    @RequestMapping(value="/sponsor/payment/edit2.do", method=RequestMethod.GET)
    public String edit2(Model model, @RequestParam("id") int id) {
        model.addAttribute("payment", paymentMapper.selectById(id));
        model.addAttribute("donationPurposes", donationPurposeMapper.selectNotClosed());
        model.addAttribute("paymentMethods", codeMapper.selectByCodeGroupId(C.코드그룹ID_비정기납입방법));
        return "sponsor/payment/edit2";
    }

    private String redirectToList(Model model, int sid) {
        PaginationSponsor pagination = (PaginationSponsor)model.asMap().get("pagination");
        String qs = String.format("sid=%d&%s", sid, pagination.getQueryString());
        return "redirect:list2.do?" + qs;
    }

    @RequestMapping(value="/sponsor/payment/edit2.do", method=RequestMethod.POST, params="cmd=save")
    public String edit2(Model model, @RequestParam("sid") int sid, Payment payment) throws Exception {
        paymentMapper.update(payment);
        return redirectToList(model, sid);
    }

    @RequestMapping(value="/sponsor/payment/edit2.do", method=RequestMethod.POST, params="cmd=delete")
    public String edit2(Model model, @RequestParam("sid") int sid, @RequestParam("id") int id) throws Exception {
        paymentMapper.delete(id);
        return redirectToList(model, sid);
    }

    @RequestMapping(value="/sponsor/payment/create2.do", method=RequestMethod.GET)
    public String create2(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("donationPurposes", donationPurposeMapper.selectNotClosed());
        model.addAttribute("paymentMethods", codeMapper.selectByCodeGroupId(C.코드그룹ID_비정기납입방법));
        return "sponsor/payment/edit2";
    }

    @RequestMapping(value="/sponsor/payment/create2.do", method=RequestMethod.POST)
    public String create2(Model model, @RequestParam("sid") int sid, Payment payment) throws Exception {
        payment.setSponsorId(sid);
        paymentMapper.insert(payment);
        return redirectToList(model, sid);
    }
}
