package fund.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.User;
import fund.dto.pagination.Pagination;
import fund.mapper.UserMapper;
import fund.service.C;
import fund.service.LogService;
import fund.service.UserService;


@Controller
public class UserController extends BaseController{

	@Autowired UserMapper userMapper;
	@Autowired UserService userService;
    @Autowired LogService logService;

    @RequestMapping("/user/list.do")
    public String list(Model model) {
        if (!UserService.canAccess(C.메뉴_시스템관리_사용자목록)) return "redirect:/home/logout.do";
        model.addAttribute("list", userMapper.selectAll());
        return "user/list";
    }

    // 파라미터 userId는 수정하는 사용자가 아니고, 수정되는 사용자 정보
    boolean 사용자정보를수정할권한이있는가(int userId) {
        return UserService.canAccess(C.메뉴_시스템관리_사용자목록) || UserService.getCurrentUser().getId() == userId;
    }

    @RequestMapping(value="/user/myinfo.do", method=RequestMethod.GET)
    public String myinfo() {
        return "redirect:edit.do?id=" + UserService.getCurrentUser().getId();
    }

    @RequestMapping(value="/user/edit.do", method=RequestMethod.GET)
    public String edit(Model model, @RequestParam("id") int id) {
        if (사용자정보를수정할권한이있는가(id) == false) return "redirect:/home/logout.do";
        model.addAttribute("user", userMapper.selectById(id));
        return "user/edit";
    }

    @RequestMapping(value="/user/edit.do", method=RequestMethod.POST, params="cmd=saveInfo")
    public String saveInfo(Model model, User user) {
        try {
            if (사용자정보를수정할권한이있는가(user.getId()) == false) return "redirect:/home/logout.do";
            if (user.getUserType() == null) user.setUserType(UserService.getCurrentUser().getUserType());
            logService.userInfoChange(user);
            userMapper.update(user);
            model.addAttribute("successMsg", "저장되었습니다.");
            if (UserService.getCurrentUser().getId() == user.getId())
                UserService.setCurrentUser(user);
            return "user/edit";
        } catch (Exception e) {
            return logService.logErrorAndReturn(model, e, "user/edit");
        }
    }

    @RequestMapping(value="/user/edit.do", method=RequestMethod.POST, params="cmd=savePassword")
    public String savePassword(Model model, User user) {
        try {
            if (사용자정보를수정할권한이있는가(user.getId()) == false) return "redirect:/home/logout.do";
            if (userService.checkPassword(user.getPassword1())) {
                if (comparePassword(user)) {
                    user.setPassword(UserService.encryptPasswd(user.getPassword1()));
                    userMapper.updatePassword(user);
                    model.addAttribute("successMsg", "저장되었습니다.");
                } else
                    model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
            } else
                model.addAttribute("errorMsg", "비밀번호 작성 규칙에 어긋납니다.");
            return "user/edit";
        } catch (Exception e) {
            return logService.logErrorAndReturn(model, e, "user/edit");
        }
    }

    private boolean comparePassword(User user) {
        return StringUtils.isBlank(user.getPassword1()) == false && user.getPassword1().equals(user.getPassword2());
    }

    @RequestMapping(value="/user/edit.do", method=RequestMethod.POST, params="cmd=delete")
    public String delete(Model model, @RequestParam("id") int id, Pagination pagination) {
        if (사용자정보를수정할권한이있는가(id) == false) return "redirect:/home/logout.do";
        logService.userDelete(id);
        userMapper.delete(id);
        return "redirect:list.do";
    }

    @RequestMapping(value="/user/create.do", method=RequestMethod.GET)
    public String create(Model model) {
        if (사용자정보를수정할권한이있는가(0) == false) return "redirect:/home/logout.do";
        model.addAttribute("user", new User());
        return "user/create";
    }

    @RequestMapping(value="/user/create.do", method=RequestMethod.POST)
    public String create(Model model, User user) {
        try {
            if (사용자정보를수정할권한이있는가(0) == false) return "redirect:/home/logout.do";
            if (userService.checkPassword(user.getPassword1())) {
                if (comparePassword(user)) {
                    user.setPassword(UserService.encryptPasswd(user.getPassword1()));
                    logService.userCreate(user);
                    userMapper.insert(user);
                    return "redirect:list.do";
                } else
                    model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
            } else
                model.addAttribute("errorMsg", "비밀번호 작성 규칙에 어긋납니다.");
            return "user/create";
        } catch (Exception e) {
            return logService.logErrorAndReturn(model, e, "user/create");
        }
    }
}