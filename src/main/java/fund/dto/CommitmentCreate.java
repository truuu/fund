package fund.dto;

import java.util.Date;

public class CommitmentCreate {
	//Date commitmentStartDate;       // 공통
	//Date commitmentDetailStartDate;
	String commitmentEtc;
	String commitmentDetailEtc;
	
	int sponsorID;                 //  commitment에만
	int donationPurposeID;
	int paymentMethodID;
	//Date commitmentDate;
	//Date endDate;
	
	
	           
	String amountPerMonth;   //1회납입액 일단 string으로 받아서 컨트롤러에서 다시 int로 변경하기 //  commitmentDetail에만
	int paymentDay;
	int bankID;
	String accountNo;
	String accountHolder;
	
	/*
	public Date getCommitmentStartDate() {
		return commitmentStartDate;
	}
	public void setCommitmentStartDate(Date commitmentStartDate) {
		this.commitmentStartDate = commitmentStartDate;
	}
	public Date getCommitmentDetailStartDate() {
		return commitmentDetailStartDate;
	}
	public void setCommitmentDetailStartDate(Date commitmentDetailStartDate) {
		this.commitmentDetailStartDate = commitmentDetailStartDate;
	}*/
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
	/*
	public Date getCommitmentDate() {
		return commitmentDate;
	}
	public void setCommitmentDate(Date commitmentDate) {
		this.commitmentDate = commitmentDate;
	}
	/*
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	*/
	
	public int getPaymentDay() {
		return paymentDay;
	}
	
	public String getAmountPerMonth() {
		return amountPerMonth;
	}
	public void setAmountPerMonth(String amountPerMonth) {
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
