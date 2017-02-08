package fund.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import fund.dto.EB21;

public interface EB21Mapper {
    List<EB21> selectByPaymentDate(Date paymentDate);
    EB21 selectByCommitmentNo(String commitmentNo);
    List<EB21> selectCmsResult(Map<String,Object> map);
	void insert(EB21 eb21);
    void update(EB21 eb21);
}
