package fund.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import fund.dto.EB13_Commitment;

public interface EB13_CommitmentDetailMapper {
	List<EB13_Commitment> selectEB14(String createDate);
	void updateEB14error(@Param("sponsorNo") String sponsorNo, @Param("errorCode") String errorCode);
	void updateEB14success(Date createDate);
	List<EB13_Commitment> selectEB1314(@Param("startDate") String startDate,@Param("endDate") String endDate);
	void createEB13list(int commitmentDetailID);
}
