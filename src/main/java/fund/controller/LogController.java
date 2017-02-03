package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import fund.dto.Pagination;
import fund.mapper.LogMapper;

@Controller
public class LogController extends BaseController {

    @Autowired LogMapper logMapper;

    @RequestMapping("/log/list.do")
    public String list(Model model, Pagination pagination) {
        pagination.setRecordCount(logMapper.selectCount(pagination));
        model.addAttribute("list", logMapper.selectPage(pagination));
        return "log/list";
    }


}
