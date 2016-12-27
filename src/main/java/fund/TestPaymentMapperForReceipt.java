package fund;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Payment;
import fund.dto.Receipt;
import fund.mapper.ReceiptMapper;
import fund.mapper.PaymentMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"})

public class TestPaymentMapperForReceipt {

	@Autowired PaymentMapper paymentMapper;
	@Autowired ReceiptMapper receiptMapper;
	
	@SuppressWarnings("null")
	@Test
	public void test(){
		
		paymentMapper.issueReceiptByName(71, "4"); //4번 납입을 71번 영수증으로 발급
		paymentMapper.deleteReceiptByReceiptID(71);//4번 납입내역 영수증 발급 취소
		List<Payment> paymentList = paymentMapper.selectByRctID(71);
		Integer num = null;
		for(Payment p : paymentList){
			if(p.getId()==4)
				num=p.getId();
		}
		assertNull(num);
		
		//영수증번호로 확인
		paymentList = paymentMapper.selectByRctID(52);
		num = 91;
		for(Payment p : paymentList){
			String id = Integer.toString(p.getId());
			assert(num == paymentMapper.selectById(id));			
		} 
		
		//영수증 생성 후 발급 및 삭제 후 확인 
		Receipt receipt=new Receipt();
		receipt.setNo("2016-9999");
		receipt.setSponsorID(98);
		receipt.setCreateDate("2016-12-25");
		receiptMapper.insert(receipt);
		int receiptID = receiptMapper.selectRctID();
		paymentMapper.issueReceiptByDur(receiptID, "2016-09-24","2016-09-26" ,1, 98);
		paymentMapper.deleteReceiptByReceiptID(receiptID);
		receiptMapper.deleteById(receiptID);
		
		List<Integer> sponsorID = paymentMapper.selectDistinctSponsorID("2016-09-19", "2016-09-21", 1);
		for(Integer i : sponsorID){
			num=1;
			if(i == 90)//sponsorID가 여러개이면 num1 or num2 or...
				num=null;
			assertNull(num);
		}
		
	}
}
