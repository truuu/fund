package fund.mapper;
import java.util.List;
import fund.dto.DonationPurpose;
import fund.dto.pagination.Pagination;

public interface DonationPurposeMapper {

    DonationPurpose selectById(int id);
    List<DonationPurpose> selectAll();
    List<DonationPurpose> selectNotClosed();
    List<DonationPurpose> selectPage(Pagination pagination);
    int selectCount(Pagination pagination);
	void insert(DonationPurpose donationPurpose);
    void update(DonationPurpose donationPurpose);
    void delete(int id);

}
