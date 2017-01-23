package fund.dto;

import java.io.UnsupportedEncodingException;

public class PaginationSponsor extends Pagination {

    int st1;
    int st2;

    public int getSt1() {
        return st1;
    }

    public void setSt1(int st1) {
        this.st1 = st1;
    }

    public int getSt2() {
        return st2;
    }

    public void setSt2(int st2) {
        this.st2 = st2;
    }

    public String getQueryString() throws UnsupportedEncodingException {
        String s = super.getQueryString();
        return String.format("%s&st1=%d&st2=%d", s, st1, st2);
    }

}
