package fund.dto;

import java.util.Date;

public class Payment {
	int ID;
	int sponsorID;
	int commitmentID;
	int amount;
	String paymentDate;
	String etc;
	int receiptID;
	int donationPurposeID;
	int paymentMethodID;

	
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
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
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
	
}
