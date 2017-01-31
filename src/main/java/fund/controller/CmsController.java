package fund.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.OutputStreamWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import fund.dto.EB14ResultParam;
import fund.mapper.CodeMapper;
import fund.mapper.CommitmentMapper;
import fund.service.EncryptService;

@Controller
public class CmsController extends BaseController {

    static final SimpleDateFormat format_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    static final SimpleDateFormat format_yyMMdd = new SimpleDateFormat("yyMMdd");
    static final SimpleDateFormat format_MMdd = new SimpleDateFormat("MMdd");

    @Autowired CommitmentMapper commitmentMapper;
    @Autowired CodeMapper codeMapper;

    @RequestMapping(value="/cms/eb13.do", method=RequestMethod.GET)
    public String eb13(Model model) throws Exception {
        List<Commitment> list = commitmentMapper.selectEB13Candidate();
        for (Commitment c : list)
            c.setJuminNo(EncryptService.decAES(c.getJuminNo()));
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

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()))) {
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

    @RequestMapping(value="/cms/eb14.do", method=RequestMethod.GET)
    public String eb14() {
        return "cms/eb14";
    }

    // TODO: 에러 발생시 EB14 재등록 메시지.
    @RequestMapping(value="/cms/eb14.do", method=RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String eb14(Model model, @RequestParam("file") MultipartFile file) throws Exception {
        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        String s = IOUtils.toString(stream, "UTF-8");

        Date today = format_yyMMdd.parse(s, new ParsePosition(27));
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
        while (index < s.length()) {
          String t = s.substring(index, index + size);
          list.add(t);
          index += size;
        }
        return list;
    }

    @RequestMapping(value="/cms/eb14result.do", method=RequestMethod.GET)
    public String eb14Result(Model model) throws Exception {
        EB14ResultParam param = new EB14ResultParam();
        Calendar cal = Calendar.getInstance(); cal.add(Calendar.MONTH, -1);
        Date date1 = cal.getTime();
        Date date2 = new Date();
        param.setStartDt(format_yyyyMMdd.format(date1));
        param.setEndDt(format_yyyyMMdd.format(date2));
        param.setState("에러");
        List<Commitment> list = commitmentMapper.selectByEB14ResultParam(param);
        for (Commitment c : list)
            c.setJuminNo(EncryptService.decAES(c.getJuminNo()));
        model.addAttribute("param", param);
        model.addAttribute("list", list);
        return "cms/eb14result";
    }

    @RequestMapping(value="/cms/eb14result.do", method=RequestMethod.POST)
    public String eb14Result(Model model, EB14ResultParam param) throws Exception {
        List<Commitment> list = commitmentMapper.selectByEB14ResultParam(param);
        for (Commitment c : list)
            c.setJuminNo(EncryptService.decAES(c.getJuminNo()));
        model.addAttribute("param", param);
        model.addAttribute("list", list);
        return "cms/eb14result";
    }

}











































