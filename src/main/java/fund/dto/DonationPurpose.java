package fund.dto;

public class DonationPurpose {
	int ID;
	int corporateID;
	int organizationID;
	String name;
	String gubun;
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
