package fund.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import fund.dto.DonationPurpose;
import fund.mapper.DonationPurposeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml�쓽 寃쎈줈紐�

public class TestDonationPurposeMapper {

	@Autowired DonationPurposeMapper donationPurposeMapper;

	@Test
	public void test() {

        assertNotNull(donationPurposeMapper);

        List<DonationPurpose> list = donationPurposeMapper.selectAll();
        String name = donationPurposeMapper.selectByID(90).getName();
        String corporateName = donationPurposeMapper.selectCoporateName(90);

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

        //update
        donationPurpose.setName("test 기부목적이름2");
        donationPurpose.setGubun("지정");
        donationPurposeMapper.update(donationPurpose);

        // 기관 delete
        donationPurposeMapper.delete(donationPurpose.getID());

        // 삭제된 것 확인
        donationPurpose = donationPurposeMapper.selectByName("test 기부목적이름");
        assertNull(donationPurpose);

	}

}
