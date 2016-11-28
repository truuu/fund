package fund.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fund.dto.Payment;
import fund.dto.Pagination;
import fund.dto.Commitment;
import fund.dto.EB21_commitmentDetail;
import fund.dto.Salary;
import fund.dto.PaymentRecordStats;
import fund.dto.PaymentSummary1;


public interface PaymentMapper {

	int selectById(String pid);
	List<Integer> selectDistinctSponsorID(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("corporateID") int corporateID);
	List<Payment> selectReceiptByName(Pagination pagination);
	List<Payment> selectByRctID(int rid);
	List<Payment> selectPage(Pagination pagination);
	int selectCount(Pagination pagination);
	List<Payment> selectTaxData(Pagination pagination);
	void issueReceiptByDur(@Param("receiptID") int receiptID,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("corporateID") int corporateID,@Param("sponsorID") int sponsorID);
	void issueReceiptByName(@Param("receiptID") int receiptID, @Param("id") String id);
	void deleteReceiptByReceiptID(int id);
	void delete(int id);

	void insertEB21Payment(Payment payment);
	void insertIrregularPayment(Payment payment);
	List<Payment> selectPaymentRegular(int sponsorID);
	List<Payment> selectPaymentIrregular(int sponsorID);
	void insertXferResult(Payment payment);
	void insertSalaryResult(Payment payment);

	List<Payment> selectComparePaymentDate(@Param("startDate") String startDate,@Param("endDate") String endDate);
	List<Payment> selectPaymentRecord(PaymentRecordStats paymentRecordStats);
	List<Payment> selectPaymentTotal(PaymentRecordStats paymentRecordStats);
	
	List<PaymentSummary1> selectMonthDonationPurposePayment(@Param("startDate") String startDate,@Param("endDate") String endDate);
	Payment selectIrregular(int id);
	void updateIrregular(Payment payment);
	void deleteIrregular(int id);
}
