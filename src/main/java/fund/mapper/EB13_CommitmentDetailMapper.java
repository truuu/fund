package fund.mapper;

import java.util.Date;
import java.util.List;

import fund.dto.EB13_CommitmentDetail;
import fund.dto.EB14;

public interface EB13_CommitmentDetailMapper {
	List<EB13_CommitmentDetail> selectEB14(String createDate);//eb14���쇱�� 紐⑸�怨� 鍮�援���湲� ���� eb13紐⑸�
	void updateEB14error(String sponsorNo);//����瑜� ���щ� 蹂�寃� 
	void updateEB14success(Date createDate);//����瑜� �깃났�쇰� 蹂�寃�
	List<EB13_CommitmentDetail> selectEB1314();//eb13,14�� 寃곌낵 議고��
	List<EB13_CommitmentDetail> test();
	void createEB13list(int commitmentDetailID);//eb13리스트생성 
}
