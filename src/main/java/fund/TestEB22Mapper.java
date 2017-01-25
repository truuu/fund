package fund;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Payment;
import fund.mapper.EB21_CommitmentDetailMapper;
import fund.mapper.PaymentMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml
public class TestEB22Mapper {
	@Autowired EB21_CommitmentDetailMapper eb21_commitmentDetailMapper;
	@Autowired PaymentMapper paymentMapper;

	@Test
	public void test() throws ParseException {
		//eb21_commitmentDetailMapper�� autowired �Ǿ����� Ȯ��.
		assertNotNull(eb21_commitmentDetailMapper);
		//paymentMapper�� autowired �Ǿ����� Ȯ��.
		assertNotNull(paymentMapper);

		//eb22������ ������ �� �ִ� �Ŀ����� �ִ��� Ȯ��
		eb21_commitmentDetailMapper.selectEB21success();

		//������ ����� update
		String pDate = "2016-12-20";
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = transFormat.parse(pDate);
		eb21_commitmentDetailMapper.updateEB21error("129",date,"0012");
		//������ ����� update
		eb21_commitmentDetailMapper.updateEB21success(date);
		
		//������ ����� ���� ��Ͽ� insert
		Payment payment = new Payment();
		payment.setSponsorId(129);
		payment.setCommitmentId(91);
		payment.setAmount(50000);
		payment.setPaymentDate(date);
		payment.setDonationPurposeId(90);
		payment.setPaymentMethodId(10);
		paymentMapper.insertEB21Payment(payment);
		
		//eb2122 ��� Ȯ��
		eb21_commitmentDetailMapper.selectEB2122("2016-11-01", "2016-12-30");
	}

}
