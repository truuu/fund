package fund.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fund.mapper.*;
import fund.dto.Pagination;
import fund.dto.Receipt;

@Controller
public class ReceiptController {
	
	@Autowired ReceiptMapper receiptMapper;
	@Autowired PaymentMapper paymentMapper;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping(value="/certificate/receiptList.do")
	public String receiptManage(Model model, Pagination pagination)throws Exception {
		pagination.setRecordCount(receiptMapper.selectCount(pagination));
		model.addAttribute("receiptList",receiptMapper.selectPage(pagination));
		return "certificate/receiptList";
	}
	
	@RequestMapping(value="/certificate/deleteReceipts.do")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String deleteReceipts(Model model, HttpServletRequest req,HttpServletResponse res)throws Exception{
		String[] id= req.getParameterValues("rid");

		if(id!=null){
			for(int i=0;i<id.length;i++){
				int rctID = Integer.parseInt(id[i]);
				paymentMapper.deleteReceiptByReceiptID(rctID);
				receiptMapper.deleteById(rctID);
			}
		}
		return "redirect:/certificate/receiptList.do";
	}
	
	@RequestMapping(value="/certificate/receiptByDur.do",method=RequestMethod.GET )
	public String receiptByDuration(Model model)throws Exception{
		return "certificate/receiptByDur";
	}

	@SuppressWarnings("null")
	@RequestMapping(value="/certificate/receiptByDur.do",method=RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String issueReceiptByDuration(Model model,HttpServletRequest req,HttpServletResponse res)throws Exception{

		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		Date createDate = format.parse(req.getParameter("createDate"));
		
		for(int corporateID=1;corporateID<3;corporateID++){
			List<Integer> sponsorID = paymentMapper.selectDistinctSponsorID(startDate, endDate, corporateID);
			for(int i=0;i<sponsorID.size();i++){
				Receipt rct = null;
				rct.setSponsorID(sponsorID.get(i));
				rct.setCreateDate(createDate);
				receiptMapper.insertByDur(rct);
				paymentMapper.issueReceiptByDur(startDate, endDate, corporateID, sponsorID.get(i));
			}
		}
		return "redirect:/certificate/receiptList.do";
	}
	
	@RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.GET)
	public String receiptByName(Model model)throws Exception {
		return "certificate/receiptByName";
	}
	
	@RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.POST)
	public String paymentListForReceipt(Model model, HttpServletRequest req, HttpServletResponse res)throws Exception{
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		String name = req.getParameter("name");
		String corporateID = req.getParameter("corporateID");
		
		model.addAttribute("paymentList",paymentMapper.selectReceiptByName(startDate, endDate, name, corporateID));
		System.out.println(paymentMapper.selectReceiptByName(startDate,endDate,name,corporateID));
		return "certificate/receiptByName";
	}
	
	@RequestMapping("/certificate/issueReceipt2.do")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String issueReceiptByName(Model model){
		return "certificate/receiptList";
	}
	
	@RequestMapping("/certificate/delete.do") 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String deleteReceiptById(@RequestParam("id") int id, Model model)throws Exception{
		paymentMapper.deleteReceiptByReceiptID(id);
		receiptMapper.deleteById(id);
		return "certificate/receiptByName";
	}
	
	@RequestMapping("/certificate/receiptView.do")
	public String receiptView(Model model, @RequestParam("id") int id, Pagination pagination)throws Exception{
		Receipt receipt = receiptMapper.selectById(id);
		model.addAttribute("receipt", receipt);
		return "certificate/receiptView";
	}
	

}
