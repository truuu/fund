package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fund.mapper.SponsorMapper;

@Controller
public class HomeController extends BaseController {

	@Autowired SponsorMapper sponsorMapper;

    @RequestMapping("/home/index.do")
    public String index(Model model) {
        return "home/index";
    }

    @RequestMapping(value="/home/login.do", method=RequestMethod.GET)
    public String login(Model model) {
        return "home/login";
    }

    @RequestMapping("/home/test1.do")
    public String test1(Model model) {
        //model.addAttribute("key2", sponsorMapper.selectKey2());
        return "home/test1";
    }

}
