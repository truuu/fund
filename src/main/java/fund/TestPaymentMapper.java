package fund;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Payment;
import fund.dto.PaymentRecordStats;
import fund.dto.PaymentSummary1;
import fund.mapper.PaymentMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml
public class TestPaymentMapper {
	@Autowired PaymentMapper paymentMapper;

	@Test
	public void test() throws ParseException {
		//paymentMapper�� autowired �Ǿ����� Ȯ��.
		assertNotNull(paymentMapper);

		//���� ���� Ȯ��
		paymentMapper.selectPaymentRegular(111);

		//������ ���� ��� insert
		String pDate = "2016-12-08";
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = transFormat.parse(pDate);

		Payment payment = new Payment();
		payment.setSponsorId(129);
		payment.setAmount(50000);
		payment.setPaymentDate(date);
		payment.setDonationPurposeId(90);
		payment.setPaymentMethodId(14);//������ ���� �ڵ�
		payment.setEtc("jUnit �׽�Ʈ");
		paymentMapper.insertIrregularPayment(payment);

		//�ڵ���ü ���� ��� insert
		Payment payment2 = new Payment();
		payment2.setSponsorId(137);
		payment2.setCommitmentId(95);//paymentMethodID�� 11�� �����̿��� ��.
		payment2.setAmount(60000);
		payment2.setPaymentDate(date);
		payment2.setDonationPurposeId(90);
		payment2.setPaymentMethodId(11);//�ڵ���ü �ڵ�
		paymentMapper.insertXferResult(payment2);

		//�޿����� ���� ��� insert
		Payment payment3 = new Payment();
		payment3.setSponsorId(114);
		payment3.setCommitmentId(50);//paymentMethodID�� 12�� �����̿��� ��.
		payment3.setAmount(70000);
		payment3.setPaymentDate(date);
		payment3.setDonationPurposeId(91);
		payment3.setPaymentMethodId(12);//�޿����� �ڵ�
		paymentMapper.insertXferResult(payment3);

		//������ ���Ե� ��� Ȯ��
		List<Payment> payment4 = paymentMapper.selectPaymentIrregular(129);
		assert(payment4 != null);

		//������ ������ ������ �ִ� �� Ȯ��
		paymentMapper.selectIrregular(92);

		//������ ���� ����
		paymentMapper.delete(92);


		List<Payment> list1 = paymentMapper.selectComparePaymentDate("2016-06-01","2016-12-01");

		PaymentRecordStats paymentRecordStats = new PaymentRecordStats();
		paymentRecordStats.setStartDate("2016-06-01");
		paymentRecordStats.setEndDate("2016-12-01");
		paymentRecordStats.setSrchType1(1); 
		paymentRecordStats.setSrchType2(90);

		List<Payment> list2 = paymentMapper.selectPaymentRecord(paymentRecordStats);

		List<Payment> list3 = paymentMapper.selectPaymentTotal(paymentRecordStats);

		List<PaymentSummary1> list4 = paymentMapper.selectMonthDonationPurposePayment("2016-06-01","2016-12-01");

	}

}
