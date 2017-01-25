package fund.mapper;

import java.util.List;
import fund.dto.Commitment;

public interface CommitmentMapper {

    Commitment selectById(int id);
	Commitment selectByCommitmentNo(String commitmentNo);

	Commitment selectBySponsorAndPaymentMethod(String sponsorNo, int paymentMethodid);
	List<Commitment> selectBySponsorId(int id);
	String generateCommitmentNo(int sponsorId);

	void insert(Commitment commitment);
	void update(Commitment commitment);
	void delete(int id);
	void updateEndDate(int id);
}

