package com.example.andy.meizi.articlefragment.db;

import com.example.andy.meizi.meizifragment.db.MeiziDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * article table
 * Created by andy on 17-4-24.
 */

@Table(database = MeiziDatabase.class)
public class ArticleModel extends BaseModel {
    @PrimaryKey
    int id;
    @Column
    String title;
    @Column
    String author;
    @Column
    String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

