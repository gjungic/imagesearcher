package com.example.imagesearcher.models;

import lombok.Data;

@Data
public class SearchResult {
    private String title;
    private String originalUrl;

    public SearchResult(String title, String originalUrl) {
        this.title = title;
        this.originalUrl = originalUrl;
    }
}
