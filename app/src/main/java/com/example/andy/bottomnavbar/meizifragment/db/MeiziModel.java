package com.example.andy.bottomnavbar.meizifragment.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * meizi 数据库实体类
 * Created by andy on 17-4-21.
 */
@Table(database = MeiziDatabase.class)
public class MeiziModel extends BaseModel {
    @PrimaryKey(autoincrement = true)
    long id;
    @Column
    String imgUrl;
    @Column
    String createTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
