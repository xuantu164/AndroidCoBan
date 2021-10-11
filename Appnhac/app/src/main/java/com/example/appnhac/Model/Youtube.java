package com.example.appnhac.Model;

public class Youtube {
    private String Title;
    private String Thumbnail;
    private String IdVideo;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public void setIdVideo(String idVideo) {
        IdVideo = idVideo;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public String getIdVideo() {
        return IdVideo;
    }

    public Youtube(String title, String thumbnail, String idVideo) {
        Title = title;
        Thumbnail = thumbnail;
        IdVideo = idVideo;
    }
}
