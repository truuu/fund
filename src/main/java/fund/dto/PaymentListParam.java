package fund.dto;

public class PaymentListParam {
    int sponsorId;
    String orderBy;

    public PaymentListParam() {
    }

    public PaymentListParam(int sponsorId, String orderBy) {
        this.sponsorId = sponsorId;
        this.orderBy = orderBy;
    }

    public int getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(int sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

}
