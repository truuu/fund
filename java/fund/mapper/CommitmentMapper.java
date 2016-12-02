package fund.mapper;
import java.util.*;
import fund.dto.Commitment;
public interface CommitmentMapper {

	List<Commitment> selectCommitmentBySponsorNo(String sponsorNo);
	Commitment selectByCommitmentNo(String commitmentNo);
	Commitment selectIDBySponsorNo(String sponsorNo);
	Commitment selectByID(int ID);
	List<Commitment> selectBySponsorID(int ID);
	void insert(Commitment commitment);
	void update(Commitment commitment);
	void delete(int ID);
	void updateEndDate(int ID);
	int selectCountCommitment(int sponsorID);
	
}

