package fund.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Payment;
import fund.dto.PaymentRecordStats;
import fund.dto.PaymentSummary1;
import fund.mapper.PaymentMapper;

import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) 

public class TestPaymentMapper {
	
    @Autowired PaymentMapper paymentMapper;

    
	@Test
	public void test() {
        
        assertNotNull(paymentMapper);
        
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
