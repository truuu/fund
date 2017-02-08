package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fund.mapper.CodeMapper;

@Controller
public class HomeController extends BaseController {

	@Autowired CodeMapper codeMapper;

    @RequestMapping("/home/index.do")
    public String index(Model model) {
        return "home/index";
    }

    @RequestMapping(value="/home/login.do", method=RequestMethod.GET)
    public String login(Model model) {
        return "home/login";
    }

    @RequestMapping("test1.do")
    public String test1(RedirectAttributes ra) {
        ra.addFlashAttribute("msg1", "hello");
        return "redirect:test2.do";
    }

    @RequestMapping("test2.do")
    public String test2(Model model) {
        model.addAttribute("msg2", "world");
        return "home/test2";
    }


}
