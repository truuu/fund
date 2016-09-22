package fund.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import fund.dto.EB21_commitmentDetail;
import fund.dto.EB22;

public interface EB21_CommitmentDetailMapper {
	List<EB21_commitmentDetail> selectEB21(int pDay);//eb21���Ϸ� ���� ��� ���
	List<EB21_commitmentDetail> selectEB22(int paymentDay);//eb22������ ��ϰ� ���ϱ� ���� eb13���
	void updateEB21error(@Param("sponsorNo") String sponsorNo, @Param("paymentDate") Date paymentDate);//���¸� ������ ���� 
	void updateEB21success(@Param("paymentDate") Date paymentDate);//���¸� �������� ����
	List<EB21_commitmentDetail> selectEB2122();//eb21,22�� ��� ��ȸ
	EB22 selectSponsorName(String sponsorNo);
}
