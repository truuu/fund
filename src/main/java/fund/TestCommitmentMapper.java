package fund;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import fund.dto.Commitment;
import fund.dto.CommitmentDetail;
import fund.mapper.CommitmentDetailMapper;
import fund.mapper.CommitmentMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml�쓽 寃쎈줈紐�

public class TestCommitmentMapper {

    @Autowired CommitmentMapper commitmentMapper;
    @Autowired CommitmentDetailMapper commitmentDetailMapper;

	@Test
	public void test() {

        assertNotNull(commitmentMapper);

        Commitment commitment = commitmentMapper.selectById(85);  // 후원자 박대표

        assert(commitment == null);
        assertEquals("2016-0120-01", commitment.getCommitmentNo());

        commitment = commitmentMapper.selectByCommitmentNo("2016-0120-03");
        List<Commitment> commitment2 = commitmentMapper.selectBySponsorId(122);

        assertNull(commitment); // 2016-0120-03 없는거 확인

        // 2016-0120-03 약정 insert
        Commitment newCommitment = new Commitment();
        //newCommitment.setCommitmentNo("2016-0120-03");
        newCommitment.setSponsorId(122);
        newCommitment.setDonationPurposeId(90);
        newCommitment.setPaymentMethodId(10);
        newCommitment.setCommitmentDate("2016-11-03");
        newCommitment.setStartDate("2016-11-03");
        newCommitment.setEndDate("2017-11-03");
        newCommitment.setEtc("junit test");
        commitmentMapper.insert(newCommitment);

        CommitmentDetail commitmentDetail = new CommitmentDetail();  // 약정상세

		commitmentDetail.setCommitmentID(commitmentDetailMapper.selectCommitmentID());
		commitmentDetail.setAmountPerMonth(10000);
		commitmentDetail.setBankID(19);
		commitmentDetail.setPaymentDay(25);
		commitmentDetail.setAccountHolder("박대표");
		commitmentDetail.setAccountNo("1158890776");
		commitmentDetail.setStartDate(newCommitment.getStartDate());
		commitmentDetail.setEtc("상세 비고");

		commitmentDetailMapper.insert(commitmentDetail);  // 약정 상세 insert

        commitment = commitmentMapper.selectByCommitmentNo("2016-0120-03");
        commitmentDetail = commitmentDetailMapper.selectMaxCommitmentDetailID();

        assert(commitment != null);
        assert(commitmentDetail != null);

        commitment.setDonationPurposeId(97); // 기부목적 경영학부 장학금으로 변경
        commitmentMapper.update(commitment);

        commitmentDetail.setStartDate("2016-12-03");
        commitmentDetailMapper.update(commitmentDetail);

        commitmentMapper.updateEndDate(commitment.getId()); //약정 종료

        List<CommitmentDetail> datailList = commitmentDetailMapper.selectByCommitmentID2(commitment.getId());

        int number = commitmentDetail.getID();  // 약정상세id 저장
        int number2 = commitmentDetailMapper.selectCommitmentID();  // MAX id


        // 약정 상세 먼저 delete
        commitmentDetailMapper.delete(commitmentDetail.getID());

        // 약정 delete
        commitmentMapper.delete(commitment.getId());

        // 약정 상세 삭제된 것 확인
        commitmentDetail = commitmentDetailMapper.selectByCommitmentDetailID(number);
        assertNull(commitmentDetail);

        // 약정 삭제된 것 확인
        commitment = commitmentMapper.selectByCommitmentNo("2016-0120-03");
        assertNull(commitment);


	}

}