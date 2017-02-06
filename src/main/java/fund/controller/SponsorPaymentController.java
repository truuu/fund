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
    void modelAttr1(Model model, @RequestParam("sid") int sid, @ModelAttribute("pagination") PaginationSponsor pagination) {
        model.addAttribute("sponsor", sponsorMapper.selectById(sid));
    }

    static final String[] orderBy = new String[] { "paymentDate DESC", "paymentDate", "ID DESC", "ID" };

    // 정기 납입
    @RequestMapping("/sponsor/paymentList1.do")
    public String paymentList1(@RequestParam("sid") int sid, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) {
        model.addAttribute("commitments", commitmentMapper.selectBySponsorId(sid));
        return "sponsor/paymentList1";
    }

    @RequestMapping(value="/sponsor/paymentList1ajax.do", method=RequestMethod.POST)
    public String paymentList1a(@RequestParam("commitmentId") int commitmentId, Model model) {
        model.addAttribute("list", paymentMapper.selectPaymentList1a(commitmentId));
        return "sponsor/paymentList1ajax/ajax";
    }

    // 비정기 납입
    @RequestMapping("/sponsor/paymentList2.do")
    public String paymentList2(@RequestParam("sid") int sid, @ModelAttribute("pagination") PaginationSponsor pagination, Model model) {
        model.addAttribute("list", paymentMapper.selectPaymentList2(sid));
        return "sponsor/paymentList2";
    }

    // 비정기 납입 수정
    @RequestMapping(value="/sponsor/paymentEdit2.do", method=RequestMethod.GET)
    public String paymentEdit2(Model model, @RequestParam("id") int id) {
        model.addAttribute("payment", paymentMapper.selectById(id));
        model.addAttribute("donationPurposes", donationPurposeMapper.selectAll());
        model.addAttribute("paymentMethods", codeMapper.selectByCodeGroupId(C.코드그룹ID_비정기납입방법));
        return "sponsor/paymentEdit2";
    }

    private String redirectToList(Model model, int sid) {
        PaginationSponsor pagination = (PaginationSponsor)model.asMap().get("pagination");
        String qs = String.format("sid=%d&%s", sid, pagination.getQueryString());
        return "redirect:paymentList2.do?" + qs;
    }

    // 비정기 납입 저장
    @RequestMapping(value="/sponsor/paymentEdit2.do", method=RequestMethod.POST, params="cmd=save")
    public String paymentEdit2(Model model, @RequestParam("sid") int sid, Payment payment) throws Exception {
        paymentMapper.update(payment);
        return redirectToList(model, sid);
    }

    // 비정기 납입 삭제
    @RequestMapping(value="/sponsor/paymentEdit2.do", method=RequestMethod.POST, params="cmd=delete")
    public String paymentEdit2(Model model, @RequestParam("sid") int sid, @RequestParam("id") int id) throws Exception {
        paymentMapper.delete(id);
        return redirectToList(model, sid);
    }

    // 비정기 납입 생성
    @RequestMapping(value="/sponsor/paymentNew2.do", method=RequestMethod.GET)
    public String paymentNew2(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("donationPurposes", donationPurposeMapper.selectAll());
        model.addAttribute("paymentMethods", codeMapper.selectByCodeGroupId(C.코드그룹ID_비정기납입방법));
        return "sponsor/paymentEdit2";
    }

    // 비정기 납입 저장
    @RequestMapping(value="/sponsor/paymentNew2.do", method=RequestMethod.POST)
    public String paymentNew2(Model model, @RequestParam("sid") int sid, Payment payment) throws Exception {
        payment.setSponsorId(sid);
        paymentMapper.insert(payment);
        return redirectToList(model, sid);
    }
}
