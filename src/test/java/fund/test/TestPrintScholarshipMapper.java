package fund.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Pagination;
import fund.dto.PrintScholarship;
import fund.mapper.PrintScholarshipMapper;

import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml�쓽 寃쎈줈紐�

public class TestPrintScholarshipMapper {
	
    @Autowired PrintScholarshipMapper printScholarshipMapper;
    
	@Test
	public void test() {
        
        assertNotNull(printScholarshipMapper);
        
        PrintScholarship printScholarship = printScholarshipMapper.selectById(7);
        assert(printScholarship == null);
        assertEquals("안지은",printScholarship.getStudentName());
        
        Pagination pagination = new Pagination();
        List<PrintScholarship> list = printScholarshipMapper.selectPage(pagination);
        int count = printScholarshipMapper.selectCount(pagination);
        
        PrintScholarship newPrintScholarship = new PrintScholarship();
        
        newPrintScholarship.setUserID(1);
        newPrintScholarship.setStudentName("이광수");
        newPrintScholarship.setDepartment("소프트웨어공학과");
        newPrintScholarship.setStudentNo("201332050");
        printScholarshipMapper.insert(newPrintScholarship);
       
        String serialNum = printScholarshipMapper.selectSerialNum();
        
        printScholarship = printScholarshipMapper.selectMaxNum();
        
        int s = printScholarship.getNum();
        
        assert(printScholarship != null);
        
        printScholarshipMapper.delete(printScholarship.getID());
        
        printScholarship = printScholarshipMapper.selectByNum(s);
        assertNull(printScholarship);
	}

}
