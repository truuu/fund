package fund.dto;


public class CommitmentDetail {
	int ID;
	int commitmentID;
	int amountPerMonth;
	int paymentDay;
	String startDate;
	int bankID;
	String accountNo;
	String accountHolder;
	String etc;
	
	Integer amount2;
	
	public Integer getAmount2() {
		return amount2;
	}
	public void setAmount2(Integer amount2) {
		this.amount2 = amount2;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getCommitmentID() {
		return commitmentID;
	}
	public void setCommitmentID(int commitmentID) {
		this.commitmentID = commitmentID;
	}
	public int getAmountPerMonth() {
		return amountPerMonth;
	}
	public void setAmountPerMonth(int amountPerMonth) {
		this.amountPerMonth = amountPerMonth;
	}
	public int getPaymentDay() {
		return paymentDay;
	}
	public void setPaymentDay(int paymentDay) {
		this.paymentDay = paymentDay;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
	
}
