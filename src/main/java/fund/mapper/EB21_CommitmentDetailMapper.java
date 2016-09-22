package fund.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import fund.dto.EB21_commitmentDetail;
import fund.dto.EB22;

public interface EB21_CommitmentDetailMapper {
	List<EB21_commitmentDetail> selectEB21(int pDay);//eb21파일로 만들 목록 출력
	List<EB21_commitmentDetail> selectEB22(int paymentDay);//eb22파일의 목록과 비교하기 위한 eb13목록
	void updateEB21error(@Param("sponsorNo") String sponsorNo, @Param("paymentDate") Date paymentDate);//상태를 에러로 변경 
	void updateEB21success(@Param("paymentDate") Date paymentDate);//상태를 성공으로 변경
	List<EB21_commitmentDetail> selectEB2122();//eb21,22의 결과 조회
	EB22 selectSponsorName(String sponsorNo);
}
