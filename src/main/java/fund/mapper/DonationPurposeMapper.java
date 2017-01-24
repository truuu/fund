package fund.mapper;
import java.util.List;
import fund.dto.DonationPurpose;

public interface DonationPurposeMapper {

    DonationPurpose selectByID(int ID);
    DonationPurpose selectByName(String name);
	String selectCoporateName(int ID);
	List<DonationPurpose> selectAll();

	void insert(DonationPurpose donationPurpose);
    void update(DonationPurpose donationPurpose);
    void delete(int ID);

}
