package fund.dto;

public class CommitmentUpdate {

	int donationPurposeID;
	String commitmentDate;
	String startDate;
	String endDate;
	String etc;
	

	public int getDonationPurposeID() {
		return donationPurposeID;
	}
	public void setDonationPurposeID(int donationPurposeID) {
		this.donationPurposeID = donationPurposeID;
	}
	public String getCommitmentDate() {
		return commitmentDate;
	}
	public void setCommitmentDate(String commitmentDate) {
		this.commitmentDate = commitmentDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
	

}
