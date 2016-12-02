package fund.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import fund.dto.IregularPayment;

@Service
public class PaymentService {
	public String validateBeforeInsert(IregularPayment iregularPayment) throws Exception {
		if(iregularPayment == null) return "모든 항목을 입력해주세요.";
			
		int s = iregularPayment.getAmount();
		if (s == 0)
			return "금액을 입력하세요.";

		String d = iregularPayment.getPaymentDate();
		if (StringUtils.isBlank(d))
			return "날짜를 입력하세요.";

		int p = iregularPayment.getDonationPurposeID();
		if (p == 0)
			return "기부목적을 입력하세요.";
		
		p = iregularPayment.getPaymentMethodID();
		if (p == 0)
			return "납입방법을 입력하세요.";

		return null;
	}
}
