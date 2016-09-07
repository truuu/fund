package fund.mapper;

import java.util.List;

import fund.dto.Receipt;
import fund.dto.Pagination;

public interface ReceiptMapper {
	Receipt selectById(int id);
	List<Receipt> selectReceiptList();
	List<Receipt> selectPage(Pagination pagination);
	int selectCount(Pagination pagination);
	void insertByDur(Receipt receipt);
	void insertByName(Receipt receipt);
	void deleteByNo(int no);
	void deleteById(int id);
}
