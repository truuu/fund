package fund.dto;

import org.apache.commons.lang.StringUtils;

public class EB13Commitment {
    int id;
    int eb13id;
    int commitmentId;
    String state;
    String errorCode;

    //
    String sponsorNo;
    String name;
    String bankName;
    String accountNo;
    String accountHolder;
    String juminNo;
    String commitmentNo;

    //
    public boolean isValid() {
        return StringUtils.isNotBlank(juminNo) &&
               StringUtils.isNotBlank(accountNo) &&
               StringUtils.isNotBlank(bankName);
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

    public String getName() {
        return name;
    }

    public void setName(String sponsorName) {
        this.name = sponsorName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getJuminNo() {
        return juminNo;
    }

    public void setJuminNo(String juminNo) {
        this.juminNo = juminNo;
    }

    public String getCommitmentNo() {
        return commitmentNo;
    }

    public void setCommitmentNo(String commitmentNo) {
        this.commitmentNo = commitmentNo;
    }

}
