package fund.dto;

import java.util.Date;
import fund.service.EncryptService;

public class EB13_Commitment {
    int id;
    int eb13id;
    int commitmentId;
    String state;
    String errorCode;

    //
    String sponsorNo;
    String accountHolder;
    String codeName;
    String accountNo;
    String jumin;
    int sponsorType1Id;
    String createDate;
    String commitmentNo;
    String donationPurpose;
    String name;
    String etc1;
    String description;
    Date startDate;
    Date endDate;

    //
    public String getJumin2() throws Exception {
        return EncryptService.decAES(jumin);
    }

    //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEb13id() {
        return eb13id;
    }

    public void setEb13id(int eb13id) {
        this.eb13id = eb13id;
    }

    public int getCommitmentId() {
        return commitmentId;
    }

    public void setCommitmentId(int commitmentId) {
        this.commitmentId = commitmentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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

    public int getSponsorType1Id() {
        return sponsorType1Id;
    }

    public void setSponsorType1Id(int sponsorType1Id) {
        this.sponsorType1Id = sponsorType1Id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
