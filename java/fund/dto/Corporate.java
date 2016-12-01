package fund.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class Corporate {
	int ID;
	
	@NotEmpty(message="기관명을 입력하세요!")
	String name;
	
	@NotEmpty(message="기관번호를 입력하세요!")
	String corporateNo;
	
	@NotEmpty(message="대표자명을 입력하세요!")
	String representative;
	
	@NotEmpty(message="주소를 입력하세요!")
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
