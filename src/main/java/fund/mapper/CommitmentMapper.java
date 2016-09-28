package fund.mapper;

import java.util.List;

import fund.dto.Commitment;

public interface CommitmentMapper {
	Commitment selectByID(int ID);
	List<Commitment> selectBySponsorID(int ID);
	void insert(Commitment commitment);
	void update(Commitment commitment);
	void delete(int ID);
	void updateEndDate(int ID);
	int selectCountCommitment(int sponsorID);
	
}
