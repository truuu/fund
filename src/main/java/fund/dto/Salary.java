package fund.dto;

public class Salary {
	int ID;
	String sponsorNo;
	String sponsorName;
	String amount;
	String paymentDate;

	public Salary(String sponsorNo, String sponsorName, String amount, String paymentDate) {

		this.sponsorNo = sponsorNo;
		this.sponsorName = sponsorName;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getSponsorNo() {
		return sponsorNo;
	}
	public void setSponsorNo(String sponsorNo) {
		this.sponsorNo = sponsorNo;
	}
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
}
