package fund.mapper;

import java.util.Date;
import java.util.List;

import fund.dto.EB13_CommitmentDetail;

public interface EB13_CommitmentDetailMapper {
	List<EB13_CommitmentDetail> selectEB13();//eb13���Ϸ� ���� ��� ���
	List<EB13_CommitmentDetail> selectEB14();//eb14������ ��ϰ� ���ϱ� ���� eb13���
	void updateEB14error(int sponsorNo);//���¸� ������ ���� 
	void updateEB14success(int sponsorNo,Date createDate);//���¸� �������� ����
	List<EB13_CommitmentDetail> selectEB1314();//eb13,14�� ��� ��ȸ
}
