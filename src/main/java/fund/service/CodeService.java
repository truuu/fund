package fund.service;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fund.dto.Church;
import fund.dto.Code;
import fund.dto.Corporate;
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

	public String getJSON(int codeGroupId) throws JsonProcessingException {
	    List<Church> list = codeMapper.selectByCodeGroupId(codeGroupId)
	                        .stream().map(code -> new Church(code)).collect(Collectors.toList());
        return mapper.writeValueAsString(list);
	}

}
