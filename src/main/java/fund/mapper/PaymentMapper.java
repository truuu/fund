package fund.mapper;

import java.util.List;

import fund.dto.Payment;

public interface PaymentMapper {
	void insertIrregularPayment(Payment payment);
	List<Payment> selectPaymentRegular(int sponsorID);
	List<Payment> selectPaymentIrregular(int sponsorID);
}
