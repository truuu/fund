package fund.mapper;

import java.util.List;

import fund.dto.CommitmentDetail;

public interface CommitmentDetailMapper {
	List<CommitmentDetail> selectByCommitmentID2(int commitmentID);
	void insert(CommitmentDetail commitmentDetail);
	void update(CommitmentDetail commitmentDetail);
	int selectCommitmentID();
	void delete(int ID);

}
