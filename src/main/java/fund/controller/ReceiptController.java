package fund.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
import fund.dto.TempNo;
import fund.dto.pagination.Pagination;
import fund.dto.param.Wrapper;
import fund.mapper.CorporateMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.PaymentMapper;
import fund.mapper.ReceiptMapper;
import fund.mapper.SponsorMapper;
import fund.mapper.TempNoMapper;
import fund.service.ReceiptService;
import fund.service.ReportBuilder;
import fund.service.ReportBuilder2;
import fund.service.SponsorService;
import fund.service.Util;
import net.sf.jasperreports.engine.JRException;

@Controller
public class ReceiptController extends BaseController {

    @Autowired ReceiptMapper receiptMapper;
    @Autowired PaymentMapper paymentMapper;
    @Autowired CorporateMapper corporateMapper;
    @Autowired SponsorMapper sponsorMapper;
    @Autowired TempNoMapper tempNoMapper;
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
        SponsorService.decryptJuminNo(sponsor);
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
        for (int i = 0; i < rid.length; ++i) {
            Receipt receipt = receiptMapper.selectById(rid[i]);
            Sponsor sponsor = sponsorMapper.selectById(receipt.getSponsorId());
            SponsorService.decryptJuminNo(sponsor);
            TempNo tempNo = new TempNo();
            tempNo.setId(sponsor.getId());
            tempNo.setNo(sponsor.getJuminNo());
            tempNoMapper.insert(tempNo);
        }
        String s = Arrays.toString(rid);
        s = s.substring(1, s.length()-1);
        String whereClause = "WHERE r.id IN (" + s + ")";

        ReportBuilder2 reportBuilder = new ReportBuilder2("donationReceipt", "donationReceipt.pdf", req, res);
        reportBuilder.setConnection(dataSource.getConnection());
        reportBuilder.setParameter("whereClause", whereClause);
        reportBuilder.addSubReport("paymentList.jasper");
        reportBuilder.build("pdf");
        tempNoMapper.deleteAll();
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
        return "receipt/taxData";
    }

    @RequestMapping(value="/receipt/taxData.do", method=RequestMethod.POST)
    public String taxData(Model model, Wrapper wrapper) {
        return "receipt/taxData";
    }






    @RequestMapping(value="/certificate/receiptList.do")
    public String receiptManage(Model model, Pagination pagination) throws Exception {
        // pagination.setRecordCount(receiptMapper.selectCount(pagination));
        model.addAttribute("receiptList", receiptMapper.selectPage(pagination));
        return "certificate/receiptList";
    }

    @RequestMapping(value="/certificate/receiptList.do", method=RequestMethod.POST, params = "cmd=deleteRct")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String deleteReceipts(Model model, HttpServletRequest req, HttpServletResponse res) throws Exception {
        String[] id = req.getParameterValues("rid");

        if (id != null) {
            for (int i = 0; i < id.length; i++) {
                int rctId = Integer.parseInt(id[i]);
                paymentMapper.deleteReceiptByReceiptId(rctId);
                receiptMapper.deleteById(rctId);
            }
        }
        return "redirect:/certificate/receiptList.do";
    }



    @RequestMapping(value="/certificate/receiptByDur.do", method=RequestMethod.GET)
    public String receiptByDuration(Model model) throws Exception {
        return "certificate/receiptByDur";
    }

    @SuppressWarnings("null")
    @RequestMapping(value="/certificate/receiptByDur.do", method=RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String issueReceiptByDuration(Model model, HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        String startdate = req.getParameter("startDate");
        String enddate = req.getParameter("endDate");
        String createdate = req.getParameter("createDate");
        String[] getY = startdate.split("-");

        List<Corporate> corporates = corporateMapper.selectAll();
        for (Corporate corporate : corporates) {
            List<Integer> sponsorId = paymentMapper.selectDistinctSponsorID(startdate, enddate, corporate.getId());
            for (int i = 0; i < sponsorId.size(); i++) {
                int rctNoInt;
                if (receiptMapper.getLastNo(getY[0]) == null)
                    rctNoInt = 0;
                else {
                    String[] rctNo = receiptMapper.getLastNo(getY[0]).split("-");
                    rctNoInt = Integer.parseInt(rctNo[1]);
                }
                String newRctNo = getY[0] + "-" + String.format("%04d", rctNoInt + 1);
                Receipt rct = new Receipt();
                rct.setSponsorId(sponsorId.get(i));
                rct.setCreateDate(createdate);
                rct.setNo(newRctNo);
                receiptMapper.insert(rct);
                //paymentMapper.issueReceiptByDur(receiptMapper.getRid(), startdate, enddate, corporate.getId(), sponsorId.get(i));
            }
        }
        return "redirect:/certificate/receiptList.do";
    }

    @RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.POST, params = "cmd=search")
    public String paymentListForReceipt(Model model, Payment payment, Pagination pagination, HttpServletRequest req,
            HttpServletResponse res) throws Exception {
        // model.addAttribute("paymentList",paymentMapper.selectReceiptByName(pagination));
        model.addAttribute("corporates", corporateMapper.selectAll());
        return "certificate/receiptByName";
    }

    @RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.POST, params = "cmd=deleteRct")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String deleteReceipt(Payment payment, Pagination pagination, Model model, HttpServletRequest req,
            HttpServletResponse res) throws Exception {
        int rctId = Integer.parseInt(req.getParameter("delid"));
        paymentMapper.deleteReceiptByReceiptId(rctId);
        receiptMapper.deleteById(rctId);
        model.addAttribute("corporates", corporateMapper.selectAll());
        return "redirect:/certificate/receiptByName.do?" + pagination.getQueryString();
    }

    @RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.POST, params = "cmd=issueRct")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String issueReceiptByName(Model model, Pagination pagination, @RequestParam("pid") int[] pID,
            HttpServletRequest req, HttpServletResponse res) throws Exception {
        String startdate = null; //pagination.getStartDate();
        String message = null; //receiptService.validateBeforeInsert(pID);
        model.addAttribute("corporates", corporateMapper.selectAll());

        if (message == null) {
            // 영수증생성
            String[] getY = startdate.split("-");
            int rctNoInt;

            if (receiptMapper.getLastNo(getY[0]) == null)
                rctNoInt = 0;
            else {
                String[] rctNo = receiptMapper.getLastNo(getY[0]).split("-");
                rctNoInt = Integer.parseInt(rctNo[1]);
            }

            String newRctNo = getY[0] + "-" + String.format("%04d", rctNoInt + 1);

            Receipt rct = new Receipt();
            rct.setSponsorId(paymentMapper.selectById(pID[0]).getSponsorId());
            rct.setCreateDate(req.getParameter("createDate"));
            rct.setNo(newRctNo);
            receiptMapper.insert(rct);
            int rctID = receiptMapper.selectRctID();
            for (int i = 0; i < pID.length; i++) {
                //paymentMapper.issueReceiptByName(rctID, pID[i]);
            }

        } else {
            model.addAttribute("errorMsg", message);
            return "redirect:/certificate/receiptByName.do?" + pagination.getQueryString();
        }

        return "redirect:/certificate/receiptList.do";
    }

    @RequestMapping("/certificate/taxData.do")
    public String taxData(Model model, Pagination pagination) throws Exception {
        pagination.setRecordCount(paymentMapper.selectCount(pagination));
        model.addAttribute("taxDataList", paymentMapper.selectPage(pagination));
        model.addAttribute("corporates", corporateMapper.selectAll());
        return "certificate/taxData";
    }

    @RequestMapping(value="/certificate/taxData.do", method=RequestMethod.POST, params = "cmd=xlsx")
    public void taxDataReport(Pagination pagination, HttpServletRequest req, HttpServletResponse res)
            throws JRException, IOException {
        List<Payment> list = paymentMapper.selectTaxData(pagination);
        ReportBuilder reportBuilder = new ReportBuilder("taxData", list, "taxData.xlsx", req, res);
        reportBuilder.build("xlsx");
    }

}
