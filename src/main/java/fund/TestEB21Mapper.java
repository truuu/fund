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
		//eb21Mapper�� autowired �Ǿ����� Ȯ��.
		assertNotNull(eb21Mapper);
		//eb21_commitmentDetailMapper�� autowired �Ǿ����� Ȯ��.
		assertNotNull(eb21_commitmentDetailMapper);
		//commitmentDetailMapper�� autowired �Ǿ����� Ȯ��.
		assertNotNull(commitmentDetailMapper);

		//�������� "20"�� �Ŀ��� �� eb21������ ���� �� �ִ� �Ŀ����� �ִ��� Ȯ��
		commitmentDetailMapper.selectEB21(20);

		//eb21������ ��������� �� Ȯ��
		eb21Mapper.createEB21file("2016-12-20");

		//commitmentDetailID���� 94�� �׸��� eb21���� ���� insert
		eb21_commitmentDetailMapper.createEB21List(20);

		//�׽�Ʈ������ ���� EB21���̺�� EB21_CommitmentDetail���̺��� �׸��� �������Ѵ�.
	}

}
