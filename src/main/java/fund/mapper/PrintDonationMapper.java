package fund.mapper;

import java.util.List;
import fund.dto.PrintDonation;
import fund.dto.pagination.Pagination;

public interface PrintDonationMapper {
	PrintDonation selectByNum(int num);
	PrintDonation selectMaxNum();
	PrintDonation selectById(int ID);
    List<PrintDonation> selectPage(Pagination pagination);
    int selectCount(Pagination pagination);
    void insert(PrintDonation printDonation);
    void delete(int ID);
    String selectSerialNum();
}
