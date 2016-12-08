package fund;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.mapper.CommitmentDetailMapper;
import fund.mapper.EB21Mapper;
import fund.mapper.EB21_CommitmentDetailMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml
public class TestEB21Mapper {
	@Autowired EB21Mapper eb21Mapper;
	@Autowired EB21_CommitmentDetailMapper eb21_commitmentDetailMapper;
	@Autowired CommitmentDetailMapper commitmentDetailMapper;

	@Test
	public void test() {
		//eb21Mapper가 autowired 되었는지 확인.
		assertNotNull(eb21Mapper);
		//eb21_commitmentDetailMapper가 autowired 되었는지 확인.
		assertNotNull(eb21_commitmentDetailMapper);
		//commitmentDetailMapper가 autowired 되었는지 확인.
		assertNotNull(commitmentDetailMapper);

		//납입일이 "20"인 후원인 중 eb21파일을 만들 수 있는 후원인이 있는지 확인
		commitmentDetailMapper.selectEB21(20);

		//eb21파일이 만들어지는 지 확인
		eb21Mapper.createEB21file("2016-12-20");

		//commitmentDetailID값이 94인 항목의 eb21파일 내용 insert
		eb21_commitmentDetailMapper.createEB21List(20);

		//테스트용으로 만든 EB21테이블과 EB21_CommitmentDetail테이블의 항목은 지워야한다.
	}

}
