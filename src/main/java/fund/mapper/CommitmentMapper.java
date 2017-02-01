package fund.mapper;

import java.util.Date;
import java.util.List;
import fund.dto.Commitment;
import fund.param.CmsResultParam;
import fund.param.EB21Param;

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

	// CMS
	List<Commitment> selectEB13Candidate();
	List<Commitment> selectByEB13Date(Date date);
	List<Commitment> selectByCmsResultParam(CmsResultParam param);
	void updateEB13(Commitment commitment);

    List<Commitment> selectEB21Candidate(EB21Param param);
}

