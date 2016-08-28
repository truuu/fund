package fund.mapper;

import java.util.List;

import fund.dto.DonationPurpose;
import fund.dto.Pagination;

public interface DonationPurposeMapper {
	DonationPurpose selectById(int id);
	List<DonationPurpose> selectPage(Pagination pagination);
	int selectCount();
	void insert(DonationPurpose donationPurpose);
    void update(DonationPurpose donationPurpose);
    void delete(int id);

}
