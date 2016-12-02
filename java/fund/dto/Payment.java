package fund.dto;

import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;

public class Payment {
	int id;
	
	@NotEmpty(message="id를 입력하세요.")
	int sponsorID;
	
	int commitmentID;
	
	@NotEmpty(message="금액을 입력하세요.")
	int amount;
	
	@NotEmpty(message="날짜를 입력하세요.")
	Date paymentDate;
	
	String etc;
	int receiptID;
	
	@NotEmpty(message="기부목적을 선택하세요.")
	int donationPurposeID;
	
	@NotEmpty(message="납입방법을 선택하세요.")
	int paymentMethodID;
	
	String donationPurpose; // 기부목적name
	int count1; // 기부후원인 수 count
	int count2; // 기부목적 count
	int sum; // 총 기부금 
	double percent; // 백분율
	String sponsorNo;
	String name; // 후원인 이름
	String sponsorType2; // 후원인구분2
	String church;
	String gubun; // 정기/비정기 구분
	String paymentMethod; // 납입 방법
	int totalSum; // 납입 총계 후원인당 총액
	int totalCount;  // 납입 총계 후원인당 총납입수
	
	String juminNo;

	String commitmentNo;
	String accountNo;
	String bankName;
	String accountHoler;
	String corporate;
	int amountPerMonth;
	
	String rctNo;//영수증번호
	String corporateID;
	
	String accountHolder;//예금주...
	String paymentDateString;
	

	
	public String getPaymentDateString() {
		return paymentDateString;
	}
	public void setPaymentDateString(String paymentDateString) {
		this.paymentDateString = paymentDateString;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getCommitmentNo() {
		return commitmentNo;
	}
	public void setCommitmentNo(String commitmentNo) {
		this.commitmentNo = commitmentNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountHoler() {
		return accountHoler;
	}
	public void setAccountHoler(String accountHoler) {
		this.accountHoler = accountHoler;
	}
	public String getCorporate() {
		return corporate;
	}
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	public String getDonationPurpose() {
		return donationPurpose;
	}
	public void setDonationPurpose(String donationPurpose) {
		this.donationPurpose = donationPurpose;
	}
	public int getAmountPerMonth() {
		return amountPerMonth;
	}
	public void setAmountPerMonth(int amountPerMonth) {
		this.amountPerMonth = amountPerMonth;
	}
	
	public String getJuminNo() {
		return juminNo;
	}
	public void setJuminNo(String juminNo) {
		this.juminNo = juminNo;
	}
	
	public String getCorporateID() {
		return corporateID;
	}
	public void setCorporateID(String corporateID) {
		this.corporateID = corporateID;
	}
	public String getRctNo() {
		return rctNo;
	}
	public void setRctNo(String rctNo) {
		this.rctNo = rctNo;
	}
	public String getSponsorNo() {
		return sponsorNo;
	}
	public void setSponsorNo(String sponsorNo) {
		this.sponsorNo = sponsorNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSponsorID() {
		return sponsorID;
	}
	public void setSponsorID(int sponsorID) {
		this.sponsorID = sponsorID;
	}
	public int getCommitmentID() {
		return commitmentID;
	}
	public void setCommitmentID(int commitmentID) {
		this.commitmentID = commitmentID;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public int getReceiptID() {
		return receiptID;
	}
	public void setReceiptID(int receiptID) {
		this.receiptID = receiptID;
	}
	public int getDonationPurposeID() {
		return donationPurposeID;
	}
	public void setDonationPurposeID(int donationPurposeID) {
		this.donationPurposeID = donationPurposeID;
	}
	public int getPaymentMethodID() {
		return paymentMethodID;
	}
	public void setPaymentMethodID(int paymentMethodID) {
		this.paymentMethodID = paymentMethodID;
	}
	
	public int getCount1() {
		return count1;
	}
	public void setCount1(int count1) {
		this.count1 = count1;
	}
	public int getCount2() {
		return count2;
	}
	public void setCount2(int count2) {
		this.count2 = count2;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public String getSponsorType2() {
		return sponsorType2;
	}
	public void setSponsorType2(String sponsorType2) {
		this.sponsorType2 = sponsorType2;
	}
	public String getChurch() {
		return church;
	}
	public void setChurch(String church) {
		this.church = church;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public int getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(int totalSum) {
		this.totalSum = totalSum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}