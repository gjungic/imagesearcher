package com.example.imagesearcher.util;

import com.example.imagesearcher.lucene.indexer.Indexer;
import com.example.imagesearcher.lucene.searcher.Searcher;
import com.example.imagesearcher.models.SearchResult;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.imagesearcher.util.Constants.*;


public class SearchEngine {

    private static final String[] SEARCH_FIELDS = {TITLE, THUMBNAIL_URL};

    public static void createIndex() {
        try {
            File indexDir = Paths.get(INDEX_DIR_PATH).toFile();
            if (indexDir.exists() && indexDir.isDirectory() && indexDir.list().length == 0) {
                Long startTime = System.currentTimeMillis();
                File inputFilesDir = new File(SOURCE_PATH);
                for (String inputFilePath : inputFilesDir.list()) {
                    System.out.println("Creating index " + INDEX_DIR_PATH + " from " + inputFilePath);
                    Indexer index = new Indexer(SOURCE_PATH + inputFilePath, INDEX_DIR_PATH);
                    index.insert();
                }
                System.out.format("Indexing finished in %.2f seconds.\n",
                        (float) (System.currentTimeMillis() - startTime) / 1000);
            }
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public static List<SearchResult> find(String queryString) {
        try {
            List<SearchResult> searchResults = new ArrayList<>();
            Searcher searcher = new Searcher(INDEX_DIR_PATH);
            List<LinkedHashMap<String, String>> result = searcher.find(SEARCH_FIELDS, queryString, SEARCH_LIMIT);
            for (LinkedHashMap<String, String> row : result) {
                searchResults.add(new SearchResult(row.get(TITLE), row.get(THUMBNAIL_URL)));
            }

            return searchResults;
        } catch (IOException error) {
            System.out.println(error.getMessage());
        } catch (ParseException error) {
            System.out.println(error.getMessage());
        }

        return new ArrayList<>();
    }
}
