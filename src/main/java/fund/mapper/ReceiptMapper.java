package fund.mapper;

import java.util.List;

import fund.dto.Receipt;
import fund.dto.Pagination;

public interface ReceiptMapper {
	int selectRctID();//영
	Receipt selectById(int id);
	Receipt selectByNo(String no);
	//List<Receipt> selectReceiptList();
	List<Receipt> selectPage(Pagination pagination);
	//Receipt selectReceiptView(int rid);
	int getRid();
	String getLastNo(String year);
	int selectCount(Pagination pagination);
	void insert(Receipt receipt);
	void deleteByNo(String no);
	void deleteById(int id);

}
