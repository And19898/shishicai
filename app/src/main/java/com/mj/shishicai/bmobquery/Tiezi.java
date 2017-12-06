package com.mj.shishicai.bmobquery;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * author: Rea.X
 * date: 2017/12/4.
 */

public class Tiezi extends BmobObject implements Serializable {

    private String title;
    private String content;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
