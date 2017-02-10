package fund.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fund.dto.Corporate;
import fund.dto.Payment;
import fund.dto.Receipt;
import fund.dto.Sponsor;
import fund.dto.pagination.Pagination;
import fund.dto.param.Wrapper;
import fund.mapper.CorporateMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.PaymentMapper;
import fund.mapper.ReceiptMapper;
import fund.mapper.SponsorMapper;
import fund.service.ReceiptService;
import fund.service.ReportBuilder;
import fund.service.Util;

@Controller
public class ReceiptController extends BaseController {

    @Autowired ReceiptMapper receiptMapper;
    @Autowired PaymentMapper paymentMapper;
    @Autowired CorporateMapper corporateMapper;
    @Autowired SponsorMapper sponsorMapper;
    @Autowired DonationPurposeMapper donationPurposeMapper;
    @Autowired SimpleDriverDataSource dataSource;
    @Autowired ReceiptService receiptService;

    @ModelAttribute
    void modela(Model model, Pagination pagination) {
    }

    @RequestMapping("/receipt/list.do")
    public String list(Model model, Pagination pagination) throws Exception {
        pagination.setRecordCount(receiptMapper.selectCount(pagination));
        model.addAttribute("list", receiptMapper.selectPage(pagination));
        return "receipt/list";
    }

    @RequestMapping(value="/receipt/create1.do", method=RequestMethod.GET)
    public String create1(Model model) throws Exception {
        model.addAttribute("wrapper", new Wrapper());
        model.addAttribute("corporates", corporateMapper.selectAll());
        return "receipt/create1";
    }

    @RequestMapping(value="/receipt/create1.do", method=RequestMethod.POST, params="cmd=search")
    public String create1Search(Model model, Wrapper wrapper) throws Exception {
        wrapper.getMap().put("createDate", Util.toYMD());
        model.addAttribute("list", paymentMapper.selectForReceiptCreation1(wrapper.getMap()));
        model.addAttribute("corporates", corporateMapper.selectAll());
        return "receipt/create1";
    }

    @RequestMapping(value="/receipt/create1.do", method=RequestMethod.POST, params="cmd=createReceipt")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String create1CreateReceipt(RedirectAttributes ra, @RequestParam("pid") int[] pid, Wrapper wrapper) throws Exception {
        String createDate = (String)wrapper.getMap().get("createDate");
        receiptService.createReceipt1(createDate, pid);
        ra.addFlashAttribute("successMsg", "영수증이 생성되었습니다.");
        return "redirect:list.do";
    }

    @RequestMapping("/receipt/detail.do")
    public String detail(Model model, @RequestParam("id") int id) throws Exception {
        Receipt receipt = receiptMapper.selectById(id);
        List<Payment> list = paymentMapper.selectByReceiptId(id);
        Sponsor sponsor = sponsorMapper.selectById(receipt.getSponsorId());
        Corporate corporate = null;
        if (list.size() > 0)
            corporate = corporateMapper.selectById(donationPurposeMapper.selectById(list.get(0).getDonationPurposeId()).getCorporateId());
        model.addAttribute("receipt", receipt);
        model.addAttribute("list", list);
        model.addAttribute("sponsor", sponsor);
        model.addAttribute("corporate", corporate);
        return "receipt/detail";
    }

    @RequestMapping("/receipt/report.do")
    public void list(@RequestParam("rid") int[] rid, HttpServletRequest req, HttpServletResponse res) throws Exception {
        String s = Arrays.toString(rid);
        s = s.substring(1, s.length()-1);
        String whereClause = "WHERE r.id IN (" + s + ")";

        ReportBuilder reportBuilder = new ReportBuilder("donationReceipt", "donationReceipt.pdf", req, res);
        reportBuilder.setConnection(dataSource.getConnection());
        reportBuilder.setParameter("whereClause", whereClause);
        reportBuilder.addSubReport("paymentList.jasper");
        reportBuilder.build("pdf");
    }

    @RequestMapping("/receipt/delete.do")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String delete(RedirectAttributes ra, @RequestParam("id") int id, Pagination pagination) {
        receiptService.deleteReceipt(id);
        ra.addFlashAttribute("successMsg", "영수증이 삭제되었습니다.");
        return "redirect:list.do?" + pagination.getQueryString();
    }

    @RequestMapping(value="/receipt/create2.do", method=RequestMethod.GET)
    public String create2(Model model) throws Exception {
        model.addAttribute("wrapper", new Wrapper());
        return "receipt/create2";
    }

    @RequestMapping(value="/receipt/create2.do", method=RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String create2CreateReceipt(RedirectAttributes ra, Wrapper wrapper) throws Exception {
        receiptService.createReceipt2(wrapper.getMap());
        ra.addFlashAttribute("successMsg", "영수증이 생성되었습니다.");
        return "redirect:list.do";
    }

    @RequestMapping(value="/receipt/taxData.do", method=RequestMethod.GET)
    public String taxData(Model model) {
        model.addAttribute("wrapper", new Wrapper());
        model.addAttribute("corporates", corporateMapper.selectAll());
        return "receipt/taxData";
    }

    @RequestMapping(value="/receipt/taxData.do", method=RequestMethod.POST)
    public void taxData(Model model, Wrapper wrapper, HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Map<String, Object>> list = paymentMapper.selectForTaxData(wrapper.getMap());
        ReportBuilder reportBuilder = new ReportBuilder("taxData", "taxData.xlsx", req, res);
        reportBuilder.setCollection(list);
        reportBuilder.build("xlsx");
    }


}
