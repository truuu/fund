package fund.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import fund.dto.EB21_commitmentDetail;
import fund.dto.Payment;

public interface EB21_CommitmentDetailMapper {
	List<EB21_commitmentDetail> selectEB22(int paymentDay);//eb22������ ��ϰ� ���ϱ� ���� eb13���
	void updateEB21error(@Param("sponsorNo") String sponsorNo, @Param("paymentDate") Date paymentDate);//���¸� ������ ���� 
	void updateEB21success(@Param("paymentDate") Date paymentDate);//���¸� �������� ����
	List<EB21_commitmentDetail> selectEB2122(@Param("startDate") String startDate,@Param("endDate") String endDate);//eb21,22�� ��� ��ȸ
	List<Payment> selectEB21success();
	void createEB21List(int commitmentDetailID);//eb21���ϻ���
}
