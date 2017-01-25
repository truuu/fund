package fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fund.dto.Commitment;
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

    @RequestMapping("/home/test1.do")
    public String test1(Model model) {
        Data d = new Data();
        d.setId(123);
        model.addAttribute("d", d);

        Commitment c = new Commitment();
        c.setId(123);
        model.addAttribute("c", c);
        return "home/test1";
    }


}
