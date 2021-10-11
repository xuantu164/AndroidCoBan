package com.example.appnhac.Model;

public class Tracks {
    private String title;
    private String artist;
    private int image;

    public Tracks(String title, String artist, int image) {
        this.title = title;
        this.artist = artist;
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getImage() {
        return image;
    }
}
