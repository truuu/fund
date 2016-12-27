package fund.dto;

import javax.validation.constraints.Null;

public class Corporate {
	int ID;
	
	@Null(message="������� �Է��ϼ���!")
	String name;
	
	@Null(message="�����ȣ�� �Է��ϼ���!")
	String corporateNo;
	
	@Null(message="��ǥ�ڸ��� �Է��ϼ���!")
	String representative;
	
	@Null(message="�ּҸ� �Է��ϼ���!")
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
