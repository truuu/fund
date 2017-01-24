package fund.mapper;

import java.util.List;
import fund.dto.Commitment;

public interface CommitmentMapper {

    Commitment selectByID(int ID);
	Commitment selectByCommitmentNo(String commitmentNo);

	Commitment selectBySponsorAndPaymentMethod(String sponsorNo, int paymentMethodID);
	List<Commitment> selectBySponsorID(int ID);

	void insert(Commitment commitment);
	void update(Commitment commitment);
	void delete(int ID);
	void updateEndDate(int ID);
}

