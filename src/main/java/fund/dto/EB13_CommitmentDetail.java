package fund.dto;

public class EB13_CommitmentDetail {
	int ID;
	int EB13ID;
	int commitmentDetailID;
	String state;
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


}
