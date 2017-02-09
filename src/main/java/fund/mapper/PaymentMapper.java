package fund.mapper;

import java.util.List;
import java.util.Map;
import fund.dto.Payment;


public interface PaymentMapper {

    Payment selectById(int id);
    List<Payment> selectPaymentList2(int sponsorId);
    List<Payment> selectPaymentList1(int commitmentId);

    void update(Payment payment);
    void delete(int id);
    void insert(Payment payment);

    List<Payment> selectForReceiptCreation1(Map<String,Object> map);
    List<Payment> selectForReceiptCreation2(Map<String,Object> map);
    List<Payment> selectByReceiptId(int rid);
    List<Payment> selectForTaxData(Map<String,Object> map);

}
