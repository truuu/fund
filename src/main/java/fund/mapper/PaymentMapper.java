package fund.mapper;

import java.util.List;

import fund.dto.Commitment;
import fund.dto.EB21_commitmentDetail;
import fund.dto.Payment;

public interface PaymentMapper {
	List<Payment> selectEB21success();
	void insertEB21Payment(Payment payment);
	void insertIrregularPayment(Payment payment);
	List<Payment> selectPaymentRegular(int sponsorID);
	List<Payment> selectPaymentIrregular(int sponsorID);
	Commitment selectByCommitmentNo(String commitmentNo);
	void insertXferResult(Payment payment);
}
