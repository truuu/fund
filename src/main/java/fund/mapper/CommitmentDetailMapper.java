package fund.mapper;

import fund.dto.CommitmentDetail;

public interface CommitmentDetailMapper {
	CommitmentDetail selectByCommitmentID2(int commitmentID);
	void insert(CommitmentDetail commitmentDetail);
	void update(CommitmentDetail commitmentDetail);
	int selectCommitmentID();

}
