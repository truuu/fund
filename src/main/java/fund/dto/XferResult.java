package fund.dto;

import java.util.Date;

public class XferResult {
	String sponsorNo;
	String accountNo;
	String sponsorName;
	String amount;
	String paymentDate;
	String paymentWay;
	
	public XferResult(String accountNo,String sponsorName, String amount, String paymentDate, String paymentWay) {
		this.accountNo = accountNo;
		this.sponsorName = sponsorName;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.paymentWay = paymentWay;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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
	public String getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}
}