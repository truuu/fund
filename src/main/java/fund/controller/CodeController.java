package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.Code;
import fund.mapper.CodeMapper;

@Controller
public class CodeController extends BaseController{

    @Autowired CodeMapper codeMapper;

    @RequestMapping("/code/list.do")
    public String list(Model model, @RequestParam("gid") int codeGroupId) {
        model.addAttribute("list", codeMapper.selectByCodeGroupId(codeGroupId));
        model.addAttribute("codeGroup", codeMapper.selectCodeGroupById(codeGroupId));
        return "code/list";
    }

    @RequestMapping(value="/code/create.do", method=RequestMethod.GET)
    public String create(Model model, @RequestParam("gid") int codeGroupId) {
        Code code = new Code();
        code.setCodeGroupId(codeGroupId);
        model.addAttribute("code", code);
        model.addAttribute("codeGroup", codeMapper.selectCodeGroupById(codeGroupId));
        return "code/edit";
    }

    @RequestMapping(value="/code/create.do", method=RequestMethod.POST)
    public String create(Code code, @RequestParam("gid") int codeGroupId) {
        codeMapper.insert(code);
        return "redirect:list.do?gid=" + codeGroupId;
    }

    @RequestMapping(value="/code/edit.do", method=RequestMethod.GET)
    public String edit(Model model, @RequestParam("id") int id, @RequestParam("gid") int codeGroupId) {
        model.addAttribute("code", codeMapper.selectById(id));
        model.addAttribute("codeGroup", codeMapper.selectCodeGroupById(codeGroupId));
        return "code/edit";
    }

    @RequestMapping(value="/code/edit.do", method=RequestMethod.POST, params="cmd=save")
    public String edit(Model model, Code code, @RequestParam("gid") int codeGroupId) {
        codeMapper.update(code);
        model.addAttribute("codeGroup", codeMapper.selectCodeGroupById(codeGroupId));
        return "redirect:list.do?gid=" + codeGroupId;
    }

    @RequestMapping(value="/code/edit.do", method=RequestMethod.POST, params="cmd=delete")
    public String delete(Model model, @RequestParam("id") int id, @RequestParam("gid") int codeGroupId) {
        codeMapper.delete(id);
        return "redirect:list.do?gid=" + codeGroupId;
    }

}
