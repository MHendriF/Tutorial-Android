package com.taimoorsikander.cityguideapp.HelperClasses.HomeAdapter;

import android.graphics.drawable.Drawable;

public class Categories {
    private int images;
    private String title;
    private Drawable gradient;

    public Categories(int images, String title, Drawable gradient) {
        this.images = images;
        this.title = title;
        this.gradient = gradient;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getGradient() {
        return gradient;
    }

    public void setGradient(Drawable gradient) {
        this.gradient = gradient;
    }

}
