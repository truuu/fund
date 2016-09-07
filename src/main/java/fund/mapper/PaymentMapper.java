package fund.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fund.dto.Payment;
import fund.dto.Pagination;

public interface PaymentMapper {
	
	List<Integer> selectDistinctSponsorID(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("corporateID") int corporateID);
	List<Payment> selectReceiptByName(@Param("startDate") String startDate,@Param("endDate") String endDate, @Param("name") String name, @Param("corporateID") String corporateID);
	void issueReceiptByDur(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("corporateID") int corporateID,@Param("sponsorID") int sponsorID);
	void deleteReceiptByReceiptID(int id);
	void delete(int id);
	
}
