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
		//eb13_commitmentDetailMapper�� autowired �Ǿ����� Ȯ��.
		assertNotNull(eb13_commitmentDetailMapper);
		
		//eb14������ ������ �� �ִ� �Ŀ����� �ִ��� Ȯ��
		eb13_commitmentDetailMapper.selectEB14("2016-12-08");
		
		//������ ����� update
		eb13_commitmentDetailMapper.updateEB14error("129","0012");
		//������ ����� update
		String december = "2016-12-08";
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = transFormat.parse(december);
		eb13_commitmentDetailMapper.updateEB14success(date);
		
		//eb1314 ��� Ȯ��
		eb13_commitmentDetailMapper.selectEB1314("2016-11-01", "2016-12-30");
	}

}
