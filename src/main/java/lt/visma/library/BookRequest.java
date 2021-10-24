package lt.visma.library;

import org.springframework.beans.factory.annotation.Required;

public class BookRequest {


    private String userName;
    private int periodInDays;
    private long guid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPeriodInDays() {
        return periodInDays;
    }

    public void setPeriodInDays(int periodInDays) {
        this.periodInDays = periodInDays;
    }

    public long getGuid() {
        return guid;
    }

    public void setGuid(long guid) {
        this.guid = guid;
    }

    public User getUser(){
        if (userName==null)return null;
        return new User(userName);
    }
}
