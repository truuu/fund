package fund.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import fund.service.*;

public class EB13_CommitmentDetail {
	AES128UtilService cipherService=new AES128UtilService(); //양방향 암호화 서비스
	
	int ID;
	int EB13ID;
	int commitmentDetailID;
	String state;
	
	String sponsorNo;
	String accountHolder;
	String codeName;
	String accountNo;
	String jumin;
	String jumin2;
	int sponsorType1ID;
	
	String createDate;
	String commitmentNo;
	String donationPurpose;
	String name;
	String etc1;
	String description;
	
	Date startDate;
	Date endDate;
	
	public int getSponsorType1ID() {
		return sponsorType1ID;
	}
	public void setSponsorType1ID(int sponsorType1ID) {
		this.sponsorType1ID = sponsorType1ID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getJumin2() throws Exception {
		String test=cipherService.decAES(jumin2);
		return test;
	}
	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCommitmentNo() {
		return commitmentNo;
	}
	public void setCommitmentNo(String commitmentNo) {
		this.commitmentNo = commitmentNo;
	}
	public String getDonationPurpose() {
		return donationPurpose;
	}
	public void setDonationPurpose(String donationPurpose) {
		this.donationPurpose = donationPurpose;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEtc1() {
		return etc1;
	}
	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}

