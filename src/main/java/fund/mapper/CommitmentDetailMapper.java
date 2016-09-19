package fund.mapper;

import fund.dto.CommitmentDetail;

public interface CommitmentDetailMapper {
	//CommitmentDetail selectByID(int iD);
	void insert(CommitmentDetail commitmentDetail);
	void update(CommitmentDetail commitmentDetail);

}
