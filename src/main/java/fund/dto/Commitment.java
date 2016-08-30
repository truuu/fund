package fund.dto;

import java.util.Date;

public class Commitment{
	int ID;
	int sponsorID;
	String commitmentNo;
	int donationPurposeID;
	int paymentMethodID;
	Date commitmentDate;
	Date startDate;
	Date endDate;
	String etc;
	String name;//donationPurpose name
	String codeName; // paymentMethodID
	
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
	public String getCommitmentNo() {
		return commitmentNo;
	}
	public void setCommitmentNo(String commitmentNo) {
		this.commitmentNo = commitmentNo;
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
	public Date getCommitmentDate() {
		return commitmentDate;
	}
	public void setCommitmentDate(Date commitmentDate) {
		this.commitmentDate = commitmentDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	
}
