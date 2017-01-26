package fund.dto;

import java.io.UnsupportedEncodingException;

public class PaginationSponsor extends Pagination {

    int st1;
    int st2;
    int od1;

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

    public int getOd1() {
        return od1;
    }

    public void setOd1(int od1) {
        this.od1 = od1;
    }

    @Override
    public String getQueryString() throws UnsupportedEncodingException {
        String s = super.getQueryString();
        return String.format("%s&st1=%d&st2=%d&od1=%d", s, st1, st2, od1);
    }

}
