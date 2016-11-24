package fund.service;
import org.apache.commons.lang3.StringUtils; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import fund.dto.Code;
import fund.dto.Corporate;
import fund.dto.DonationPurpose;
import fund.mapper.CodeMapper;


@Service
public class CodeService {
	
	@Autowired CodeMapper codeMapper;
	
	public String validate(Code code) {   // code check
		if (StringUtils.isBlank(code.getCodeName())) return "코드명을 입력하세요!"; 

		return null; 
	}
	
	public boolean validate(String postNum){
		if (StringUtils.isBlank(postNum)) return false; 
		
		return true;
	}
	
	public boolean validate(Corporate corporate){
		if (StringUtils.isBlank(corporate.getName())) return false;
		if (StringUtils.isBlank(corporate.getCorporateNo())) return false;
		if (StringUtils.isBlank(corporate.getRepresentative())) return false;
		if (StringUtils.isBlank(corporate.getAddress())) return false;
		
		return true;
	}
	
	public boolean validate(DonationPurpose donationPurpose){
		if (StringUtils.isBlank(donationPurpose.getName())) return false;
		if (StringUtils.isBlank(donationPurpose.getGubun())) return false;
		
		return true;
	}
	
	
}
