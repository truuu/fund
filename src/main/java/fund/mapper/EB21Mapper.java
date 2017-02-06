package fund.mapper;

import java.util.Date;
import java.util.List;
import fund.dto.EB21;
import fund.dto.param.CmsResultParam;

public interface EB21Mapper {
    List<EB21> selectByPaymentDate(Date paymentDate);
    EB21 selectByCommitmentNo(String commitmentNo);
    List<EB21> selectByCmsResultParam(CmsResultParam param);
	void insert(EB21 eb21);
    void update(EB21 eb21);
}
