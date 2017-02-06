package fund.mapper;

import java.util.List;
import fund.dto.PrintDonation;
import fund.dto.PrintScholarship;
import fund.dto.pagination.Pagination;

public interface PrintScholarshipMapper {
	PrintScholarship selectByNum(int num);
	PrintScholarship selectMaxNum();
	PrintScholarship selectById(int ID);
	List<PrintScholarship> selectPage(Pagination pagination);
	void insert(PrintScholarship printScholarship);
	void delete(int ID);
	int selectCount(Pagination pagination);
	String selectSerialNum();
}
