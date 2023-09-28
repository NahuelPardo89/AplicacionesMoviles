package com.example.ampamain.ui.home;

public class Rutinas {
    private String videoUrl;

    private final String previewImageUrl;
    private String title;
    private String description;


    // Constructor
    public Rutinas(String videoUrl,String previewImageUrl, String title, String description) {
        this.videoUrl = videoUrl;
        this.previewImageUrl = previewImageUrl;
        this.title = title;
        this.description = description;
    }

    // Getters
    public String getVideoUrl() {
        return videoUrl;
    }

    public String getPreviewImageUrl() { return previewImageUrl; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
