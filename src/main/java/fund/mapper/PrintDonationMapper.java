package fund.mapper;

import java.util.List;
import fund.dto.Pagination;
import fund.dto.PrintDonation;

public interface PrintDonationMapper {
	PrintDonation selectById(int ID);
    List<PrintDonation> selectPage(Pagination pagination);
    void insert(PrintDonation printDonation);
    void delete(int ID);
}
