package fund.dto;

import java.util.Date;

public class Sal {
    String commitmentNo;
    String name;
    int amount;
    Date date;
    String etc;

    public Sal(String commitmentNo, String name, int amount, Date date, String etc) {
        this.commitmentNo = commitmentNo;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.etc = etc;
    }

    public String getCommitmentNo() {
        return commitmentNo;
    }

    public void setCommitmentNo(String commitmentNo) {
        this.commitmentNo = commitmentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

}
