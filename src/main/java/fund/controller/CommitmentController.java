package fund.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommitmentController {
	@RequestMapping(value="/user/commitment.do", method=RequestMethod.GET)  
    public String agreement(Model model) {
        return "user/commitment";
    }
	
}
