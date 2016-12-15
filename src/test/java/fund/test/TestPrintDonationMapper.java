package fund.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Pagination;
import fund.dto.PrintDonation;
import fund.mapper.PrintDonationMapper;

import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml�쓽 寃쎈줈紐�

public class TestPrintDonationMapper {
	
    @Autowired PrintDonationMapper printDonationMapper;
    
	@Test
	public void test() {
        
        assertNotNull(printDonationMapper);
        
        PrintDonation printDonation = printDonationMapper.selectById(1);
        assert(printDonation == null);
        assertEquals("조은호",printDonation.getSponsorName());
        
        Pagination pagination = new Pagination();
        List<PrintDonation> list = printDonationMapper.selectPage(pagination);
        int count = printDonationMapper.selectCount(pagination);
        
        PrintDonation newPrintDonation = new PrintDonation();
        
        newPrintDonation.setUserID(1);
        newPrintDonation.setSponsorName("이광수");
        newPrintDonation.setAmount(100000);
        printDonationMapper.insert(newPrintDonation);
       
        String serialNum = printDonationMapper.selectSerialNum();
        
        printDonation = printDonationMapper.selectMaxNum();
        
        int s = printDonation.getNum();
        
        assert(printDonation != null);
        
        printDonationMapper.delete(printDonation.getID());
        
        printDonation = printDonationMapper.selectByNum(s);
        assertNull(printDonation);
	}

}
