package fund.controller;

import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.Commitment;
import fund.dto.PaginationSponsor;
import fund.mapper.CodeMapper;
import fund.mapper.CommitmentDetailMapper;
import fund.mapper.CommitmentMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.SponsorMapper;
import fund.service.C;
import fund.service.LogService;
import fund.service.Util;

@Controller
public class CommitmentController extends BaseController {

    @Autowired CommitmentMapper commitmentMapper;
    @Autowired CommitmentDetailMapper commitmentDetailMapper;
    @Autowired CodeMapper codeMapper;
    @Autowired DonationPurposeMapper donationPurposeMapper;
    @Autowired SponsorMapper sponsorMapper;
    @Autowired LogService logService;

    @ModelAttribute
        void modelAttr1(@ModelAttribute("pagination") PaginationSponsor pagination,
                        @RequestParam("sid") int sid, Model model) {
        model.addAttribute("sponsor", sponsorMapper.selectById(sid));
        model.addAttribute("donationPurposes", donationPurposeMapper.selectAll());
        model.addAttribute("paymentMethods", codeMapper.selectByCodeGroupId(C.코드그룹ID_정기납입방법));
        model.addAttribute("banks", codeMapper.selectByCodeGroupId(C.코드그룹ID_은행));
    }

    /* 약정 목록 화면 */
    @RequestMapping(value = "/sponsor/commitmentList.do", method = RequestMethod.GET)
    public String commitmentList(Model model, @RequestParam("sid") int sid) {
        model.addAttribute("list", commitmentMapper.selectBySponsorId(sid));
        return "sponsor/commitmentList";
    }

    private String redirectToList(Model model, int sid) throws Exception {
        PaginationSponsor pagination = (PaginationSponsor)model.asMap().get("pagination");
        String qs = String.format("sid=%d&%s", sid, pagination.getQueryString());
        return "redirect:commitmentList.do?" + qs;
    }


    /* 약정 수정 화면 */
    @RequestMapping(value="/sponsor/commitmentEdit.do", method=RequestMethod.GET)
    public String commitmentEdit(Model model, @RequestParam("id") int id) throws ParseException {
       model.addAttribute("commitment", commitmentMapper.selectById(id));
       return "sponsor/commitmentEdit";
    }

    @RequestMapping(value="/sponsor/commitmentEdit.do", method=RequestMethod.POST)
    public String commitmentSave(Model model, @RequestParam("sid") int sid, Commitment commitment, @RequestParam("cmd") String cmd) throws Exception {
        try {
            switch (cmd) {
            case "save":  commitmentMapper.update(commitment); break;
            case "close":  commitmentMapper.updateEndDate(commitment.getId()); break;
            case "delete": commitmentMapper.delete(commitment.getId()); break;
            }
            return redirectToList(model, sid);
        } catch (Exception e) {
            return logService.logErrorAndReturn(model, e, "sponsor/commitmentEdit");
        }
    }

    /* 약정 신규 화면 */
    @RequestMapping(value="/sponsor/commitmentNew.do", method=RequestMethod.GET)
    public String commitmentNew(Model model, @RequestParam("sid") int sid) throws ParseException {
        Commitment commitment = new Commitment();
        commitment.setSponsorId(sid);
        commitment.setCommitmentNo(commitmentMapper.generateCommitmentNo(sid));
        String today = Util.toYMD(new Date());
        commitment.setCommitmentDate(today);
        commitment.setStartDate(today);
        commitment.setPaymentDay(20);
        model.addAttribute("commitment", commitment);
        model.addAttribute("sponsor", sponsorMapper.selectById(sid));
        return "sponsor/commitmentNew";
    }

    @RequestMapping(value="/sponsor/commitmentNew.do", method=RequestMethod.POST)
    public String commitmentNewSave(Model model, @RequestParam("sid") int sid, Commitment commitment) throws Exception {
        try {
            commitment.setCommitmentNo(commitmentMapper.generateCommitmentNo(sid));
            commitment.setSponsorId(sid);
            if (StringUtils.isBlank(commitment.getEndDate())) commitment.setEndDate(null);
            commitmentMapper.insert(commitment);
            return redirectToList(model, sid);
        } catch (Exception e) {
            return logService.logErrorAndReturn(model, e, "sponsor/commitmentNew");
        }
    }


}