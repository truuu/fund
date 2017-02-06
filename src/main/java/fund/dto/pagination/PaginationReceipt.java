package fund.dto.pagination;

public class PaginationReceipt extends Pagination {

    String nm;
    String sd;
    String ed;

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    @Override
    public String getSd() {
        return sd;
    }

    @Override
    public void setSd(String sd) {
        this.sd = sd;
    }

    @Override
    public String getEd() {
        return ed;
    }

    @Override
    public void setEd(String ed) {
        this.ed = ed;
    }

    @Override
    public String getQueryString() {
        String s = super.getQueryString();
        return String.format("%s&nm=%s&sd=%s&ed=%s", s, nm, sd, ed);
    }

}
