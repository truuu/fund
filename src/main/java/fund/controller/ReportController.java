package fund.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fund.mapper.CodeMapper;
import fund.mapper.CorporateMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.ReportMapper;
import fund.param.OrderBy;
import fund.param.Param;
import fund.service.C;
import fund.service.ReportBuilder3;

@Controller
public class ReportController extends BaseController {

    @Autowired ReportMapper reportMapper;
    @Autowired CodeMapper codeMapper;
    @Autowired DonationPurposeMapper donationPurposeMapper;
    @Autowired CorporateMapper corporateMapper;

    //// report1
    final static OrderBy[] report1aOrderBy = new OrderBy[] {
        new OrderBy("납입일", "ORDER BY paymentDate"),
        new OrderBy("회원번호", "ORDER BY sponsorNo, paymentDate"),
        new OrderBy("이름", "ORDER BY name, sponsorNo, paymentDate"),
        new OrderBy("회원구분2", "ORDER BY sponsorType2Name, sponsorNo, paymentDate"),
        new OrderBy("교회", "ORDER BY churchName, sponsorNo, PaymentDate"),
        new OrderBy("금액", "ORDER BY amount DESC")
    };

    final static OrderBy[] report1bOrderBy = new OrderBy[] {
        new OrderBy("회원번호", "ORDER BY sponsorNo"),
        new OrderBy("이름", "ORDER BY name"),
        new OrderBy("회원구분2", "ORDER BY sponsorType2Name"),
        new OrderBy("교회", "ORDER BY churchName"),
        new OrderBy("금액", "ORDER BY amount DESC"),
    };

    @RequestMapping(value="/report/1a", method=RequestMethod.GET)
    public String report1a(Model model) {
        addModel1(model);
        model.addAttribute("param", new Param());
        return "report/1a";
    }

    @RequestMapping(value="/report/1a", method=RequestMethod.POST, params="cmd=search")
    public String report1a(Model model, Param param) {
        addModel1(model);
        model.addAttribute("list", reportMapper.selectReport1a(param.getMap()));
        return "report/1a";
    }

    private void addModel1(Model model) {
        model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분2));
        model.addAttribute("donationPurposes", donationPurposeMapper.selectAll());
        model.addAttribute("paymentMethods", codeMapper.selectByCodeGroupId(C.코드그룹ID_정기납입방법));
        model.addAttribute("churchList", codeMapper.selectByCodeGroupId(C.코드그룹ID_소속교회));
        model.addAttribute("corporates", corporateMapper.selectAll());
        model.addAttribute("report1aOrderBy", report1aOrderBy);
    }

    @RequestMapping(value="/report/1a", method=RequestMethod.POST, params="cmd=excel")
    public void report1(Model model, Param mapParam, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HashMap<String, Object> map = mapParam.getMap();
        List<HashMap<String, Object>> list = reportMapper.selectReport1a(map);

        String id = (String)map.get("paymentMethodId");
        if (StringUtils.isBlank(id) == false) map.put("paymentMethodName", codeMapper.selectById(Integer.valueOf(id)).getCodeName());
        id = (String)map.get("sponsorType2Id");
        if (StringUtils.isBlank(id) == false) map.put("sponsorType2Name", codeMapper.selectById(Integer.valueOf(id)).getCodeName());

        id = (String)map.get("regular");
        if (id == "-1") map.put("regularString", "전체");
        else if(id == "1") map.put("regularString", "정기");
        else map.put("regularString", "비정기");

        ReportBuilder3 reportBuilder = new ReportBuilder3("payment1_list",list,"납입내역.xlsx", map, req, res);
        reportBuilder.build("xlsx");
    }

    @RequestMapping(value="/report/1b", method=RequestMethod.GET)
    public String report1b(Model model) {
        model.addAttribute("param", new Param());
        addModel1(model);
        return "report/1b";
    }

    @RequestMapping(value="/report/1b", method=RequestMethod.POST, params="cmd=search")
    public String report1b(Model model, Param param) {
        addModel1(model);
        model.addAttribute("list", reportMapper.selectReport1b(param.getMap()));
        return "report/1b";
    }

    static String[] report2Title = new String[] { "기부목적", "회원구분", "소속교회" };

    //// report2
    @RequestMapping(value="/report/2/{i}", method=RequestMethod.GET)
    public String report2a(Model model, @PathVariable("i") int i) {
        Param param = new Param();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        param.getMap().put("startDate", String.format("%d-01-01", year));
        param.getMap().put("endDate", String.format("%d-12-31", year));
        model.addAttribute("param", param);
        model.addAttribute("title", report2Title[i]);
        return "report/2";
    }

    @RequestMapping(value="/report/2/{i}", method=RequestMethod.POST, params="cmd=search")
    public String report2a(Model model, Param param, @PathVariable("i") int i) {
        switch (i) {
        case 0: model.addAttribute("list", reportMapper.selectReport2a(param.getMap())); break;
        case 1: model.addAttribute("list", reportMapper.selectReport2b(param.getMap())); break;
        case 2: model.addAttribute("list", reportMapper.selectReport2c(param.getMap())); break;
        }
        model.addAttribute("title", report2Title[i]);
        return "report/2";
    }

}
