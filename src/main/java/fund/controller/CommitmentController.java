package fund.controller;

import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.BaseController;
import fund.dto.Commitment;
import fund.dto.PaginationSponsor;
import fund.mapper.CodeMapper;
import fund.mapper.CommitmentDetailMapper;
import fund.mapper.CommitmentMapper;
import fund.mapper.DonationPurposeMapper;
import fund.mapper.SponsorMapper;
import fund.service.C;
import fund.service.Util;

@Controller
public class CommitmentController extends BaseController {

    @Autowired CommitmentMapper commitmentMapper;
    @Autowired CommitmentDetailMapper commitmentDetailMapper;
    @Autowired CodeMapper codeMapper;
    @Autowired DonationPurposeMapper donationPurposeMapper;
    @Autowired SponsorMapper sponsorMapper;

    @ModelAttribute
    void modelAttr1(@ModelAttribute("pagination") PaginationSponsor pagination) {
    }

    /* 약정목록 */
    @RequestMapping(value = "/sponsor/commitmentList.do", method = RequestMethod.GET)
    public String commitmentList(Model model, @RequestParam("sid") int sid) {
        model.addAttribute("sponsor", sponsorMapper.selectById(sid));
        model.addAttribute("list", commitmentMapper.selectBySponsorId(sid));
        model.addAttribute("paymentMethodList", codeMapper.selectByCodeGroupID(C.코드그룹ID_정기납입방법));
        model.addAttribute("donationPurposeList", donationPurposeMapper.selectAll());
        model.addAttribute("bankList", codeMapper.selectByCodeGroupID(C.코드그룹ID_은행));
        return "sponsor/commitmentList";
    }

    /* 약정 수정 */
    @RequestMapping(value="/sponsor/commitmentEdit.do", method=RequestMethod.GET)
    public String commitmentEdit(Model model, @RequestParam("cid") int cid, @RequestParam("sid") int sid) throws ParseException {
       model.addAttribute("sponsor", sponsorMapper.selectById(sid));
       model.addAttribute("commitment", commitmentMapper.selectById(cid));
       model.addAttribute("donationPurposes", donationPurposeMapper.selectAll());
       model.addAttribute("paymentMethods", codeMapper.selectByCodeGroupID(C.코드그룹ID_정기납입방법));
       return "sponsor/commitmentEdit";
    }

    private String redirectToList(Model model, int sid) throws Exception {
        PaginationSponsor pagination = (PaginationSponsor)model.asMap().get("pagination");
        String qs = String.format("id=%d&%s", sid, pagination.getQueryString());
        return "redirect:commitmentList.do?" + qs;
    }

    /* 약정 저장 */
    @RequestMapping(value="/sponsor/commitmentEdit.do", method=RequestMethod.POST, params="cmd=save")
    public String commitmentSave(Model model, @RequestParam("sid") int sid, Commitment commitment) throws Exception {
        commitmentMapper.update(commitment);
        return redirectToList(model, sid);
    }

    /* 약정 종료 */
    @RequestMapping(value="/sponsor/commitmentEdit.do", method=RequestMethod.POST, params="cmd=close")
    public String commitmentClose(Model model, @RequestParam("cid") int cid, @RequestParam("sid") int sid) throws Exception {
        commitmentMapper.updateEndDate(cid);
        return redirectToList(model, sid);
    }

    /* 약정 삭제 */
    @RequestMapping(value="/sponsor/commitmentEdit.do", method=RequestMethod.POST, params="cmd=delete")
    public String commitmentDelete(Model model, @RequestParam("cid") int cid, @RequestParam("sid") int sid) throws Exception {
        commitmentMapper.delete(cid);
        return redirectToList(model, sid);
    }

    /* 약정 신규 */
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
        model.addAttribute("donationPurposes", donationPurposeMapper.selectAll());
        model.addAttribute("paymentMethods", codeMapper.selectByCodeGroupID(C.코드그룹ID_정기납입방법));
        model.addAttribute("banks", codeMapper.selectByCodeGroupID(C.코드그룹ID_은행));
        return "sponsor/commitmentNew";
    }

    /* 약정 신규 저장 */
    @RequestMapping(value="/sponsor/commitmentNew.do", method=RequestMethod.POST)
    public String commitmentNewSave(Model model, @RequestParam("sid") int sid, Commitment commitment) throws Exception {
        try {
            commitment.setCommitmentNo(commitmentMapper.generateCommitmentNo(sid));
            commitment.setSponsorId(sid);
            if (StringUtils.isBlank(commitment.getEndDate())) commitment.setEndDate(null);
            commitmentMapper.insert(commitment);
            return redirectToList(model, sid);
        } catch (DataIntegrityViolationException e) {
            //String msg = e.getMessage();
            //Pattern r = Pattern.compile("@&([^)]+)&@");
            //Matcher m = r.matcher(msg);
            //if (m.find()) System.out.println(m.group(1));

            String msg = e.getMessage();
            System.out.println(msg);

            return redirectToList(model, sid);
        }
    }


}