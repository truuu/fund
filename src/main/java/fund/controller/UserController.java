package fund.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import fund.dto.User;
import fund.dto.pagination.Pagination;
import fund.mapper.UserMapper;
import fund.service.UserService;


@Controller
@Secured("ROLE_시스템관리자")
public class UserController extends BaseController{

	@Autowired UserMapper userMapper;
	@Autowired UserService userService;

    @RequestMapping("/user/list.do")
    public String list(Model model) {
        model.addAttribute("list", userMapper.selectAll());
        return "user/list";
    }

    boolean 사용자정보를수정할권한이있는가(int userId) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.isUserInRole("ROLE_시스템관리자")  || UserService.getCurrentUser().getId() == userId;
    }

    @Secured("ROLE_전체")
    @RequestMapping(value="/user/myinfo.do", method=RequestMethod.GET)
    public String myinfo() {
        return "redirect:edit.do?id=" + UserService.getCurrentUser().getId();
    }

    @Secured("ROLE_전체")
    @RequestMapping(value="/user/edit.do", method=RequestMethod.GET)
    public String edit(Model model, @RequestParam("id") int id) {
        if (사용자정보를수정할권한이있는가(id) == false) return "redirect:/home/logout.do";
        model.addAttribute("user", userMapper.selectById(id));
        return "user/edit";
    }

    @Secured("ROLE_전체")
    @RequestMapping(value="/user/edit.do", method=RequestMethod.POST, params="cmd=saveInfo")
    public String saveInfo(Model model, User user) {
        if (사용자정보를수정할권한이있는가(user.getId()) == false) return "redirect:/home/logout.do";
        if (user.getUserType() == null) user.setUserType(UserService.getCurrentUser().getUserType());
        userMapper.update(user);
        model.addAttribute("successMsg", "저장되었습니다.");
        UserService.setCurrentUser(user);
        return "user/edit";
    }

    @Secured("ROLE_전체")
    @RequestMapping(value="/user/edit.do", method=RequestMethod.POST, params="cmd=savePassword")
    public String savePassword(Model model, User user) {
        if (사용자정보를수정할권한이있는가(user.getId()) == false) return "redirect:/home/logout.do";
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
        return "redirect:list.do";
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
            return "redirect:list.do";
        } else
            model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
        return "user/create";
    }

}