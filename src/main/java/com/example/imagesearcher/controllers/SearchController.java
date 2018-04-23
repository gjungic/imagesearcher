package com.example.imagesearcher.controllers;

import com.example.imagesearcher.models.SearchResult;
import com.example.imagesearcher.util.SearchEngine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @RequestMapping()
    public ResponseEntity<List<SearchResult>> search(@RequestParam("query") final String query) {
        return ResponseEntity.ok(SearchEngine.find(query));
    }
}
