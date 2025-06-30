package com.codewithsam.store.entity;

public class JournalEntry {
    private String date;
    private String title;
    private String content;
    private String image;

    public String getContent() {
        return content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
