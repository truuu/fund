package fund.mapper;

import java.util.Date;
import java.util.List;

import fund.dto.EB21_commitmentDetail;

public interface EB21_CommitmentDetailMapper {
	List<EB21_commitmentDetail> selectEB21();//eb21���Ϸ� ���� ��� ���
	List<EB21_commitmentDetail> selectEB22();//eb22������ ��ϰ� ���ϱ� ���� eb13���
	void updateEB21error(int sponsorNo);//���¸� ������ ���� 
	void updateEB21success(int sponsorNo,Date createDate);//���¸� �������� ����
	List<EB21_commitmentDetail> selectEB2122();//eb21,22�� ��� ��ȸ
}
