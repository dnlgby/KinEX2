package com.example.threadbarzel;

import com.example.threadbarzel.models.User;

public abstract class MyRunnable implements Runnable {
    private User mUser;
    public void setUser(User user){
        mUser = user;
    }
    public User getUser(){
        return mUser;
    }
}
