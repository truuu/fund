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
		//eb13Mapper�� autowired �Ǿ����� Ȯ��.
		assertNotNull(eb13Mapper);
		//eb13_commitmentDetailMapper�� autowired �Ǿ����� Ȯ��.
		assertNotNull(eb13_commitmentDetailMapper);
		//commitmentDetailMapper�� autowired �Ǿ����� Ȯ��.
		assertNotNull(commitmentDetailMapper);
		
		//eb13������ ���� �� �ִ� �Ŀ����� �ִ��� Ȯ��
		commitmentDetailMapper.selectEB13();
		
		//eb13������ ��������� �� Ȯ��
		eb13Mapper.createEB13file();
		
		//commitmentDetailID���� 94�� �׸��� eb13���� ���� insert
		eb13_commitmentDetailMapper.createEB13list(20);
		
		//�׽�Ʈ������ ���� EB13���̺�� EB13_CommitmentDetail���̺��� �׸��� �������Ѵ�.
	}

}
