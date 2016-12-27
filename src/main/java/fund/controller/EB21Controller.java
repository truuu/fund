package fund.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fund.BaseController;
import fund.dto.EB14;
import fund.dto.EB21_commitmentDetail;
import fund.dto.EB22;
import fund.dto.Payment;
import fund.dto.XferResult;
import fund.mapper.CommitmentDetailMapper;
import fund.mapper.EB21Mapper;
import fund.mapper.EB21_CommitmentDetailMapper;
import fund.mapper.PaymentMapper;
import fund.mapper.SponsorMapper;
import fund.service.FileExtFilter;

@Controller
public class EB21Controller extends BaseController{
	@Autowired EB21Mapper eb21Mapper;
	@Autowired EB21_CommitmentDetailMapper eb21_commitmentDetailMapper;
	@Autowired PaymentMapper paymentMapper;
	@Autowired SponsorMapper sponsorMapper;
	@Autowired CommitmentDetailMapper commitmentDetailMapper;
	@Autowired FileExtFilter fileExtFilter;

	@RequestMapping(value="/finance/eb21.do", method=RequestMethod.GET)
	public String eb21(Model model) {
		return "finance/eb21";
	}

	@RequestMapping(value="/finance/eb21.do", method=RequestMethod.POST, params="cmd=selectEB21List")
	public String selectEB21(@RequestParam("paymentDay") int paymentDay,Model model) throws Exception{
		List<EB21_commitmentDetail> eb21List = commitmentDetailMapper.selectEB21(paymentDay);
		for(int i = 0; i < eb21List.size(); i++){
			if(eb21List.get(i).getSponsorType1ID() == 3 || eb21List.get(i).getSponsorType1ID() == 4){
				String decoding = eb21List.get(i).getJumin2();//사업자등록번호 디코딩
				eb21List.get(i).setJumin(decoding);
			}else{
				String decoding = eb21List.get(i).getJumin2();//주민번호 디코딩
				eb21List.get(i).setJumin(decoding.substring(0, 6));
			}
		}
		
		model.addAttribute("eb21List", eb21List);
		model.addAttribute("paymentDay", paymentDay);

		return "finance/eb21";
	}
	@RequestMapping(value="/finance/eb21.do", method=RequestMethod.POST, params="cmd=createEB21file")
	public String createEB21file(@RequestParam("paymentDay") int paymentDay,@RequestParam("paymentDate") String paymentDate_old,@RequestParam("commitmentDetailID") int[] commitmentDetailID,Model model) throws Exception{
		List<EB21_commitmentDetail> eb21List = commitmentDetailMapper.selectEB21(paymentDay);
		for(int i = 0; i < eb21List.size(); i++){
			String decoding = eb21List.get(i).getJumin2();
			eb21List.get(i).setJumin(decoding.substring(0, 6));
		}
		model.addAttribute("eb21List",eb21List);

		CreateEB21File.createEB21File(eb21List,paymentDate_old);//EB21파일생성.

		eb21Mapper.createEB21file(paymentDate_old);
		for(int i=0 ; i<commitmentDetailID.length; ++i){
			eb21_commitmentDetailMapper.createEB21List(commitmentDetailID[i]);
		}
		model.addAttribute("successMsg", "EB21 파일 생성을 완료했습니다."); 
		return "finance/eb21";
	}

	@RequestMapping(value="/finance/eb22.do", method=RequestMethod.GET)
	public String eb22(Model model) {
		return "finance/eb22";
	}

	@RequestMapping(value="/finance/uploadEB22.do", method=RequestMethod.GET)
	public String uploadEB22(Model model) {
		return "finance/uploadEB22";
	}

	@RequestMapping(value="/finance/uploadEB22.do", method=RequestMethod.POST)
	public String uploadEB22(Model model,@RequestParam("file") MultipartFile uploadedFile,HttpSession session) throws IOException {
		if(fileExtFilter.badFileExtIsReturnBoolean(uploadedFile) == true){ // 파일 확장자 필터링.
			if (uploadedFile.getSize() > 0 ) {
				byte[] bytes = uploadedFile.getBytes();
				String fileName = "/Users/parkeunsun/Documents/"+uploadedFile.getOriginalFilename();
				File tempFile = new File(fileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tempFile));
				stream.write(bytes);
				stream.close();

				ArrayList<String> eb22file = ReadEB22File.readEB22File(fileName);
				List<EB22> eb22List = new ArrayList<EB22>();
				for(String i : eb22file){
					EB22 eb22 = new EB22();
					String getList = i;
					String sub = getList.substring(19);
					eb22.setBankCode(sub.substring(0, 7));
					eb22.setAccountNo(sub.substring(7, 23).trim());
					int amount = Integer.parseInt(sub.substring(23, 36).trim());//돈 앞에 0 제거.
					eb22.setAmountPerMonth(amount);
					eb22.setJumin(sub.substring(36,49).trim());
					eb22.setErrorCode(sub.substring(50, 54));
					String sponsorNo = sub.substring(72, 92).trim();
					eb22.setSponsorNo(sponsorNo);
					StringBuffer sNo = new StringBuffer(sponsorNo);
					sNo.insert(4,"-");//후원인번호 가운데 "-" 추가
					EB22 name = sponsorMapper.selectSponsorName(sNo.toString());//후원인번호에 맞는 이름 가져오기.
					eb22.setName(name.getName());

					eb22List.add(eb22);
				}

				model.addAttribute("eb22List",eb22List);
				session.setAttribute("eb22ListSession",eb22List);
				session.setAttribute("fileName", fileName);
				return "finance/eb22";
			}
		}else{
			model.addAttribute("errorMsg", "EB파일을 업로드 해 주세요."); 
		}
		return "finance/uploadEB22";
	}

	@RequestMapping(value="/finance/uploadEB22.do", method=RequestMethod.POST, params="cmd=updateEB22")
	public String updateEB22(HttpSession session,Model model) throws ParseException {
		String fileName = (String) session.getAttribute("fileName");
		String date = ReadEB22Date.readEB22Date(fileName);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date paymentDate = format.parse(date);

		List<EB22> eb22List = (List<EB22>) session.getAttribute("eb22ListSession");
		if(eb22List.isEmpty()){
			eb21_commitmentDetailMapper.updateEB21success(paymentDate);
		}else{
			for (int i=0; i<eb22List.size();i++) {
				EB22 x = eb22List.get(i);
				String sponsorNo = x.getSponsorNo();
				String errorCode = x.getErrorCode();
				StringBuffer sNo = new StringBuffer(sponsorNo);
				sNo.insert(4,"-");
				eb21_commitmentDetailMapper.updateEB21error(sNo.toString(),paymentDate,errorCode);
				eb21_commitmentDetailMapper.updateEB21success(paymentDate);
			}
		}

		List<Payment> successList = eb21_commitmentDetailMapper.selectEB21success();//'성공'상태의 payment데이터리스트
		for(Payment i : successList){
			paymentMapper.insertEB21Payment(i);
		}//payment테이블에 납입 업데이트
		model.addAttribute("successMsg", "EB22 파일 적용을 완료했습니다."); 
		return "finance/eb22";
	}
	@RequestMapping(value="/finance/resultEB2122.do", method=RequestMethod.GET)
	public String resultEB2122(Model model) {
		return "finance/resultEB2122";
	}

	@RequestMapping(value="/finance/resultEB2122.do", method=RequestMethod.POST)
	public String resultEB2122(Model model, @RequestParam String startDate,@RequestParam String endDate) {
		List<EB21_commitmentDetail> eb2122List = eb21_commitmentDetailMapper.selectEB2122(startDate, endDate);
		model.addAttribute("eb2122List", eb2122List);
		return "finance/resultEB2122";
	}
}

