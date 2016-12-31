package fund;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Receipt;
import fund.mapper.ReceiptMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"})

public class TestReceiptMapper {
	
	@Autowired ReceiptMapper receiptMapper;

	@SuppressWarnings("null")
	@Test
	public void test() {
		// receiptMapper가 autowired되었는지 확인
        assertNotNull(receiptMapper);

        //id=1 사용자의 이름이 " "맞는지 확인
        Receipt receipt = receiptMapper.selectById(1);
        assert(receipt == null);
        assertEquals(111, receipt.getSponsorID());
        
        //영수증no 값이 2016-0024 인 사용자가 없는 것으로 확인
        receipt = receiptMapper.selectByNo("2016-0024");
        assertNull(receipt);
        
        //영수증 생성 
        Receipt newReceipt = new Receipt();
        newReceipt.setSponsorID(89);
        newReceipt.setCreateDate("2016/10/01");
        newReceipt.setNo("2016-0031");
        receiptMapper.insert(newReceipt);
        newReceipt.setID(receiptMapper.getRid());
        receiptMapper.selectById(receiptMapper.getRid()).equals(newReceipt);
        
        //no 값이 2016-0021 인 사용자 있는 것으로 확인
        receipt = receiptMapper.selectByNo("2016-0021");
        assert(receipt != null);
        assert(receipt.getSponsorID() ==89);
        assertEquals("2016-10-04",receipt.getCreateDate());
        
        //no 값이 2016-0021인 영수증 delete
        //reference
        //receiptMapper.deleteByNo("2016-0021");
        
        //no 값이 2016-0021인 영수증 삭제된 것으로 확인
        //receipt=receiptMapper.selectByNo("2016-0021");
        //assertNull(receipt);
        
        //id 값으로 영수증 삭제 확인
        int id=receiptMapper.selectRctID();
        receiptMapper.deleteById(id);
        receipt = receiptMapper.selectById(id);
        assertNull(receipt);
        
        //--년도 마지막 영수증 번호 확인
        String receiptNo = receiptMapper.getLastNo("2016");
        receiptNo.equals("2016-0055");
        
        //마지막 영수증 id 확인
        int num = receiptMapper.selectRctID();
        Receipt receipt2 = receiptMapper.selectById(num);
        assert(receipt2.getNo() == "2016-0031");
        assert(receipt2.getSponsorID() == 89);
        assert(receipt2.getCreateDate() == "2016-10-01");
        
        
		
	}

}
