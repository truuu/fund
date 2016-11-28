package fund;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import fund.dto.DonationPurpose;
import fund.mapper.DonationPurposeMapper;

import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml�쓽 寃쎈줈紐�

public class TestDonationPurposeMapper {
	
	@Autowired DonationPurposeMapper donationPurposeMapper;
    
	@Test
	public void test() {
        
        assertNotNull(donationPurposeMapper);

        DonationPurpose donationPurpose = donationPurposeMapper.selectByID(90);
        assert(donationPurpose == null);
        assertEquals("학교위임 일만동행(학교위임)",donationPurpose.getName());

        DonationPurpose newDonationPurpose = new DonationPurpose();
        newDonationPurpose.setCorporateID(1);
        newDonationPurpose.setOrganizationID(31);
        newDonationPurpose.setName("test 기부목적이름");
        newDonationPurpose.setGubun("일반");
        
        donationPurposeMapper.insert(newDonationPurpose);
        
        donationPurpose = donationPurposeMapper.selectByName("test 기부목적이름");
       
        assert(donationPurpose != null);
        assert(donationPurpose.getName() == "test 기부목적이름"); 
      
        // 기관 delete
        donationPurposeMapper.delete(donationPurpose.getID());
        
        // 삭제된 것 확인
        donationPurpose = donationPurposeMapper.selectByName("test 기부목적이름"); 
        assertNull(donationPurpose);
        
	}

}
