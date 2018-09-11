package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fund.dto.pagination.Pagination;
import fund.mapper.SponsorMapper;
import fund.mapper.TodoMapper;
import fund.service.C;
import fund.service.UserService;

@Controller
public class TodoController extends BaseController {

    @Autowired SponsorMapper sponsorMapper;
    @Autowired TodoMapper todoMapper;

    @RequestMapping(value="/todo/list.do", method=RequestMethod.GET)
    public String index(Model model, Pagination pagination) {
        if (!UserService.canAccess(C.메뉴_기타_일정관리)) return "redirect:/home/logout.do";
        pagination.setRecordCount(todoMapper.selectCount(pagination));
        model.addAttribute("list", todoMapper.selectPage(pagination));
        return "todo/list";
    }
}