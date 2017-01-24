package fund.dto;

public class Commitment {
    int ID;
    int sponsorID;
    String commitmentNo;
    int donationPurposeID;
    int paymentMethodID;
    String commitmentDate;
    String startDate;
    String endDate;
    int amountPerMonth;
    int paymentDay;
    int bankID;
    String accountNo;
    String accountHodler;
    String etc;

    String paymentMethodName;
    String donationPurposeName;
    String corporateName;
    String organizationName;
    String state;
    int months;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(int sponsorID) {
        this.sponsorID = sponsorID;
    }

    public String getCommitmentNo() {
        return commitmentNo;
    }

    public void setCommitmentNo(String commitmentNo) {
        this.commitmentNo = commitmentNo;
    }

    public int getDonationPurposeID() {
        return donationPurposeID;
    }

    public void setDonationPurposeID(int donationPurposeID) {
        this.donationPurposeID = donationPurposeID;
    }

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
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

    public int getAmountPerMonth() {
        return amountPerMonth;
    }

    public void setAmountPerMonth(int amountPerMonth) {
        this.amountPerMonth = amountPerMonth;
    }

    public int getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(int paymentDay) {
        this.paymentDay = paymentDay;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHodler() {
        return accountHodler;
    }

    public void setAccountHodler(String accountHodler) {
        this.accountHodler = accountHodler;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getDonationPurposeName() {
        return donationPurposeName;
    }

    public void setDonationPurposeName(String donationPurposeName) {
        this.donationPurposeName = donationPurposeName;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

}