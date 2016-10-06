package fund.mapper;
import java.util.List;

import fund.dto.DonationPurpose;
import fund.dto.Pagination;

public interface DonationPurposeMapper {

	String selectDonationPurpose2(int num);
	String selectCoporateName(int ID);
	List<DonationPurpose> selectDonationPurpose(); //donationPurpose table
	DonationPurpose selectByID(int ID);
	List<DonationPurpose> selectPage();
	int selectCount();
	void insert(DonationPurpose donationPurpose);
    void update(DonationPurpose donationPurpose);
    void delete(int ID);

}
