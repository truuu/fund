package fund;

import static org.junit.Assert.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.mapper.EB13_CommitmentDetailMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml
public class TestEB14Mapper {
	@Autowired EB13_CommitmentDetailMapper eb13_commitmentDetailMapper;

	@Test
	public void test() throws ParseException {
		//eb13_commitmentDetailMapper가 autowired 되었는지 확인.
		assertNotNull(eb13_commitmentDetailMapper);
		
		//eb14파일을 적용할 수 있는 후원인이 있는지 확인
		eb13_commitmentDetailMapper.selectEB14("2016-12-08");
		
		//에러인 사용자 update
		eb13_commitmentDetailMapper.updateEB14error("129","0012");
		//성공인 사용자 update
		String december = "2016-12-08";
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = transFormat.parse(december);
		eb13_commitmentDetailMapper.updateEB14success(date);
		
		//eb1314 결과 확인
		eb13_commitmentDetailMapper.selectEB1314("2016-11-01", "2016-12-30");
	}

}
