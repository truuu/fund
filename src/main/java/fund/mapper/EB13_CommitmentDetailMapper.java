package fund.mapper;

import java.util.Date;
import java.util.List;

import fund.dto.EB13_CommitmentDetail;

public interface EB13_CommitmentDetailMapper {
	List<EB13_CommitmentDetail> selectEB13();//eb13파일로 만들 목록 출력
	List<EB13_CommitmentDetail> selectEB14();//eb14파일의 목록과 비교하기 위한 eb13목록
	void updateEB14error(int sponsorNo);//상태를 에러로 변경 
	void updateEB14success(int sponsorNo,Date createDate);//상태를 성공으로 변경
	List<EB13_CommitmentDetail> selectEB1314();//eb13,14의 결과 조회
}
