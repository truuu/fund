package fund.service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import fund.dto.Code;
import fund.dto.DonationPurpose;
import fund.mapper.CodeMapper;


@Service
public class CodeService {

	@Autowired CodeMapper codeMapper;
    ObjectMapper mapper = new ObjectMapper();

	public String validate(Code code) {   // code check
		if (StringUtils.isBlank(code.getCodeName())) return "�ڵ���� �Է��ϼ���!";
		return null;
	}

	public boolean validate(String postNum){
		if (StringUtils.isBlank(postNum)) return false;
		return true;
	}

	public boolean validate(DonationPurpose donationPurpose){
		if (StringUtils.isBlank(donationPurpose.getName())) return false;
		if (StringUtils.isBlank(donationPurpose.getGubun())) return false;
		return true;
	}

}
