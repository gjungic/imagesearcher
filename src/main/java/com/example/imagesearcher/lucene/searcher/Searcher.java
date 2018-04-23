package com.example.imagesearcher.lucene.searcher;

import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class Searcher {

    private IndexInputReader indexInputReader;

    public Searcher(String indexDirPath) throws IOException {
        this.indexInputReader = Components.getIndexInputReader(indexDirPath);
    }

    public List<LinkedHashMap<String, String>> find(String[] fields, String queryString, int limit) throws IOException, ParseException {
        return this.indexInputReader.query(fields, queryString, limit);
    }
}
