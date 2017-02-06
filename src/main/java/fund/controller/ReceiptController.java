package fund.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.Corporate;
import fund.dto.Pagination;
import fund.dto.Payment;
import fund.dto.Receipt;
import fund.dto.Sponsor;
import fund.dto.TempNo;
import fund.mapper.CorporateMapper;
import fund.mapper.PaymentMapper;
import fund.mapper.ReceiptMapper;
import fund.mapper.SponsorMapper;
import fund.mapper.TempNoMapper;
import fund.param.MyParam;
import fund.service.ReceiptService;
import fund.service.ReportBuilder;
import fund.service.ReportBuilder2;
import fund.service.Util;
import net.sf.jasperreports.engine.JRException;

@Controller
public class ReceiptController extends BaseController {

    @Autowired ReceiptMapper receiptMapper;
    @Autowired PaymentMapper paymentMapper;
    @Autowired CorporateMapper corporateMapper;
    @Autowired SponsorMapper sponsorMapper;
    @Autowired TempNoMapper tempNoMapper;
    @Autowired SimpleDriverDataSource dataSource;
    @Autowired ReceiptService receiptService;

    @RequestMapping(value = "/receipt/create1.do", method = RequestMethod.GET)
    public String create1(Model model) throws Exception {
        MyParam param = new MyParam();
        param.getMap().put("createDate", Util.toYMD());
        model.addAttribute("param", param);
        model.addAttribute("corporates", corporateMapper.selectAll());
        return "receipt/create1";
    }

    @RequestMapping(value = "/receipt/create1.do", method = RequestMethod.POST, params="cmd=search")
    public String create1Search(Model model, MyParam param) throws Exception {
        model.addAttribute("list", paymentMapper.selectForReceiptCreation(param.getMap()));
        model.addAttribute("corporates", corporateMapper.selectAll());
        return "receipt/create1";
    }

    @RequestMapping(value = "/certificate/receiptList.do")
    public String receiptManage(Model model, Pagination pagination) throws Exception {
        // pagination.setRecordCount(receiptMapper.selectCount(pagination));
        model.addAttribute("receiptList", receiptMapper.selectPage(pagination));
        return "certificate/receiptList";
    }

    @RequestMapping(value = "/certificate/receiptList.do", method = RequestMethod.POST, params = "cmd=deleteRct")
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

    @RequestMapping(value = "/certificate/receiptList.do", method = RequestMethod.POST, params = "cmd=rct")
    public void receiptListReport(@RequestParam("rid") int[] rid, HttpServletRequest request,
            HttpServletResponse response) throws JRException, IOException, SQLException {
        tempNoMapper.deleteAll();
        StringBuilder stringBuilder = new StringBuilder();
        List<Receipt> list = new ArrayList<Receipt>();
        for (int i = 0; i < rid.length; i++) {
            list.add(receiptMapper.selectById(rid[i]));
        }
        if (list.size() > 0) { // 영수증목록이 1개 이상인 경우
            stringBuilder.append("WHERE r.id IN ("); // payment
            for (Receipt r : list) {
                r.setPaymentList(paymentMapper.selectByRctID(r.getID()));
                stringBuilder.append(r.getID()).append(',');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(')');
        }

        List<Sponsor> sponsorList = sponsorMapper.selectByReceipt(stringBuilder.toString());
        tempNoMapper.deleteAll();
        TempNo tempNo = new TempNo();
        for (Sponsor s : sponsorList) {
            tempNo.setId(s.getId());
            tempNo.setNo("01012345678");// 임시
            tempNoMapper.tempInsert(tempNo);
        }

        ReportBuilder2 reportBuilder = new ReportBuilder2("donationReceipt", "donationReceipt.pdf", request, response);
        reportBuilder.setConnection(dataSource.getConnection());
        reportBuilder.setParameter("whereClause", stringBuilder.toString());
        reportBuilder.addSubReport("paymentList.jasper");
        reportBuilder.build("pdf");

        tempNoMapper.deleteAll();
    }

    @RequestMapping(value = "/report/receiptView.do")
    public void receiptReport(Model model, @RequestParam("id") int id, HttpServletRequest req, HttpServletResponse res)
            throws JRException, IOException, SQLException {
        tempNoMapper.deleteAll();
        List<Receipt> list = new ArrayList<Receipt>();
        list.add(receiptMapper.selectById(id));
        for (Receipt r : list)
            r.setPaymentList(paymentMapper.selectByRctID(id));
        String whereClause = "WHERE r.id=" + id;

        Receipt tmpReceipt = new Receipt();
        tmpReceipt = receiptMapper.selectById(id);
        TempNo tempNo = new TempNo();
        tempNo.setId(tmpReceipt.getSponsorID());
        tempNo.setNo("012345678910");// 임시
        tempNoMapper.tempInsert(tempNo);

        ReportBuilder2 reportBuilder = new ReportBuilder2("donationReceipt", "Receipt.pdf", req, res);
        reportBuilder.setConnection(dataSource.getConnection());
        reportBuilder.setParameter("whereClause", whereClause);
        reportBuilder.addSubReport("paymentList.jasper");
        reportBuilder.build("pdf");

        tempNoMapper.deleteAll();
    }

    @RequestMapping(value = "/certificate/receiptByDur.do", method = RequestMethod.GET)
    public String receiptByDuration(Model model) throws Exception {
        return "certificate/receiptByDur";
    }

    @SuppressWarnings("null")
    @RequestMapping(value = "/certificate/receiptByDur.do", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String issueReceiptByDuration(Model model, HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        String startdate = req.getParameter("startDate");
        String enddate = req.getParameter("endDate");
        String createdate = req.getParameter("createDate");
        String[] getY = startdate.split("-");

        List<Corporate> corporates = corporateMapper.selectAll();
        for (Corporate corporate : corporates) {
            List<Integer> sponsorID = paymentMapper.selectDistinctSponsorID(startdate, enddate, corporate.getId());
            for (int i = 0; i < sponsorID.size(); i++) {
                int rctNoInt;
                if (receiptMapper.getLastNo(getY[0]) == null)
                    rctNoInt = 0;
                else {
                    String[] rctNo = receiptMapper.getLastNo(getY[0]).split("-");
                    rctNoInt = Integer.parseInt(rctNo[1]);
                }
                String newRctNo = getY[0] + "-" + String.format("%04d", rctNoInt + 1);
                Receipt rct = new Receipt();
                rct.setSponsorID(sponsorID.get(i));
                rct.setCreateDate(createdate);
                rct.setNo(newRctNo);
                receiptMapper.insert(rct);
                paymentMapper.issueReceiptByDur(receiptMapper.getRid(), startdate, enddate, corporate.getId(),
                        sponsorID.get(i));
            }
        }
        return "redirect:/certificate/receiptList.do";
    }

    @RequestMapping(value = "/certificate/receiptByName.do", method = RequestMethod.POST, params = "cmd=search")
    public String paymentListForReceipt(Model model, Payment payment, Pagination pagination, HttpServletRequest req,
            HttpServletResponse res) throws Exception {
        // model.addAttribute("paymentList",paymentMapper.selectReceiptByName(pagination));
        model.addAttribute("corporates", corporateMapper.selectAll());
        return "certificate/receiptByName";
    }

    @RequestMapping(value = "/certificate/receiptByName.do", method = RequestMethod.POST, params = "cmd=deleteRct")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String deleteReceipt(Payment payment, Pagination pagination, Model model, HttpServletRequest req,
            HttpServletResponse res) throws Exception {
        int rctId = Integer.parseInt(req.getParameter("delid"));
        paymentMapper.deleteReceiptByReceiptId(rctId);
        receiptMapper.deleteById(rctId);
        model.addAttribute("corporates", corporateMapper.selectAll());
        return "redirect:/certificate/receiptByName.do?" + pagination.getQueryString();
    }

    @RequestMapping(value = "/certificate/receiptByName.do", method = RequestMethod.POST, params = "cmd=issueRct")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String issueReceiptByName(Model model, Pagination pagination, @RequestParam("pid") int[] pID,
            HttpServletRequest req, HttpServletResponse res) throws Exception {
        String startdate = pagination.getStartDate();
        String message = receiptService.validateBeforeInsert(pID);
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
            rct.setSponsorID(paymentMapper.selectById(pID[0]).getSponsorId());
            rct.setCreateDate(req.getParameter("createDate"));
            rct.setNo(newRctNo);
            receiptMapper.insert(rct);
            int rctID = receiptMapper.selectRctID();
            for (int i = 0; i < pID.length; i++) {
                paymentMapper.issueReceiptByName(rctID, pID[i]);
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

    @RequestMapping(value = "/certificate/taxData.do", method = RequestMethod.POST, params = "cmd=xlsx")
    public void taxDataReport(Pagination pagination, HttpServletRequest req, HttpServletResponse res)
            throws JRException, IOException {
        List<Payment> list = paymentMapper.selectTaxData(pagination);
        ReportBuilder reportBuilder = new ReportBuilder("taxData", list, "taxData.xlsx", req, res);
        reportBuilder.build("xlsx");
    }

}
