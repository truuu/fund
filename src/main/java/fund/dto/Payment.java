package fund.dto;

import java.util.Date;

public class Payment {
	int ID;
	int sponsorID;
	int commitmentID;
	int amount;
	Date paymentDate;
	String stc;
	int receiptID;
	int donationPurposeID;
	int paymentMethodID;
	
	String sponsorNo;
	String name; //후원인명
	
	String no;//영수증번호 
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public String getStc() {
		return stc;
	}
	public void setStc(String stc) {
		this.stc = stc;
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
	
}
