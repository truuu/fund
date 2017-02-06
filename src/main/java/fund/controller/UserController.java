package fund.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.Pagination;
import fund.dto.User;
import fund.mapper.UserMapper;
import fund.service.UserService;


@Controller
public class UserController extends BaseController{

	@Autowired UserMapper userMapper;
	@Autowired UserService userService;


    @RequestMapping("/user/list.do")
    public String list(Model model) {
        model.addAttribute("list", userMapper.selectAll());
        return "user/list";
    }

    @RequestMapping(value="/user/edit.do", method=RequestMethod.GET)
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userMapper.selectById(id));
        return "user/edit";
    }

    @RequestMapping(value="/user/edit.do", method=RequestMethod.POST, params="cmd=saveInfo")
    public String saveInfo(Model model, User user) {
        userMapper.update(user);
        model.addAttribute("successMsg", "저장되었습니다.");
        return "user/edit";
    }

    @RequestMapping(value="/user/edit.do", method=RequestMethod.POST, params="cmd=savePassword")
    public String savePassword(Model model, User user) {
        if (comparePassword(user)) {
            user.setPassword(UserService.encryptPasswd(user.getPassword1()));
            userMapper.updatePassword(user);
            model.addAttribute("successMsg", "저장되었습니다.");
        } else
            model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
        return "user/edit";
    }

    private boolean comparePassword(User user) {
        return StringUtils.isBlank(user.getPassword1()) == false && user.getPassword1().equals(user.getPassword2());
    }

    @RequestMapping(value="/user/edit.do", method=RequestMethod.POST, params="cmd=delete")
    public String delete(Model model, @RequestParam("id") int id, Pagination pagination) {
        userMapper.delete(id);
        return "redirect: list.do";
    }

    @RequestMapping(value="/user/create.do", method=RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user/create";
    }

    @RequestMapping(value="/user/create.do", method=RequestMethod.POST)
    public String create(Model model, User user) {
        if (comparePassword(user)) {
            user.setPassword(UserService.encryptPasswd(user.getPassword1()));
            userMapper.insert(user);
            return "redirect: list.do";
        } else
            model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
        return "user/create";
    }

}