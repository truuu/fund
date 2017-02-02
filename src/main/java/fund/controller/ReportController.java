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
import fund.param.MapParam;
import fund.service.C;
import fund.service.ReportBuilder3;

@Controller
public class ReportController {

    @Autowired ReportMapper reportMapper;
    @Autowired CodeMapper codeMapper;
    @Autowired DonationPurposeMapper donationPurposeMapper;
    @Autowired CorporateMapper corporateMapper;

    @RequestMapping(value="/report/1", method=RequestMethod.GET)
    public String report1(Model model) {
        model.addAttribute("mapParam", new MapParam());
        addModels(model);
        return "report/1";
    }

    @RequestMapping(value="/report/1", method=RequestMethod.POST, params="cmd=search")
    public String report1(Model model, MapParam mapParam) {
        addModels(model);
        model.addAttribute("list", reportMapper.selectReport1(mapParam.getMap()));
        return "report/1";
    }

    private void addModels(Model model) {
        model.addAttribute("sponsorType2List", codeMapper.selectByCodeGroupId(C.코드그룹ID_후원인구분2));
        model.addAttribute("donationPurposes", donationPurposeMapper.selectAll());
        model.addAttribute("paymentMethods", codeMapper.selectByCodeGroupId(C.코드그룹ID_정기납입방법));
        model.addAttribute("churchList", codeMapper.selectByCodeGroupId(C.코드그룹ID_소속교회));
        model.addAttribute("corporates", corporateMapper.selectAll());
    }

    @RequestMapping(value="/report/1", method=RequestMethod.POST, params="cmd=report")
    public void report1(Model model, MapParam mapParam, HttpServletRequest req, HttpServletResponse res) throws Exception {

        HashMap<String, Object> map = mapParam.getMap();
        List<HashMap<String, Object>> list = reportMapper.selectReport1(map);

        String id = (String)map.get("paymentMethodId");
        if (StringUtils.isBlank(id) == false) map.put("paymentMethodName", codeMapper.selectById(Integer.valueOf(id)).getCodeName());

        id = (String)map.get("sponsorType2Id");
        if (StringUtils.isBlank(id) == false) map.put("sponsorType2Name", codeMapper.selectById(Integer.valueOf(id)).getCodeName());

        ReportBuilder3 reportBuilder = new ReportBuilder3("payment1_list",list,"paymentRecord_List.pdf", map, req, res);
        reportBuilder.build("pdf");
    }

}
