package fund.mapper;

import java.util.List;

import fund.dto.Commitment;
import fund.dto.EB21_commitmentDetail;
import fund.dto.Payment;
import fund.dto.Salary;

public interface PaymentMapper {
	void insertEB21Payment(Payment payment);
	void insertIrregularPayment(Payment payment);
	List<Payment> selectPaymentRegular(int sponsorID);
	List<Payment> selectPaymentIrregular(int sponsorID);
	void insertXferResult(Payment payment);
	void insertSalaryResult(Payment payment);
}
