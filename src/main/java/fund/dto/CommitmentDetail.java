package fund.dto;

import java.util.Date;

public class CommitmentDetail {
	int ID;
	int commitmentID;
	int amountPerMonth;
	int paymentDay;
	Date startDate;
	int bankID;
	String accountNo;
	String accountHolder;
	String etc;
	
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
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
