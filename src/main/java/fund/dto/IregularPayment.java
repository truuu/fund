package fund.dto;

import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotEmpty;

public class IregularPayment {
	int sponsorID;
	
	@Null(message="금액을 입력하세요.")
	int amount;
	
	@NotEmpty(message="날짜를 입력하세요.")
	String paymentDate;
	
	@Null(message="기부목적을 선택하세요.")
	int donationPurposeID;

	int paymentMethodID;
	
	String etc;
	
	public int getSponsorID() {
		return sponsorID;
	}
	public void setSponsorID(int sponsorID) {
		this.sponsorID = sponsorID;
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
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
	

}
