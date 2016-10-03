package fund.dto;

public class Corporate {
	int ID;
	String name;
	String corporateNo;
	String representative;
	String address;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCorporateNo() {
		return corporateNo;
	}
	public void setCorporateNo(String corporateNo) {
		this.corporateNo = corporateNo;
	}
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
