package fund.mapper;

import java.util.List;

import fund.dto.Receipt;
import fund.dto.Pagination;

public interface ReceiptMapper {
	int selectRctID();
	Receipt selectById(int id);
	List<Receipt> selectReceiptList();
	List<Receipt> selectPage(Pagination pagination);
	List<Receipt> selectReceiptView(int id);
	int getRid();
	String getLastNo(String year);
	int selectCount(Pagination pagination);
	void insert(Receipt receipt);
	void deleteByNo(int no);
	void deleteById(int id);
}
