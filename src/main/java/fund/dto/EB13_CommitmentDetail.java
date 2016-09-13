package fund.dto;

public class EB13_CommitmentDetail {
	int ID;
	int EB13ID;
	int commitmentDetailID;
	String state;
	
	String sponsorNo;
	String accountHolder;
	String codeName;
	String accountNo;
	String juminNo;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getEB13ID() {
		return EB13ID;
	}
	public void setEB13ID(int eB13ID) {
		EB13ID = eB13ID;
	}
	public int getCommitmentDetailID() {
		return commitmentDetailID;
	}
	public void setCommitmentDetailID(int commitmentDetailID) {
		this.commitmentDetailID = commitmentDetailID;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSponsorNo() {
		return sponsorNo;
	}
	public void setSponsorNo(String sponsorNo) {
		this.sponsorNo = sponsorNo;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getJuminNo() {
		return juminNo;
	}
	public void setJuminNo(String juminNo) {
		this.juminNo = juminNo;
	}

}
