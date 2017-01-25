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
import fund.dto.Code;
import fund.dto.CodeGroup;
import fund.mapper.CodeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml�쓽 寃쎈줈紐�

public class TestCodeMapper {

    @Autowired CodeMapper codeMapper;


	@Test
	public void test() {

		// codeMapper가 autowired되었는지 확인
        assertNotNull(codeMapper);

        Code code = codeMapper.selectById(1);
        assert(code == null);
        assertEquals("개인",code.getCodeName());


        Code newCode = new Code();

        newCode.setCodeGroupId(1);
        newCode.setCodeName("개인2");
        newCode.setEtc1("기타test");
        codeMapper.insert(newCode);

        code = codeMapper.selectByCodeName("개인2");
        List<Code> codeList1 = codeMapper.selectByCodeGroupName("후원인구분1");
        List<Code> codeList2 =codeMapper.selectAllPaymentMethod("정기 납입방법","비정기 납입방법");
        String name = codeMapper.selectCodeGroupById(code.getId()).getName();
        List<CodeGroup> codeGroupList = codeMapper.selectCodeGroup();
        String codeGroupName = codeMapper.selectCodeGroupById(1).getName();
        List<Code> codeList3 = codeMapper.selectByCodeGroupId(1);

        code.setName("개인3");
        codeMapper.update(code);

        assert(code != null);
        assert(code.getCodeName() == "개인3");

        // 코드 delete
        codeMapper.delete(code.getId());

        // 삭제된 것 확인
        code = codeMapper.selectByCodeName("개인3");
        assertNull(code);

	}

}
