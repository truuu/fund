package fund.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fund.dto.Commitment;
import fund.dto.CommitmentDetail;
import fund.mapper.CommitmentDetailMapper;
import fund.mapper.CommitmentMapper;


import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml","file:src/main/webapp/WEB-INF/dataSource.xml"}) // dispatcher-servlet-test.xml�쓽 寃쎈줈紐�

public class TestCommitmentMapper {
	
    @Autowired CommitmentMapper commitmentMapper;
    @Autowired CommitmentDetailMapper commitmentDetailMapper;
    
	@Test
	public void test() {
        
        assertNotNull(commitmentMapper);

        Commitment commitment = commitmentMapper.selectByID(85);  // 후원자 박대표
        
        assert(commitment == null);
        assertEquals("2016-0120-01", commitment.getCommitmentNo());
        
        commitment = commitmentMapper.selectByCommitmentNo("2016-0120-03");
        List<Commitment> commitment2 = commitmentMapper.selectBySponsorID(122);
        int num = commitmentMapper.selectCountCommitment(122);  // 약정갯수 세기
        
        assertNull(commitment); // 2016-0120-03 없는거 확인

        // 2016-0120-03 약정 insert
        Commitment newCommitment = new Commitment();
        //newCommitment.setCommitmentNo("2016-0120-03");
        newCommitment.setSponsorID(122);
        newCommitment.setDonationPurposeID(90);
        newCommitment.setPaymentMethodID(10);
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
        
        commitment.setDonationPurposeID(97); // 기부목적 경영학부 장학금으로 변경
        commitmentMapper.update(commitment);
        
        commitmentDetail.setStartDate("2016-12-03");
        commitmentDetailMapper.update(commitmentDetail);
        
        commitmentMapper.updateEndDate(commitment.getID()); //약정 종료
        
        List<CommitmentDetail> datailList = commitmentDetailMapper.selectByCommitmentID2(commitment.getID());
      
        int number = commitmentDetail.getID();  // 약정상세id 저장
        int number2 = commitmentDetailMapper.selectCommitmentID();  // MAX id
        
        
        // 약정 상세 먼저 delete
        commitmentDetailMapper.delete(commitmentDetail.getID());
        
        // 약정 delete
        commitmentMapper.delete(commitment.getID());
        
        // 약정 상세 삭제된 것 확인
        commitmentDetail = commitmentDetailMapper.selectByCommitmentDetailID(number);
        assertNull(commitmentDetail);
        
        // 약정 삭제된 것 확인
        commitment = commitmentMapper.selectByCommitmentNo("2016-0120-03");
        assertNull(commitment);
        
        
	}

}
