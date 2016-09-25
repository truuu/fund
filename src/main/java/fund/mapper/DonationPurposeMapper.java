package fund.mapper;
import java.util.List;

import fund.dto.DonationPurpose;

public interface DonationPurposeMapper {
	List<DonationPurpose> selectDonationPurpose();
	String selectDonationPurpose2(int num);
	String selectCoporateName(int ID);
}
