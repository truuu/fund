package fund.mapper;

import fund.dto.CommitmentDetail;

public interface CommitmentDetailMapper {
	CommitmentDetail selectById(int id);
	void insert(CommitmentDetail commitmentDetail);
	void update(CommitmentDetail commitmentDetail);

}
