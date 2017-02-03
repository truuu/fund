package fund.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        addModels(model);
        model.addAttribute("param", new Param());
        return "report/1a";
    }

    @RequestMapping(value="/report/1a", method=RequestMethod.POST, params="cmd=search")
    public String report1a(Model model, Param param) {
        addModels(model);
        model.addAttribute("list", reportMapper.selectReport1a(param.getMap()));
        return "report/1a";
    }

    private void addModels(Model model) {
        model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분2));
        model.addAttribute("donationPurposes", donationPurposeMapper.selectAll());
        model.addAttribute("paymentMethods", codeMapper.selectByCodeGroupId(C.코드그룹ID_정기납입방법));
        model.addAttribute("churchList", codeMapper.selectByCodeGroupId(C.코드그룹ID_소속교회));
        model.addAttribute("corporates", corporateMapper.selectAll());
        model.addAttribute("report1aOrderBy", report1aOrderBy);
    }

    @RequestMapping(value="/report/1a", method=RequestMethod.POST, params="cmd=report")
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
        addModels(model);
        return "report/1b";
    }

    @RequestMapping(value="/report/1b", method=RequestMethod.POST, params="cmd=search")
    public String report1b(Model model, Param param) {
        addModels(model);
        model.addAttribute("list", reportMapper.selectReport1b(param.getMap()));
        return "report/1b";
    }


}
