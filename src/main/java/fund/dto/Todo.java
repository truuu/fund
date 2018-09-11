package fund.dto;

import java.util.Date;

public class Todo {

    int id;
    int userId;
    String userName;
    Date createDate;
    Date dueDate;
    Date dueDate2;
    String message;
    int repeat;
    int alertBefore;
    Date conformDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getAlertBefore() {
        return alertBefore;
    }

    public void setAlertBefore(int alertBefore) {
        this.alertBefore = alertBefore;
    }

    public Date getConformDate() {
        return conformDate;
    }

    public void setConformDate(Date conformDate) {
        this.conformDate = conformDate;
    }

    public Date getDueDate2() {
        return dueDate2;
    }

    public void setDueDate2(Date dueDate2) {
        this.dueDate2 = dueDate2;
    }

}