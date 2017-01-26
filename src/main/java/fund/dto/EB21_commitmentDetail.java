package fund.dto;

import java.util.Date;
import fund.service.EncryptService;

public class EB21_commitmentDetail {
	EncryptService cipherService=new EncryptService(); //����� ��ȣȭ ����
	
	int ID;
	int EB21ID;
	int commitmentDetailID;
	int commitmentID;
	String state;
	
	String sponsorNo;
	String accountHolder;
	String jumin;
	String jumin2;
	String codeName;
	String accountNo;
	int amountPerMonth;
	int paymentDay;
	int sponsorType1Id;
	
	String createDate;
	String commitmentNo;
	String donationPurpose;
	String name;
	String etc1;
	Date paymentDate;
	int sponsorID;
	String description;
	
	public int getsponsorType1Id() {
		return sponsorType1Id;
	}
	public void setsponsorType1Id(int sponsorType1Id) {
		this.sponsorType1Id = sponsorType1Id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJumin2() throws Exception {
		String test = cipherService.decAES(jumin2);
		System.out.println(test);
		return test;
	}
	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getSponsorID() {
		return sponsorID;
	}
	public void setSponsorID(int sponsorID) {
		this.sponsorID = sponsorID;
	}
	public int getPaymentDay() {
		return paymentDay;
	}
	public void setPaymentDay(int paymentDay) {
		this.paymentDay = paymentDay;
	}
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
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
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
	public int getAmountPerMonth() {
		return amountPerMonth;
	}
	public void setAmountPerMonth(int amountPerMonth) {
		this.amountPerMonth = amountPerMonth;
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
	public int getCommitmentID() {
		return commitmentID;
	}
	public void setCommitmentID(int commitmentID) {
		this.commitmentID = commitmentID;
	}
}
