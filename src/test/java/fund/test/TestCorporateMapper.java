package fund.test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Corporate;
import fund.mapper.CorporateMapper;

import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml�쓽 寃쎈줈紐�

public class TestCorporateMapper {
	
    @Autowired CorporateMapper corporateMapper;
    
	@Test
	public void test() {
        
        assertNotNull(corporateMapper);

        Corporate corporate = corporateMapper.selectByID(1);
        assert(corporate == null);
        assertEquals("성공회대학교",corporate.getName());

        Corporate newCorporate = new Corporate();
        newCorporate.setName("성공회대학교2");
        newCorporate.setCorporateNo("aaa134");
        newCorporate.setRepresentative("안지은");
        newCorporate.setAddress("서울시 구로구 항동 성공회대");
        
        corporateMapper.insert(newCorporate);
        
        corporate = corporateMapper.selectByName("성공회대학교2");
       
        assert(corporate != null);
        assert(corporate.getName() == "성공회대학교2"); 
      
        corporate.setName("성공회대학교3");
        corporate.setCorporateNo("bbb123");
        corporate.setAddress("인천시 계양구 작전동 123번지");
        corporateMapper.update(corporate);	
        
        // 기관 delete
        corporateMapper.delete(corporate.getID());
        
        // 삭제된 것 확인
        corporate = corporateMapper.selectByName("성공회대학교2"); 
        assertNull(corporate);
        
	}

}
