package fund.dto;

import javax.validation.constraints.Null;

public class Corporate {
	int ID;
	
	@Null(message="기관명을 입력하세요!")
	String name;
	
	@Null(message="기관번호를 입력하세요!")
	String corporateNo;
	
	@Null(message="대표자명을 입력하세요!")
	String representative;
	
	@Null(message="주소를 입력하세요!")
	String address;
	
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
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
