package fund.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fund.dto.Payment;
import fund.dto.Receipt;
import fund.mapper.PaymentMapper;
import fund.mapper.ReceiptMapper;

@Service
public class ReceiptService {

	@Autowired PaymentMapper paymentMapper;
    @Autowired ReceiptMapper receiptMapper;

	public String createReceipt(String createDate, int[] pid) {
	    if (pid.length <= 0) return "납입 내역을 선택하세요.";

        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < pid.length; ++i) {
            payments.add(paymentMapper.selectById(pid[i]));
            if (payments.get(0).getSponsorId() != payments.get(i).getSponsorId())
                return "하나의 후원인만 선택해주세요.";
        }
        String receiptNo = receiptMapper.generateReceiptNo(createDate);
        Receipt receipt = new Receipt();
        receipt.setNo(receiptNo);
        receipt.setSponsorId(payments.get(0).getSponsorId());
        receipt.setCreateDate(createDate);
        receiptMapper.insert(receipt);
        for (Payment p : payments) {
            p.setReceiptId(receipt.getId());
            paymentMapper.update(p);
        }
        return null;
	}

}
