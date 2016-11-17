package fund.test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Test;

import fund.dto.Sponsor;
import fund.mapper.SponsorMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"})
public class TestSponsorMapper {
	
	@Autowired SponsorMapper sponsorMapper;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
