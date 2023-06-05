package com.appco.requisicoescomretrofit.model;

public class Foto {
    private String url;
    private String albumId;
    private String title;
    private String thumbnaiUrl;
    private int id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnaiUrl() {
        return thumbnaiUrl;
    }

    public void setThumbnaiUrl(String thumbnaiUrl) {
        this.thumbnaiUrl = thumbnaiUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
