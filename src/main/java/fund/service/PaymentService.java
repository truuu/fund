package fund.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import fund.dto.IregularPayment;

@Service
public class PaymentService {
	public String validateBeforeInsert(IregularPayment iregularPayment) throws Exception {
		if(iregularPayment == null) return "��� �׸��� �Է����ּ���.";
			
		int s = iregularPayment.getAmount();
		if (s == 0)
			return "�ݾ��� �Է��ϼ���.";

		String d = iregularPayment.getPaymentDate();
		if (StringUtils.isBlank(d))
			return "��¥�� �Է��ϼ���.";

		int p = iregularPayment.getDonationPurposeID();
		if (p == 0)
			return "��θ����� �Է��ϼ���.";
		
		p = iregularPayment.getPaymentMethodID();
		if (p == 0)
			return "���Թ���� �Է��ϼ���.";

		return null;
	}
}
