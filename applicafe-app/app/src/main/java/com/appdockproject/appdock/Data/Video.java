package com.appdockproject.appdock.Data;

import android.widget.ImageView;

/**
 * Created by jangerhard on 04-May-17.
 */

public class Video {
    private String name;
    private int imageResource;
    private int videoResource;
    public ImageView im;

    public Video(String name, int imageResource, int videoResource) {
        this.name = name;
        this.imageResource = imageResource;
        this.videoResource = videoResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getVideoResource() {
        return videoResource;
    }

}
