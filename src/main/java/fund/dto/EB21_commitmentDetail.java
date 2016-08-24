package fund.dto;

public class EB21_commitmentDetail {
	int ID;
	int EB21ID;
	int commitmentDetailID;
	String state;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getEB21ID() {
		return EB21ID;
	}
	public void setEB21ID(int eB21ID) {
		EB21ID = eB21ID;
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
