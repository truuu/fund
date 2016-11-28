package fund.mapper;

import java.util.List;

import fund.dto.CommitmentDetail;
import fund.dto.EB13_CommitmentDetail;
import fund.dto.EB21_commitmentDetail;

public interface CommitmentDetailMapper {
	CommitmentDetail selectByCommitmentID3(int commitmentID);
	List<CommitmentDetail> selectByCommitmentID2(int commitmentID);
	void insert(CommitmentDetail commitmentDetail);
	void update(CommitmentDetail commitmentDetail);
	int selectCommitmentID();
	void delete(int ID);

	List<EB21_commitmentDetail> selectEB21(int pDay);//eb21파일로 만들 목록 출력
	List<EB13_CommitmentDetail> selectEB13();//eb13파일로 만들 목록 출력
}
