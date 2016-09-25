package fund.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fund.dto.Payment;
import fund.dto.PaymentRecordStats;

public interface PaymentMapper {
	List<Payment> selectComparePaymentDate(@Param("startDate") String startDate,@Param("endDate") String endDate);
	List<Payment> selectPaymentRecord(PaymentRecordStats paymentRecordStats);
	List<Payment> selectPaymentTotal(PaymentRecordStats paymentRecordStats);
}
