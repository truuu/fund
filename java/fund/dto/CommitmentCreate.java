package fund.dto;

public class CommitmentCreate {
	
	String commitmentStartDate;       // 공통
	
	String commitmentDetailStartDate;
	String commitmentEtc;
	String commitmentDetailEtc;
	int sponsorID;                 //  commitment
	Integer donationPurposeID;
	int paymentMethodID;
	String commitmentDate;
	String endDate;
	Integer amountPerMonth;    // commitmentDetail
	int paymentDay;
	int bankID;
	String accountNo;
	String accountHolder;
	
	String dname;   // 기부목적이름
	String corporateName;  // 기관이름
	
	
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public String getCommitmentStartDate() {
		return commitmentStartDate;
	}
	public void setCommitmentStartDate(String commitmentStartDate) {
		this.commitmentStartDate = commitmentStartDate;
	}
	public String getCommitmentDetailStartDate() {
		return commitmentDetailStartDate;
	}
	public void setCommitmentDetailStartDate(String commitmentDetailStartDate) {
		this.commitmentDetailStartDate = commitmentDetailStartDate;
	}
	public String getCommitmentEtc() {
		return commitmentEtc;
	}
	public void setCommitmentEtc(String commitmentEtc) {
		this.commitmentEtc = commitmentEtc;
	}
	public String getCommitmentDetailEtc() {
		return commitmentDetailEtc;
	}
	public void setCommitmentDetailEtc(String commitmentDetailEtc) {
		this.commitmentDetailEtc = commitmentDetailEtc;
	}
	public int getSponsorID() {
		return sponsorID;
	}
	public void setSponsorID(int sponsorID) {
		this.sponsorID = sponsorID;
	}
	
	public Integer getDonationPurposeID() {
		return donationPurposeID;
	}
	public void setDonationPurposeID(Integer donationPurposeID) {
		this.donationPurposeID = donationPurposeID;
	}
	public int getPaymentMethodID() {
		return paymentMethodID;
	}
	public void setPaymentMethodID(int paymentMethodID) {
		this.paymentMethodID = paymentMethodID;
	}
	

	public String getCommitmentDate() {
		return commitmentDate;
	}
	public void setCommitmentDate(String commitmentDate) {
		this.commitmentDate = commitmentDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getPaymentDay() {
		return paymentDay;
	}
	
	public Integer getAmountPerMonth() {
		return amountPerMonth;
	}
	public void setAmountPerMonth(Integer amountPerMonth) {
		this.amountPerMonth = amountPerMonth;
	}
	public void setPaymentDay(int paymentDay) {
		this.paymentDay = paymentDay;
	}
	public int getBankID() {
		return bankID;
	}
	public void setBankID(int bankID) {
		this.bankID = bankID;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	
	
}