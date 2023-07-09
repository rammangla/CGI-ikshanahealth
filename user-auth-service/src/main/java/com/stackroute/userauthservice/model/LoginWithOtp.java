package com.stackroute.userauthservice.model;

public class LoginWithOtp {
    private String username;
    private int otpnumber;

    public LoginWithOtp(String username, int otpnumber) {
        this.username = username;
        this.otpnumber = otpnumber;
    }

    public LoginWithOtp() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOtpnumber() {
        return otpnumber;
    }

    public void setOtpnumber(int otpnumber) {
        this.otpnumber = otpnumber;
    }
}
