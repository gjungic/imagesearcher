package com.example.imagesearcher;

import com.example.imagesearcher.util.SearchEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImagesearcherApplication {

    public static void main(String[] args) {
        SearchEngine.createIndex();
        SpringApplication.run(ImagesearcherApplication.class, args);
    }
}
