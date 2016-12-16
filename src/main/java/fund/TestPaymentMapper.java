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
		//paymentMapper가 autowired 되었는지 확인.
		assertNotNull(paymentMapper);

		//정기 납입 확인
		paymentMapper.selectPaymentRegular(111);

		//비정기 납입 등록 insert
		String pDate = "2016-12-08";
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = transFormat.parse(pDate);

		Payment payment = new Payment();
		payment.setSponsorID(129);
		payment.setAmount(50000);
		payment.setPaymentDate(date);
		payment.setDonationPurposeID(90);
		payment.setPaymentMethodID(14);//비정기 현물 코드
		payment.setEtc("jUnit 테스트");
		paymentMapper.insertIrregularPayment(payment);

		//자동이체 납입 등록 insert
		Payment payment2 = new Payment();
		payment2.setSponsorID(137);
		payment2.setCommitmentID(95);//paymentMethodID가 11인 약정이여야 함.
		payment2.setAmount(60000);
		payment2.setPaymentDate(date);
		payment2.setDonationPurposeID(90);
		payment2.setPaymentMethodID(11);//자동이체 코드
		paymentMapper.insertXferResult(payment2);

		//급여공제 납입 등록 insert
		Payment payment3 = new Payment();
		payment3.setSponsorID(114);
		payment3.setCommitmentID(50);//paymentMethodID가 12인 약정이여야 함.
		payment3.setAmount(70000);
		payment3.setPaymentDate(date);
		payment3.setDonationPurposeID(91);
		payment3.setPaymentMethodID(12);//급여공제 코드
		paymentMapper.insertXferResult(payment3);

		//비정기 납입된 사람 확인
		List<Payment> payment4 = paymentMapper.selectPaymentIrregular(129);
		assert(payment4 != null);

		//수정할 비정기 납입이 있는 지 확인
		paymentMapper.selectIrregular(92);

		//비정기 납입 삭제
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
