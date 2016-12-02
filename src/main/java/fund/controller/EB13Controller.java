package fund.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fund.BaseController;
import fund.dto.EB13;
import fund.dto.EB13_CommitmentDetail;
import fund.dto.EB14;
import fund.dto.XferResult;
import fund.mapper.CommitmentDetailMapper;
import fund.mapper.EB13Mapper;
import fund.mapper.EB13_CommitmentDetailMapper;
import fund.service.AES128UtilService;
import fund.service.FileExtFilter;


@Controller
public class EB13Controller extends BaseController{
	@Autowired EB13Mapper eb13Mapper;
	@Autowired EB13_CommitmentDetailMapper eb13_commitmentDetailMapper;
	@Autowired CommitmentDetailMapper commitmentDetailMapper;
	@Autowired FileExtFilter fileExtFilter;
	@Autowired AES128UtilService cipherService;

	@RequestMapping(value="/finance/eb13.do", method=RequestMethod.GET)
	public String eb13(Model model) {
		return "finance/eb13";
	}
	
	@RequestMapping(value="/finance/eb13.do", method=RequestMethod.POST, params="cmd=selectEB13")
	   public String selectEB13(Model model)throws Exception{
	      List<EB13_CommitmentDetail> eb13List = commitmentDetailMapper.selectEB13();
	      for(int i = 0; i < eb13List.size(); i++){
	    	  if(eb13List.get(i).getSponsorType1ID() == 3 || eb13List.get(i).getSponsorType1ID() == 4){
	    		 String decoding = eb13List.get(i).getJumin2();//사업자등록번호 디코딩
	 	         eb13List.get(i).setJumin(decoding);
	    	  }else{
	    		 String decoding = eb13List.get(i).getJumin2();//주민번호 디코딩
	 	         eb13List.get(i).setJumin(decoding.substring(0, 6));
	    	  }
	      }
	      model.addAttribute("eb13List", eb13List);
	      return "finance/eb13";
	   }
	
	@RequestMapping(value="/finance/eb13.do", method=RequestMethod.POST, params="cmd=createEB13file")
	public String createEB13file(@RequestParam("commitmentDetailID") int[] commitmentDetailID,Model model) throws Exception{
		List<EB13_CommitmentDetail> eb13List = commitmentDetailMapper.selectEB13();
		for(int i = 0; i < eb13List.size(); i++){
	         String decoding = eb13List.get(i).getJumin2();
	         eb13List.get(i).setJumin(decoding.substring(0, 6));
	    }
		model.addAttribute("eb13List", eb13List);
		CreateEB13File.createEB13File(eb13List);

		eb13Mapper.createEB13file();
		for(int i = 0 ; i < commitmentDetailID.length; ++i){
			eb13_commitmentDetailMapper.createEB13list(commitmentDetailID[i]);
		}
		model.addAttribute("successMsg", "EB13 파일 생성을 완료했습니다."); 
		return "finance/eb13";
	}

	@RequestMapping(value="/finance/eb14.do", method=RequestMethod.GET)
	public String eb14(Model model) {
		return "finance/eb14";
	}

	@RequestMapping(value="/finance/uploadEB14.do", method=RequestMethod.GET)
	public String uploadEB14(Model model) {
		return "finance/uploadEB14";
	}

	@RequestMapping(value="/finance/uploadEB14.do", method=RequestMethod.POST)
	public String uploadEB14(Model model,@RequestParam("file") MultipartFile uploadedFile,HttpSession session) throws IOException, ParseException {
		if(fileExtFilter.badFileExtIsReturnBoolean(uploadedFile) == true){ // 파일 확장자 필터링.
			if (uploadedFile.getSize() > 0 ) {
				byte[] bytes = uploadedFile.getBytes();
				String fileName = "/Users/parkeunsun/Documents/"+uploadedFile.getOriginalFilename();
				File tempFile = new File(fileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tempFile));
				stream.write(bytes);
				stream.close();

				ArrayList<String> eb14file = ReadEB14File.readEB14File(fileName);
				List<EB14> eb14List = new ArrayList<EB14>();
				SimpleDateFormat format = new SimpleDateFormat("yyMMdd");

				for(String i : eb14file){
					EB14 eb14 = new EB14();
					String getList = i;
					String sub = getList.substring(19);
					String date = sub.substring(0, 6);

					eb14.setCreateDate(format.parse(date));
					eb14.setSponsorNo(sub.substring(7, 26).trim());
					eb14.setBankCode(sub.substring(27, 33).trim());
					eb14.setAccountNo(sub.substring(34, 50).trim());
					eb14.setJumin(sub.substring(50, 67).trim());
					eb14.setErrorCode(sub.substring(73,77)); //N 다음 불능코드 4자리
					eb14List.add(eb14);
				}	
				model.addAttribute("eb14List",eb14List);
				session.setAttribute("eb14ListSession", eb14List);
				session.setAttribute("fileNameSession", fileName);
				return "finance/eb14";
			}
		}else{
			model.addAttribute("errorMsg", "EB파일을 업로드 해 주세요."); 
		}
		return "finance/uploadEB14";
	}

	@RequestMapping(value="/finance/uploadEB14.do", method=RequestMethod.POST, params="cmd=updateEB14")
	public String updateEB14(HttpSession session,Model model) throws ParseException {
		List<EB14> eb14List = (List<EB14>) session.getAttribute("eb14ListSession");
		if(eb14List.isEmpty()){
			String fileName = (String) session.getAttribute("fileNameSession");
			String date = ReadEB14Date.readEB14Date(fileName);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date createDate = format.parse(date);
			eb13_commitmentDetailMapper.updateEB14success(createDate);
		}else{
			for(int i=0; i<eb14List.size(); i++){
				EB14 x = eb14List.get(i);
				String sponsorNo = x.getSponsorNo();
				Date createDate = x.getCreateDate();
				String errorCode = x.getErrorCode();
				StringBuffer sNo = new StringBuffer(sponsorNo);
				sNo.insert(4,"-");
				eb13_commitmentDetailMapper.updateEB14error(sNo.toString(),errorCode);//에러코드 추가
				eb13_commitmentDetailMapper.updateEB14success(createDate);
			}
		}
		model.addAttribute("successMsg", "EB14 파일 적용을 완료했습니다."); 
		return "finance/eb14";
	}

	@RequestMapping(value="/finance/resultEB1314.do", method=RequestMethod.GET)
	public String resultEB1314(Model model) {
		return "finance/resultEB1314";
	}

	@RequestMapping(value="/finance/resultEB1314.do", method=RequestMethod.POST)
	public String resultEB1314(Model model,@RequestParam String startDate,@RequestParam String endDate) {
		List<EB13_CommitmentDetail> eb1314result = eb13_commitmentDetailMapper.selectEB1314(startDate, endDate);
		model.addAttribute("eb1314List", eb1314result);
		return "finance/resultEB1314";
	}
}
