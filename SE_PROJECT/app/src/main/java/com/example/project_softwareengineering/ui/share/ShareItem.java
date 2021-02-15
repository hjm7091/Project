package com.example.project_softwareengineering.ui.share;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShareItem implements Serializable {

    private String title;
    private String content;
    private String name;
    private String email;
    private String date;
    private int like = 0;
    public Map<String, Boolean> map = new HashMap<>();

    public ShareItem(){

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

}
