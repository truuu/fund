package funds;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.mapper.MenuUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml",
                                   "file:src/main/webapp/WEB-INF/dataSource.xml"})
public class TestMapper {

    @Autowired MenuUserMapper menuUserMapper;

    @Test
    public void testMenuUserMapper() {
        assertNotNull(menuUserMapper);

        assertNotNull(menuUserMapper.selectMenuIdByUserId(6));
        assert(menuUserMapper.selectMenuIdByUserId(6).size() > 10);

        assert(menuUserMapper.selectMenuUserByUserId(6).size() > 10);
        menuUserMapper.delete(1, 6);
        menuUserMapper.insert(1, 6);
    }

}
