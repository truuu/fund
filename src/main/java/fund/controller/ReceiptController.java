package fund.controller;

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
import fund.dto.Payment;
import fund.dto.Receipt;

@Controller
public class ReceiptController {
	
	@Autowired ReceiptMapper receiptMapper;
	@Autowired PaymentMapper paymentMapper;
	
	@RequestMapping(value="/certificate/receiptList.do")
	public String receiptManage(Model model, Pagination pagination)throws Exception {
		pagination.setRecordCount(receiptMapper.selectCount(pagination));
		model.addAttribute("receiptList",receiptMapper.selectPage(pagination));
		return "certificate/receiptList";
	}
	
	@RequestMapping(value="/certificate/receiptList.do", method=RequestMethod.POST, params="cmd=deleteRct")
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
		String startdate = req.getParameter("startDate");
		String enddate = req.getParameter("endDate");
		String createdate = req.getParameter("createDate");
		String[] getY = startdate.split("-");
		
		for(int corporateID=1;corporateID<3;corporateID++){
			List<Integer> sponsorID = paymentMapper.selectDistinctSponsorID(startdate, enddate, corporateID);
			for(int i=0;i<sponsorID.size();i++){
				int rctNoInt;
				String[] rctNo = receiptMapper.getLastNo().split("-");
				if(rctNo == null){
					rctNoInt = 0;
				}
				else{
					rctNoInt = Integer.parseInt(rctNo[1]);
				}
				String newRctNo = getY[0]+"-"+String.format("%04d", rctNoInt+1);
				Receipt rct = new Receipt();
				rct.setSponsorID(sponsorID.get(i));
				rct.setCreateDate(createdate);
				rct.setNo(newRctNo);
				receiptMapper.insertByDur(rct);
				paymentMapper.issueReceiptByDur(receiptMapper.getRid(),startdate, enddate, corporateID, sponsorID.get(i));
			}
		}
		return "redirect:/certificate/receiptList.do";
	}
	
	@RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.GET)
	public String receiptByName(Model model,Pagination pagination)throws Exception {
		return "certificate/receiptByName";
	}
	
	@RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.POST, params="cmd=search")
	public String paymentListForReceipt(Model model, Payment payment,Pagination pagination,HttpServletRequest req, HttpServletResponse res)throws Exception{
		System.out.println(req.getQueryString());
		model.addAttribute("paymentList",paymentMapper.selectReceiptByName(pagination));
		return "certificate/receiptByName";
	}
	
	//영수증 개별 삭제, 검색 조건 가지고있어야하는데.
	@RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.POST, params="cmd=deleteRct")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String deleteReceipt(Payment payment,Pagination pagination,Model model, HttpServletRequest req, HttpServletResponse res)throws Exception{
		int rctId = Integer.parseInt(req.getParameter("delid"));
		paymentMapper.deleteReceiptByReceiptID(rctId);
		receiptMapper.deleteById(rctId);
		return "redirect:/certificate/receiptByName.do?"+pagination.getQueryString();
	}
	
	//영수증 발행, 동명이인있으면 발급안되야함
	@RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.POST, params="cmd=issueRct" )
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String issueReceiptByName(Model model, HttpServletRequest req,HttpServletResponse res)throws Exception{
		
		String createdate = req.getParameter("createDate");
		return "redirect:/certificate/receiptList.do";
	}
	
	
	@RequestMapping("/certificate/receiptView.do")
	public String receiptView(Model model, @RequestParam("id") int id, Pagination pagination)throws Exception{
		model.addAttribute("receipt",receiptMapper.selectReceiptView(id));
		model.addAttribute("paymentList",paymentMapper.selectByRctID(id));
		return "certificate/receiptView";
	}
	
}
