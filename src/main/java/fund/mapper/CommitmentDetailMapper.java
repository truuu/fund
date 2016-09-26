package fund.mapper;

import java.util.List;

import fund.dto.EB13_CommitmentDetail;
import fund.dto.EB21_commitmentDetail;

public interface CommitmentDetailMapper {
	List<EB21_commitmentDetail> selectEB21(int pDay);//eb21���Ϸ� ���� ��� ���
	List<EB13_CommitmentDetail> selectEB13();//eb13���Ϸ� ���� ��� ���
}
