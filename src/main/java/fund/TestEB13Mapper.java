package fund;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.mapper.CommitmentDetailMapper;
import fund.mapper.EB13Mapper;
import fund.mapper.EB13_CommitmentDetailMapper;

import java.text.ParseException;

import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml
public class TestEB13Mapper {
	@Autowired EB13Mapper eb13Mapper;
	@Autowired EB13_CommitmentDetailMapper eb13_commitmentDetailMapper;
	@Autowired CommitmentDetailMapper commitmentDetailMapper;

	@Test
	public void test() throws ParseException {
		//eb13Mapper가 autowired 되었는지 확인.
		assertNotNull(eb13Mapper);
		//eb13_commitmentDetailMapper가 autowired 되었는지 확인.
		assertNotNull(eb13_commitmentDetailMapper);
		//commitmentDetailMapper가 autowired 되었는지 확인.
		assertNotNull(commitmentDetailMapper);
		
		//eb13파일을 만들 수 있는 후원인이 있는지 확인
		commitmentDetailMapper.selectEB13();
		
		//eb13파일이 만들어지는 지 확인
		eb13Mapper.createEB13file();
		
		//commitmentDetailID값이 94인 항목의 eb13파일 내용 insert
		eb13_commitmentDetailMapper.createEB13list(20);
		
		//테스트용으로 만든 EB13테이블과 EB13_CommitmentDetail테이블의 항목은 지워야한다.
	}

}
