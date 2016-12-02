package fund;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Code;
import fund.mapper.CodeMapper;



import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml�쓽 寃쎈줈紐�

public class TestCodeMapper {
	
    @Autowired CodeMapper codeMapper;

    
	@Test
	public void test() {
        
		// codeMapper가 autowired되었는지 확인
        assertNotNull(codeMapper);

        Code code = codeMapper.selectByID(1);
        assert(code == null);
        assertEquals("개인",code.getCodeName());

       
        Code newCode = new Code();
        
        newCode.setCodeGroupID(1);
        newCode.setCodeName("개인2");
        newCode.setEtc1("기타test");
        codeMapper.insert(newCode);
        
        code = codeMapper.selectByCodeName("개인2");
       
        assert(code != null);
        assert(code.getCodeName() == "개인2"); 
      
        // 코드 delete
        codeMapper.delete(code.getID());
        
        // 삭제된 것 확인
        code = codeMapper.selectByCodeName("개인2"); 
        assertNull(code);
        
	}

}
