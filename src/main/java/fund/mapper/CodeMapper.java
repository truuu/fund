package fund.mapper;
import java.util.List;

import fund.dto.Code;
import fund.dto.DonationPurpose;

public interface CodeMapper {
	List<Code> selectByBank(String bank);
	List<Code> selectByPaymentMethod(String name);
	List<DonationPurpose> selectDonationPurpose();
}
