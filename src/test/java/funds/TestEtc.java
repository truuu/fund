package funds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml",
                                   "file:src/main/webapp/WEB-INF/dataSource.xml"})
public class TestEtc {


    @Test
    public void test1() throws Exception {
        String s = "<tbody><tr><td></td></tr><tr><td></td></tr></tbody>";
        System.out.println(StringUtils.countOccurrencesOf(s, "tr"));
        System.out.println(StringUtils.countOccurrencesOf(s, "<tr>"));
    }
}