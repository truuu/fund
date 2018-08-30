package funds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Sponsor;
import fund.service.SponsorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml",
                                   "file:src/main/webapp/WEB-INF/dataSource.xml"})
public class TestService {

    @Autowired SponsorService sponsorService;


    @Test
    public void testSponsorService() throws Exception {
        Sponsor s1 = new Sponsor();
        s1.setName("홍길동");
        Sponsor s2 = new Sponsor();
        s2.setName("임꺽정");
        //sponsorService.changeLog(s1, s2);
    }
}