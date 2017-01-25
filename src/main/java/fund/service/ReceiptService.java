package fund.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fund.mapper.PaymentMapper;

@Service
public class ReceiptService {

	@Autowired
	PaymentMapper paymentMapper;

	public String validateBeforeInsert(int[] pid){
		int sid =  paymentMapper.selectById(pid[0]).getSponsorId();;
		for (int i = 1; i < pid.length; ++i)
			if (sid != paymentMapper.selectById(pid[i]).getSponsorId())
				return "하나의 후원인만 선택해주세요.";
		return null;
	}
}
