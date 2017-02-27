package fund.mapper;
import java.util.List;
import fund.dto.DonationPurpose;

public interface DonationPurposeMapper {

    DonationPurpose selectById(int id);
    List<DonationPurpose> selectAll();
    List<DonationPurpose> selectNotClosed();
	void insert(DonationPurpose donationPurpose);
    void update(DonationPurpose donationPurpose);
    void delete(int id);

}
