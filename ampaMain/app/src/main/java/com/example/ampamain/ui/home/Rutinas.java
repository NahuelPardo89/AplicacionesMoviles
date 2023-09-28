package com.example.ampamain.ui.home;

public class Rutinas {
    private String videoUrl;
    private String title;
    private String description;

    // Constructor
    public Rutinas(String videoUrl, String title, String description) {
        this.videoUrl = videoUrl;
        this.title = title;
        this.description = description;
    }

    // Getters
    public String getVideoUrl() {
        return videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
