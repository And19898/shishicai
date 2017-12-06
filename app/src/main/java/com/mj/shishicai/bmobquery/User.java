package com.mj.shishicai.bmobquery;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by xinru on 2017/12/3.
 */

public class User extends BmobObject implements Serializable {
    private static final long serialVersionUID = 4383515754631865886L;
    private String username;
    private String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
