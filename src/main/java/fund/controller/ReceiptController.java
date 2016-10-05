package fund.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import fund.BaseController;
import fund.mapper.*;
import fund.dto.Corporate;
import fund.dto.Pagination;
import fund.dto.Payment;
import fund.dto.Receipt;
import fund.service.*;
import net.sf.jasperreports.engine.JRException;

@Controller
public class ReceiptController extends BaseController{
	
	@Autowired ReceiptMapper receiptMapper;
	@Autowired PaymentMapper paymentMapper;
	@Autowired CorporateMapper corporateMapper;
	@Autowired SimpleDriverDataSource dataSource;
		
	@Autowired ReceiptService receiptService;
	
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
	
	@RequestMapping(value="/certificate/receiptList.do", method=RequestMethod.POST, params="cmd=rct")
    public void receiptListReport(@RequestParam("rid") int[] rid,  
            HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        List<Receipt> list = new ArrayList<Receipt>();
        for(int i=0;i<rid.length;i++){
        	list.add(receiptMapper.selectById(rid[i]));
        }
        if (list.size() > 0) { // 영수증목록이 1개 이상인 경우
            stringBuilder.append("WHERE r.id IN ("); // payment
            for (Receipt r : list) {
                r.setPaymentList(paymentMapper.selectByRctID(r.getID()));
                stringBuilder.append(r.getID()).append(',');
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append(')');
        }
        ReportBuilder2 reportBuilder = new ReportBuilder2("donationReceipt","donationReceipt.pdf",request, response);
        reportBuilder.setConnection(dataSource.getConnection());
        reportBuilder.setParameter("whereClause", stringBuilder.toString());
        reportBuilder.addSubReport("paymentList.jasper");
        reportBuilder.build("pdf");
    }

	@RequestMapping(value="/report/receiptView.do")
	public void receiptReport(Model model, @RequestParam("id") int id,HttpServletRequest req,HttpServletResponse res)throws JRException, IOException, SQLException{
		List<Receipt> list = new ArrayList<Receipt>();
		list.add(receiptMapper.selectById(id));
		for(Receipt r : list)
			r.setPaymentList(paymentMapper.selectByRctID(id));
		String whereClause = "WHERE r.id="+id;
		ReportBuilder2 reportBuilder = new ReportBuilder2("donationReceipt","Receipt.pdf",req,res);
		reportBuilder.setConnection(dataSource.getConnection());
		reportBuilder.setParameter("whereClause", whereClause);
        reportBuilder.addSubReport("paymentList.jasper");
        reportBuilder.build("pdf");		
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
				if(receiptMapper.getLastNo(getY[0])==null)
					rctNoInt=0;
				else{
					String[] rctNo = receiptMapper.getLastNo(getY[0]).split("-");
					rctNoInt = Integer.parseInt(rctNo[1]);
				}
				String newRctNo = getY[0]+"-"+String.format("%04d", rctNoInt+1);
				Receipt rct = new Receipt();
				rct.setSponsorID(sponsorID.get(i));
				rct.setCreateDate(createdate);
				rct.setNo(newRctNo);
				receiptMapper.insert(rct);
				paymentMapper.issueReceiptByDur(receiptMapper.getRid(),startdate, enddate, corporateID, sponsorID.get(i));
			}
		}
		return "redirect:/certificate/receiptList.do";
	}
	
	@RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.GET)
	public String receiptByName(Model model,Pagination pagination)throws Exception {
		model.addAttribute("paymentList",paymentMapper.selectReceiptByName(pagination));
		model.addAttribute("corporates",corporateMapper.selectCorporate());
		return "certificate/receiptByName";
	}
	
	@RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.POST, params="cmd=search")
	public String paymentListForReceipt(Model model, Payment payment,Pagination pagination,HttpServletRequest req, HttpServletResponse res)throws Exception{
		model.addAttribute("paymentList",paymentMapper.selectReceiptByName(pagination));
		model.addAttribute("corporates",corporateMapper.selectCorporate());
		return "certificate/receiptByName";
	}
	
	@RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.POST, params="cmd=deleteRct")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String deleteReceipt(Payment payment,Pagination pagination,Model model, HttpServletRequest req, HttpServletResponse res)throws Exception{
		int rctId = Integer.parseInt(req.getParameter("delid"));
		paymentMapper.deleteReceiptByReceiptID(rctId);
		receiptMapper.deleteById(rctId);
		model.addAttribute("corporates",corporateMapper.selectCorporate());
		return "redirect:/certificate/receiptByName.do?"+pagination.getQueryString();
	}
	
	@RequestMapping(value="/certificate/receiptByName.do", method=RequestMethod.POST, params="cmd=issueRct" )
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String issueReceiptByName(Model model,Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws Exception{
		String[] pID = req.getParameterValues("pid");
		String startdate = pagination.getStartDate();
		String message = receiptService.validateBeforeInsert(pID);
		model.addAttribute("corporates",corporateMapper.selectCorporate());

		if(message == null){
			//영수증생성
			String[] getY = startdate.split("-");			
			int rctNoInt;

			if(receiptMapper.getLastNo(getY[0])==null)
				rctNoInt=0;
			else{
				String[] rctNo = receiptMapper.getLastNo(getY[0]).split("-");
				rctNoInt = Integer.parseInt(rctNo[1]);
			}

			String newRctNo = getY[0]+"-"+String.format("%04d", rctNoInt+1);

			Receipt rct = new Receipt();
			rct.setSponsorID(paymentMapper.selectById(pID[0]));
			rct.setCreateDate(req.getParameter("createDate"));
			rct.setNo(newRctNo);
			receiptMapper.insert(rct);
			int rctID = receiptMapper.selectRctID();
			for(int i=0;i<pID.length;i++){
				paymentMapper.issueReceiptByName(rctID,pID[i]);
			}
			
		} else{
			model.addAttribute("error",message);
			return "redirect:/certificate/receiptByName.do?"+pagination.getQueryString();
		}
		
		return "redirect:/certificate/receiptList.do";
	}
	
	
	@RequestMapping("/certificate/taxData.do")
	public String taxData(Model model,Pagination pagination)throws Exception{
		pagination.setRecordCount(paymentMapper.selectCount(pagination));
		model.addAttribute("taxDataList",paymentMapper.selectPage(pagination));
		model.addAttribute("corporates",corporateMapper.selectCorporate());
		return "certificate/taxData";
	}

	@RequestMapping(value="/certificate/taxData.do", method=RequestMethod.POST, params="cmd=xlsx" )
	public void taxDataReport(Pagination pagination, HttpServletRequest req,HttpServletResponse res)throws JRException, IOException{
 		List<Payment> list = paymentMapper.selectTaxData(pagination);
 		ReportBuilder reportBuilder = new ReportBuilder("taxData",list,"taxData.xlsx",req,res);
		reportBuilder.build("xlsx");
 	}
	
}
