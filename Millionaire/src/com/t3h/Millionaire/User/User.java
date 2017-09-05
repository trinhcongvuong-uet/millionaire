package com.t3h.Millionaire.User;

/**
 * Created by Wrongway on 14/01/2016.
 */
public class User {
    private String username;
    private int usermoney;

    public User(){
        this.username = "";
        this.usermoney = 0;
    }

    public User(String username,int usermoney){
        this.username = username;
        this.usermoney = usermoney;
    }

    public String getUsername() {
        return username;
    }

    public int getUsermoney() {
        return usermoney;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsermoney(int usermoney) {
        this.usermoney = usermoney;
    }
}
