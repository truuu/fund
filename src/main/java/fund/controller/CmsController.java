package fund.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import fund.dto.Commitment;
import fund.dto.EB21;
import fund.dto.Payment;
import fund.dto.Sal;
import fund.dto.Xfer;
import fund.mapper.CodeMapper;
import fund.mapper.CommitmentMapper;
import fund.mapper.EB21Mapper;
import fund.mapper.PaymentMapper;
import fund.param.CmsResultParam;
import fund.param.EB21Param;
import fund.service.C;
import fund.service.C2;
import fund.service.EncryptService;
import fund.service.ExcelService;
import fund.service.SponsorService;

@Controller
public class CmsController extends BaseController {

    static final SimpleDateFormat format_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    static final SimpleDateFormat format_yyMMdd = new SimpleDateFormat("yyMMdd");
    static final SimpleDateFormat format_MMdd = new SimpleDateFormat("MMdd");

    @Autowired CommitmentMapper commitmentMapper;
    @Autowired CodeMapper codeMapper;
    @Autowired EB21Mapper eb21Mapper;
    @Autowired PaymentMapper paymentMapper;

    //// EB13
    @RequestMapping(value="/cms/eb13.do", method=RequestMethod.GET)
    public String eb13(Model model) throws Exception {
        List<Commitment> list = commitmentMapper.selectEB13Candidate();
        SponsorService.decriptJuminNo(list);
        model.addAttribute("list", list);
        model.addAttribute("today", format_yyyyMMdd.format(new Date()));
        return "cms/eb13";
    }

    // TODO: 에러 발생시 EB13 파일 사용 불가 메시지.
    @RequestMapping(value="/cms/eb13.do", method=RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void eb13Create(@RequestParam("date") String date, HttpServletResponse response) throws Exception {
        Date today = format_yyyyMMdd.parse(date);
        String fileName = "EB13" + format_MMdd.format(today);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "MS949"))) {
            downloadEB13File(writer, fileName, today);
        }
    }

    private void downloadEB13File(BufferedWriter writer, String fileName, Date today) throws Exception {
        String orgCode = "9983010152";
        String yyMMdd = format_yyMMdd.format(today);

        writer.write("H");
        writer.write(StringUtils.repeat('0', 8));
        writer.write(orgCode);
        writer.write(fileName);
        writer.write(yyMMdd);
        writer.write(StringUtils.repeat(' ', 87));

        int count = 0;
        List<Commitment> list = commitmentMapper.selectEB13Candidate();
        for(Commitment c : list) {
            c.setJuminNo(EncryptService.decAES(c.getJuminNo()));
            if (c.isValid() == false) continue;

            String juminNo = c.getJuminNo();
            if (juminNo.length() == 13) juminNo = juminNo.substring(0, 6);

            writer.write("R");
            writer.write(String.format("%08d ", count + 1));
            writer.write(orgCode);
            writer.write(yyMMdd);
            writer.write("1");
            writer.write(String.format("%-20s", c.getCommitmentNo().replaceAll("-", "")));
            writer.write(c.getBankCode());
            writer.write(String.format("%-16s", c.getAccountNo().replaceAll("-", "")));
            writer.write(String.format("%-16s", juminNo));
            writer.write(StringUtils.repeat(' ', 4));
            writer.write(StringUtils.repeat(' ', 2));
            writer.write(StringUtils.repeat(' ', 1));
            writer.write(StringUtils.repeat(' ', 4));
            writer.write(StringUtils.repeat(' ', 1));
            writer.write(StringUtils.repeat(' ', 12));
            writer.write(StringUtils.repeat(' ', 1));
            writer.write(StringUtils.repeat(' ', 10));
            count++;

            c.setEb13Date(today);
            c.setEb13State("신청");
            c.setEb13ErrorCode(null);
            commitmentMapper.updateEB13(c);
        }
        writer.write("T");
        writer.write(StringUtils.repeat('9', 8));
        writer.write(orgCode);
        writer.write(fileName);
        writer.write(String.format("%08d", count));
        writer.write(String.format("%08d", count));
        writer.write(StringUtils.repeat('0', 8));
        writer.write(StringUtils.repeat('0', 8));
        writer.write(StringUtils.repeat('0', 8));
        writer.write(StringUtils.repeat(' ', 43));
        writer.write(StringUtils.repeat(' ', 10));
    }

    //// EB14
    @RequestMapping(value="/cms/eb14.do", method=RequestMethod.GET)
    public String eb14(Model model) {
        model.addAttribute("title", "EB14 파일 업로드");
        return "cms/upload";
    }

    // TODO: 에러 발생시 EB14 재등록 메시지.
    @RequestMapping(value="/cms/eb14.do", method=RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String eb14(Model model, @RequestParam("file") MultipartFile file) throws Exception {
        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        String s = IOUtils.toString(stream, "ASCII");

        Date today = format_yyMMdd.parse(s.substring(27,27+6));
        int headerLength = 120, trailerLength = 120, recordLength = 120;
        s = s.substring(headerLength);
        s = s.substring(0, s.length() - trailerLength);
        List<String> list = split(s, recordLength);
        for (String r : list) {
            String commitmentNo = r.substring(26, 26 + 20).trim();
            String errorCode = r.substring(92, 92 + 4);
            Commitment c = commitmentMapper.selectByCommitmentNo(commitmentNo);
            c.setEb13ErrorCode(errorCode);
            c.setEb13State("에러");
            commitmentMapper.updateEB13(c);
        }
        for (Commitment c : commitmentMapper.selectByEB13Date(today))
            if ("신청".equals(c.getEb13State())) {
                c.setEb13State("성공");
                commitmentMapper.updateEB13(c);
            }
        return "redirect:eb14result.do";
    }

    static List<String> split(String s, int size) {
        List<String> list = new ArrayList<String>();
        int index = 0;
        while (index + size <= s.length()) {
          String t = s.substring(index, index + size);
          list.add(t);
          index += size;
        }
        return list;
    }

    @RequestMapping(value="/cms/eb14result.do", method=RequestMethod.GET)
    public String eb14Result(Model model) throws Exception {
        CmsResultParam param = new CmsResultParam();
        Calendar cal = Calendar.getInstance(); cal.add(Calendar.MONTH, -1);
        Date date1 = cal.getTime();
        Date date2 = new Date();
        param.setStartDt(format_yyyyMMdd.format(date1));
        param.setEndDt(format_yyyyMMdd.format(date2));
        param.setState("all");
        List<Commitment> list = commitmentMapper.selectByCmsResultParam(param);
        SponsorService.decriptJuminNo(list);
        model.addAttribute("param", param);
        model.addAttribute("list", list);
        return "cms/eb14result";
    }

    @RequestMapping(value="/cms/eb14result.do", method=RequestMethod.POST)
    public String eb14Result(Model model, CmsResultParam param) throws Exception {
        List<Commitment> list = commitmentMapper.selectByCmsResultParam(param);
        SponsorService.decriptJuminNo(list);
        model.addAttribute("param", param);
        model.addAttribute("list", list);
        return "cms/eb14result";
    }

    //// EB21
    @RequestMapping(value="/cms/eb21.do", method=RequestMethod.GET)
    public String eb21(Model model) throws Exception {
        Calendar today = Calendar.getInstance();
        int day = today.get(Calendar.DAY_OF_MONTH);
        int paymentDay = day > 20 ? 25 : 20;
        Calendar paymentDate = Calendar.getInstance();
        paymentDate.set(Calendar.DAY_OF_MONTH, paymentDay);
        EB21Param param = new EB21Param();
        param.setPaymentDay(paymentDay);
        param.setPaymentDate(format_yyyyMMdd.format(paymentDate.getTime()));
        model.addAttribute("param", param);
        return "cms/eb21";
    }

    @RequestMapping(value="/cms/eb21.do", method=RequestMethod.POST, params="cmd=search")
    public String eb21(Model model, EB21Param param) throws Exception {
        List<Commitment> list = commitmentMapper.selectEB21Candidate(param);
        SponsorService.decriptJuminNo(list);
        model.addAttribute("param", param);
        model.addAttribute("list", list);
        return "cms/eb21";
    }

    // TODO: 에러 발생시 EB21 파일 사용 불가 메시지.
    @RequestMapping(value="/cms/eb21.do", method=RequestMethod.POST, params="cmd=create")
    public void eb21Create(EB21Param param, HttpServletResponse response) throws Exception {
        List<Commitment> list = commitmentMapper.selectEB21Candidate(param);
        SponsorService.decriptJuminNo(list);

        Date today = format_yyyyMMdd.parse(param.getPaymentDate());
        String fileName = "EB21" + format_MMdd.format(today);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "MS949"))) {
            downloadEB21File(writer, fileName, param);
        }
    }

    private void downloadEB21File(BufferedWriter writer, String fileName, EB21Param param) throws Exception {
        String orgCode = "9983010152";
        String yyMMdd = param.getPaymentDate().replaceAll("-", "").substring(2);

        writer.write("H");
        writer.write(StringUtils.repeat('0', 8));
        writer.write(orgCode);
        writer.write(fileName);
        writer.write(yyMMdd);
        writer.write("0054593");
        writer.write("15922014245");
        writer.write(StringUtils.repeat(' ', 94));

        int count = 0;
        int total = 0;
        EB21 eb21 = new EB21();
        eb21.setPaymentDate(format_yyyyMMdd.parse(param.getPaymentDate()));
        eb21.setState("신청");
        List<Commitment> list = commitmentMapper.selectEB21Candidate(param);
        for(Commitment c : list) {
            c.setJuminNo(EncryptService.decAES(c.getJuminNo()));
            if (c.isValid() == false) continue;

            String juminNo = c.getJuminNo();
            if (juminNo.length() == 13) juminNo = juminNo.substring(0, 6);

            writer.write("R");
            writer.write(String.format("%08d ", count + 1));
            writer.write(orgCode);
            writer.write(c.getBankCode());
            writer.write(String.format("%-16s", c.getAccountNo()));
            writer.write(String.format("%-13s", c.getAmountPerMonth()));
            total += c.getAmountPerMonth();

            String s = c.getJuminNo();
            if (s.length() == 13) s = s.substring(0, 6);
            writer.write(String.format("%-13s", s));
            writer.write(StringUtils.repeat(' ', 5));
            writer.write(C2.EB21Message);
            writer.write("  ");
            writer.write(String.format("%-20s", c.getCommitmentNo().replaceAll("-", "")));
            writer.write(StringUtils.repeat(' ', 5));
            writer.write("1");
            writer.write(StringUtils.repeat(' ', 12));
            writer.write(StringUtils.repeat(' ', 21));
            count++;

            eb21.setCommitmentId(c.getId());
            eb21Mapper.insert(eb21);
        }
        writer.write("T");
        writer.write(StringUtils.repeat('9', 8));
        writer.write(orgCode);
        writer.write(fileName);
        writer.write(String.format("%08d", count));
        writer.write(String.format("%08d", count));
        writer.write(String.format("%013d", total));
        writer.write(StringUtils.repeat('0', 8));
        writer.write(StringUtils.repeat('0', 13));
        writer.write(StringUtils.repeat(' ', 63));
        writer.write(StringUtils.repeat(' ', 10));
    }

    //// EB22
    @RequestMapping(value="/cms/eb22.do", method=RequestMethod.GET)
    public String eb22(Model model) {
        model.addAttribute("title", "EB22 파일 업로드");
        return "cms/upload";
    }

    // TODO: 에러 발생시 EB22 재등록 메시지.
    @RequestMapping(value="/cms/eb22.do", method=RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String eb22(Model model, @RequestParam("file") MultipartFile file) throws Exception {
        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        String s = IOUtils.toString(stream, "ASCII");

        Date today = format_yyMMdd.parse(s.substring(27,27+6));
        int headerLength = 150, trailerLength = 150, recordLength = 150;
        s = s.substring(headerLength);
        s = s.substring(0, s.length() - trailerLength);
        List<String> list = split(s, recordLength);
        for (String r : list) {
            String commitmentNo = r.substring(91, 91 + 20).trim();
            String errorCode = r.substring(69, 69 + 4);

            EB21 e = eb21Mapper.selectByCommitmentNo(commitmentNo);
            e.setState("에러");
            e.setErrorCode(errorCode);
            eb21Mapper.update(e);
        }
        for (EB21 e : eb21Mapper.selectByPaymentDate(today))
            if ("신청".equals(e.getState())) {
                e.setState("성공");
                eb21Mapper.update(e);
            }
        return "redirect:eb22result.do";
    }

    @RequestMapping(value="/cms/eb22result.do", method=RequestMethod.GET)
    public String eb21Result(Model model) throws Exception {
        CmsResultParam param = new CmsResultParam();
        Calendar cal = Calendar.getInstance(); cal.add(Calendar.DAY_OF_MONTH, -14);
        Date date1 = cal.getTime();
        Date date2 = new Date();
        param.setStartDt(format_yyyyMMdd.format(date1));
        param.setEndDt(format_yyyyMMdd.format(date2));
        param.setState("에러");
        List<EB21> list = eb21Mapper.selectByCmsResultParam(param);
        model.addAttribute("param", param);
        model.addAttribute("list", list);
        return "cms/eb22result";
    }

    @RequestMapping(value="/cms/eb22result.do", method=RequestMethod.POST)
    public String eb21Result(Model model, CmsResultParam param) throws Exception {
        List<EB21> list = eb21Mapper.selectByCmsResultParam(param);
        model.addAttribute("param", param);
        model.addAttribute("list", list);
        return "cms/eb22result";
    }


    //// 자동이체
    @RequestMapping(value="/cms/xfer.do", method=RequestMethod.GET)
    public String xfer(Model model) {
        model.addAttribute("title", "자동이체 결과파일 업로드");
        return "cms/upload";
    }

    @RequestMapping(value="/cms/xfer.do", method=RequestMethod.POST, params="cmd=upload")
    public String xfer(Model model, @RequestParam("file") MultipartFile file, HttpSession session) throws Exception {
        String redirect1 = "redirect:xfer.do";
        if (file.getSize() <= 0) return redirect1;
        List<Xfer> list = ExcelService.get자동이체Result(file.getInputStream());
        if (list.size() <= 0) return redirect1;
        session.setAttribute("xfer_notSaved", list);
        session.setAttribute("xfer_saved", null);
        return "cms/xfer";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value="/cms/xfer.do", method=RequestMethod.POST, params="cmd=save")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String xfer(Model model, @RequestParam("commitmentNo") String[] commitmentNo, HttpSession session) throws Exception {
        List<Xfer> list = (List<Xfer>)session.getAttribute("xfer_notSaved");
        List<Xfer> list_saved = (List<Xfer>)session.getAttribute("xfer_saved");
        if (list_saved == null) list_saved = new ArrayList<Xfer>();
        List<Xfer> list_notSaved = new ArrayList<Xfer>();

        for (int i = 0; i < list.size(); ++i) {
            Xfer x = list.get(i);
            x.setCommitmentNo(commitmentNo[i]);
            if (insertPayment(x)) list_saved.add(x);
            else list_notSaved.add(x);
        }
        session.setAttribute("xfer_notSaved", list_notSaved);
        session.setAttribute("xfer_saved", list_saved);
        return "cms/xfer";
    }

    private boolean insertPayment(Xfer xfer) {
        try {
            Commitment c = commitmentMapper.selectByCommitmentNo(xfer.getCommitmentNo());
            Payment payment = new Payment();
            payment.setSponsorId(c.getSponsorId());
            payment.setCommitmentId(c.getId());
            payment.setAmount(xfer.getAmount());
            payment.setPaymentDate(format_yyyyMMdd.format(xfer.getDate()));
            payment.setEtc(xfer.getEtc1());
            payment.setDonationPurposeId(c.getDonationPurposeId());
            payment.setPaymentMethodId(C.코드ID_자동이제);
            paymentMapper.insert(payment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //// 급여공제
    @RequestMapping(value="/cms/sal.do", method=RequestMethod.GET)
    public String salary(Model model) {
        model.addAttribute("title", "급여공제 결과파일 업로드");
        return "cms/upload";
    }

    @RequestMapping(value="/cms/sal.do", method=RequestMethod.POST, params="cmd=upload")
    public String sal(@RequestParam("file") MultipartFile file, HttpSession session) throws Exception {
        String redirect1 = "redirect:sal.do";
        if (file.getSize() <= 0) return redirect1;
        List<Sal> list = ExcelService.get급여공제Result(file.getInputStream());
        if (list.size() <= 0) return redirect1;
        session.setAttribute("sal_notSaved", list);
        session.setAttribute("sal_saved", null);
        return "cms/sal";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value="/cms/sal.do", method=RequestMethod.POST, params="cmd=save")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String sal(Model model, @RequestParam("commitmentNo") String[] commitmentNo, HttpSession session) throws Exception {
        List<Sal> list = (List<Sal>)session.getAttribute("sal_notSaved");
        List<Sal> list_saved = (List<Sal>)session.getAttribute("sal_saved");
        if (list_saved == null) list_saved = new ArrayList<Sal>();
        List<Sal> list_notSaved = new ArrayList<Sal>();

        for (int i = 0; i < list.size(); ++i) {
            Sal s = list.get(i);
            s.setCommitmentNo(commitmentNo[i]);
            if (insertPayment(s)) list_saved.add(s);
            else list_notSaved.add(s);
        }
        session.setAttribute("sal_notSaved", list_notSaved);
        session.setAttribute("sal_saved", list_saved);
        return "cms/sal";
    }

    private boolean insertPayment(Sal sal) {
        try {
            Commitment c = commitmentMapper.selectByCommitmentNo(sal.getCommitmentNo());
            Payment payment = new Payment();
            payment.setSponsorId(c.getSponsorId());
            payment.setCommitmentId(c.getId());
            payment.setAmount(sal.getAmount());
            payment.setPaymentDate(format_yyyyMMdd.format(sal.getDate()));
            payment.setEtc(sal.getEtc());
            payment.setDonationPurposeId(c.getDonationPurposeId());
            payment.setPaymentMethodId(C.코드ID_급여공제);
            paymentMapper.insert(payment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}




























