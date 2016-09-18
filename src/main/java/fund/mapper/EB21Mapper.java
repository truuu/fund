package fund.mapper;

public interface EB21Mapper {
	void createEB21file(String paymentDay);
	void createEB21List(int[] commitmentDetailID);//eb21파일생성
}
