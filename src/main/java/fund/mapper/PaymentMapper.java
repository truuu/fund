package fund.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fund.dto.Payment;
import fund.dto.Pagination;

public interface PaymentMapper {
	
	int selectById(String pid);
	List<Integer> selectDistinctSponsorID(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("corporateID") int corporateID);
	List<Payment> selectReceiptByName(Pagination pagination);
	List<Payment> selectByRctID(int rctId);
	List<Payment> selectPage(Pagination pagination);
	int selectCount(Pagination pagination);
	void issueReceiptByDur(@Param("receiptID") int receiptID,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("corporateID") int corporateID,@Param("sponsorID") int sponsorID);
	void issueReceiptByName(@Param("receiptID") int receiptID, @Param("id") String id);
	void deleteReceiptByReceiptID(int id);
	void delete(int id);
	
}
