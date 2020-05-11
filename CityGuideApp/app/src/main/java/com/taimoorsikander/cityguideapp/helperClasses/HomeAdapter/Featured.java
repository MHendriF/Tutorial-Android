package com.taimoorsikander.cityguideapp.helperClasses.HomeAdapter;

public class Featured {
    private int images;
    private String title;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Featured(int images, String title, String description) {
        this.images = images;
        this.title = title;
        this.description = description;
    }
}
