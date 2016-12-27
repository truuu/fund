package fund.dto;

public class EB22 {
	String sponsorNo;
	String jumin;
	String bankCode;
	String accountNo;
	int amountPerMonth;
	String paymentDate;
	String name;
	String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSponsorNo() {
		return sponsorNo;
	}
	public void setSponsorNo(String sponsorNo) {
		this.sponsorNo = sponsorNo;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public int getAmountPerMonth() {
		return amountPerMonth;
	}
	public void setAmountPerMonth(int amountPerMonth) {
		this.amountPerMonth = amountPerMonth;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
}