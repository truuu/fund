package fund.mapper;

import java.util.List;

import fund.dto.Pagination;
import fund.dto.PrintScholarship;

public interface  PrintScholarshipMapper {
	List<PrintScholarship> selectPage(Pagination pagination);
	void insert(PrintScholarship printScholarship);
	void delete(int ID);
	int selectCount(Pagination pagination);
	String selectSerialNum();
}