package fund;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping("/home/index.do")
    public String index(Model model) {
        return "home/index";
    }

    @RequestMapping(value="/home/login.do", method=RequestMethod.GET)
    public String login(Model model) {
        return "home/login";
    }
    
    @RequestMapping(value="/code/code.do", method=RequestMethod.GET)
    public String code(Model model) {
        return "code/code";
    }
    
    @RequestMapping(value="/user/agreement.do", method=RequestMethod.GET)
    public String agreement(Model model) {
        return "user/agreement";
    }
    
    @RequestMapping(value="/certificate/donation_cer.do", method=RequestMethod.GET)
    public String donation_cer(Model model) {
        return "certificate/donation_cer";
    }
    
    @RequestMapping(value="/certificate/scholarship_cer.do", method=RequestMethod.GET)
    public String scholarship_cer(Model model) {
        return "certificate/scholarship_cer";
    }
    
    @RequestMapping(value="/certificate/donation_cer_list.do", method=RequestMethod.GET)
    public String donation_cer_list(Model model) {
        return "certificate/donation_cer_list";
    }
    
    @RequestMapping(value="/certificate/scholarship_cer_list.do", method=RequestMethod.GET)
    public String scholarship_cer_list(Model model) {
        return "certificate/scholarship_cer_list";
    }
    
    @RequestMapping(value="/agreement/stats.do", method=RequestMethod.GET)
    public String stats(Model model) {
        return "agreement/stats";
    }
    
    @RequestMapping(value="/agreement/cmscheck.do", method=RequestMethod.GET)
    public String cmscheck(Model model) {
        return "agreement/cmscheck";
    }
   
}

