package fund.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import fund.dto.Payment;
import fund.dto.PaymentListParam;
import fund.dto.PaymentRecordStats;
import fund.dto.PaymentSummary1;
import fund.dto.pagination.Pagination;


public interface PaymentMapper {

    Payment selectById(int id);
    List<Payment> selectPaymentList1(PaymentListParam param);
    List<Payment> selectPaymentList2(int sponsorId);
    List<Payment> selectPaymentList1a(int commitmentId);

    void update(Payment payment);
    void delete(int id);
    void insert(Payment payment);

    List<Payment> selectForReceiptCreation1(Map<String,Object> map);
    List<Payment> selectForReceiptCreation2(Map<String,Object> map);
    List<Payment> selectByReceiptId(int rid);
    Map<String,Object> selectForTaxData(Map<String,Object> map);




	List<Integer> selectDistinctSponsorID(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("corporateId") int corporateId);
	List<Payment> selectPage(Pagination pagination);
	int selectCount(Pagination pagination);
	List<Payment> selectTaxData(Pagination pagination);
	void deleteReceiptByReceiptId(int id);

	void insertEB21Payment(Payment payment);
	void insertXferResult(Payment payment);
	void insertSalaryResult(Payment payment);

	List<Payment> selectComparePaymentDate(@Param("startDate") String startDate,@Param("endDate") String endDate);
	List<Payment> selectPaymentRecord(PaymentRecordStats paymentRecordStats);
	List<Payment> selectPaymentTotal(PaymentRecordStats paymentRecordStats);

	List<PaymentSummary1> selectMonthDonationPurposePayment(@Param("startDate") String startDate,@Param("endDate") String endDate);
	Payment selectIrregular(int id);
}
