package com.bidfrail.android.ui.loginregister.introduction;

public class Intro {

    private int backgroundColor;
    private int image;
    private String title;
    private String description;

    public Intro(int backgroundColor, int image, String title, String description) {
        this.backgroundColor = backgroundColor;
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
