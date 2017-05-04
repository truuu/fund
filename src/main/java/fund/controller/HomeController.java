package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.Todo;
import fund.mapper.SponsorMapper;
import fund.mapper.TodoMapper;
import fund.service.UserService;

@Controller
public class HomeController extends BaseController {

	@Autowired SponsorMapper sponsorMapper;
	@Autowired TodoMapper todoMapper;

    @RequestMapping(value="/home/index.do", method=RequestMethod.GET)
    public String index(Model model) {
        if (UserService.getCurrentUser() == null)
            return "redirect:/home/login.do";
        if (UserService.isUserInRole("시스템관리자", "관리자"))
            model.addAttribute("list", todoMapper.selectList());
        return "home/index";
    }

    @RequestMapping(value="/home/index.do", method=RequestMethod.POST, params="cmd=delete")
    public String index_delete(Model model, @RequestParam("id") int id) {
        if (UserService.isUserInRole("시스템관리자", "관리자")) {
            todoMapper.delete(id);
            model.addAttribute("list", todoMapper.selectList());
        }
        return "home/index";
    }

    @RequestMapping(value="/home/index.do", method=RequestMethod.POST, params="cmd=create")
    public String index_create(Model model, Todo todo) {
        if (UserService.isUserInRole("시스템관리자", "관리자")) {
            todo.setUserId(UserService.getCurrentUser().getId());
            todoMapper.insert(todo);
            model.addAttribute("list", todoMapper.selectList());
        }
        return "home/index";
    }

    @RequestMapping(value="/home/login.do", method=RequestMethod.GET)
    public String login(Model model) {
        return "home/login";
    }

    @RequestMapping("/home/test1.do")
    public String test1(Model model) {
        return "home/test1";
    }
}