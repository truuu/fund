package fund.mapper;

import java.util.List;

import fund.dto.Pagination;
import fund.dto.PrintDonation;
import fund.dto.PrintScholarship;

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
