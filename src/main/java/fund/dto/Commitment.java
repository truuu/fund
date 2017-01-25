package fund.dto;

public class Commitment {
    int id;
    int sponsorId;
    String commitmentNo;
    int donationPurposeId;
    int paymentMethodId;
    String commitmentDate;
    String startDate;
    String endDate;
    int amountPerMonth;
    int paymentDay;
    int bankId;
    String accountNo;
    String accountHolder;
    String etc;

    String paymentMethodName;
    String bankName;
    String donationPurposeName;
    String corporateName;
    String organizationName;
    String state;
    int months;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(int sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getCommitmentNo() {
        return commitmentNo;
    }

    public void setCommitmentNo(String commitmentNo) {
        this.commitmentNo = commitmentNo;
    }

    public int getDonationPurposeId() {
        return donationPurposeId;
    }

    public void setDonationPurposeId(int donationPurposeId) {
        this.donationPurposeId = donationPurposeId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
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

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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