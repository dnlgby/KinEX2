package com.example.threadbarzel.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    @Expose()
    private String mName;

    @SerializedName("email")
    @Expose()
    private String mEmail;

    @SerializedName("phone")
    @Expose()
    private String mPhone;

    @SerializedName("website")
    @Expose()
    private String mWebSite;

    public User(String mName, String mEmail, String mPhone, String mWebSite) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mWebSite = mWebSite;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public String getWebSite() {
        return mWebSite;
    }

    public void setWebSite(String webSite) {
        this.mWebSite = webSite;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name=" + mName +
                ", Email=" + mEmail +
                ", Phone='" + mPhone + '\'' +
                ", WebSite='" + mWebSite + '\'' +
                '}';
    }
}
