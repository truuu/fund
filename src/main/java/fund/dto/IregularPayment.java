package fund.dto;

import javax.validation.constraints.Null;
import org.hibernate.validator.constraints.NotEmpty;

public class IregularPayment {
	int sponsorId;

	@Null(message="�ݾ��� �Է��ϼ���.")
	int amount;

	@NotEmpty(message="��¥�� �Է��ϼ���.")
	String paymentDate;

	@Null(message="��θ����� �����ϼ���.")
	int donationPurposeID;

	int paymentMethodID;

	String etc;

	public int getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(int sponsorId) {
		this.sponsorId = sponsorId;
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
