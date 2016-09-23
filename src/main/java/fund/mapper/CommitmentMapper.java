package fund.mapper;

import java.util.List;

import fund.dto.Commitment;

public interface CommitmentMapper {
	Commitment selectByID(int ID);
	List<Commitment> selectBySponsorID(int ID);
	void insert(Commitment commitment);
	void update(Commitment commitment);
	int selectCountCommitment(int sponsorID);
	
}
