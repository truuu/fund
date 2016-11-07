package fund.dto;

import java.util.Date;
import java.util.List;

public class Receipt {
	int ID;
	int sponsorID;
	String createDate;
	String no;
	
	String name;
	int amount;//기부금 영수증당 합계금액
	String juminNo;
	String mobilePhone;
	
	String address;
	String corName;
	String corporateNo;
	String corAddress;
	String representative;
	
	List<Payment> paymentList;
	
	@Override
	public boolean equals(Object obj) {
		if((obj instanceof Receipt)== false) return false;
		
		Receipt r = (Receipt)obj;
		if(this.ID != r.ID) return false;
		if(this.sponsorID != r.sponsorID) return false;
		if((this.createDate == null ? r.createDate == null : createDate.equals(r.createDate)) == false ) return false;
		if((this.no == null ? r.no == null : no.equals(r.no))== false) return false;
		return true;
	}
	
	public List<Payment> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCorName() {
		return corName;
	}
	public void setCorName(String corName) {
		this.corName = corName;
	}
	public String getCorporateNo() {
		return corporateNo;
	}
	public void setCorporateNo(String corporateNo) {
		this.corporateNo = corporateNo;
	}
	public String getCorAddress() {
		return corAddress;
	}
	public void setCorAddress(String corAddress) {
		this.corAddress = corAddress;
	}
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getJuminNo() {
		return juminNo;
	}
	public void setJuminNo(String juminNo) {
		this.juminNo = juminNo;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
	public void setID(int ID) {
		this.ID = ID;
	}
	public int getSponsorID() {
		return sponsorID;
	}
	public void setSponsorID(int sponsorID) {
		this.sponsorID = sponsorID;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}

}
