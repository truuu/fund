package fund.mapper;

import java.util.List;

import fund.dto.CommitmentDetail;
import fund.dto.EB13_Commitment;
import fund.dto.EB21_commitmentDetail;

public interface CommitmentDetailMapper {
	
	CommitmentDetail selectMaxCommitmentDetailID();
	CommitmentDetail selectByCommitmentDetailID(int id);
	List<CommitmentDetail> selectByCommitmentID2(int commitmentID);
	void insert(CommitmentDetail commitmentDetail);
	void update(CommitmentDetail commitmentDetail);
	int selectCommitmentID();
	void delete(int ID);

	List<EB21_commitmentDetail> selectEB21(int pDay);//eb21���Ϸ� ���� ��� ���
	List<EB13_Commitment> selectEB13();//eb13���Ϸ� ���� ��� ���
}
