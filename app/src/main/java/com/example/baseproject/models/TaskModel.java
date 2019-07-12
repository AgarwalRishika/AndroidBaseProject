package com.example.baseproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * created by Rishika Agarwal on 6/26/2019.
 */
@Entity
public class TaskModel {
    @PrimaryKey(autoGenerate = true)
    int id;
    String title;
    String content;
    long date;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
