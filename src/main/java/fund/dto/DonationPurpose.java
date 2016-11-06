package fund.dto;

import javax.validation.constraints.Null;

public class DonationPurpose {
	int ID;
	int corporateID;
	int organizationID;
	
	@Null(message="기부목적명을 입력하세요!")
	String name;
	
	@Null(message="구분을 입력하세요!")
	String gubun;
	
	String codeName;
	String corporateName;
	
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getCorporateID() {
		return corporateID;
	}
	public void setCorporateID(int corporateID) {
		this.corporateID = corporateID;
	}
	public int getOrganizationID() {
		return organizationID;
	}
	public void setOrganizationID(int organizationID) {
		this.organizationID = organizationID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}


}
