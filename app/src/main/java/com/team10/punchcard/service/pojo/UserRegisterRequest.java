package com.team10.punchcard.service.pojo;

/**
 * Created by leasunhy on 5/18/16.
 */
public class UserRegisterRequest {
    public String userName;
    public String name;
    public String password;

    public UserRegisterRequest() {}

    public UserRegisterRequest(String userName, String nickName, String password) {
        this.userName = userName;
        this.name = nickName;
        this.password = password;
    }
}
