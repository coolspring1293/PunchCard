package com.team10.punchcard.service.pojo;


/**
 * Created by leasunhy on 5/18/16.
 */
public class LoginRequest {
    public String userName;
    public String password;

    public LoginRequest() {}
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
