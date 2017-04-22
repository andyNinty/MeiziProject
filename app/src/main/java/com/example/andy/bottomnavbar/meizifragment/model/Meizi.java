package com.example.andy.bottomnavbar.meizifragment.model;

/**
 * 妹子实体类
 * Created by andy on 17-4-21.
 */

public class Meizi {
    private String createdAt;
    private String url;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MeiziModel{" +
                "createdAt='" + createdAt + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
