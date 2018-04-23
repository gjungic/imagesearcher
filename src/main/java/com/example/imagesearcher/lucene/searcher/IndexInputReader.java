package com.example.imagesearcher.lucene.searcher;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

class IndexInputReader {

    private final QueryParser queryParser;
    private final IndexSearcher searcher;
    private List<LinkedHashMap<String, String>> results = new ArrayList<>();


    IndexInputReader(String indexDirPath) throws IOException {
        this.queryParser = Components.getQueryParser();
        this.searcher = Components.getIndexSearcher(indexDirPath);

    }

    List<LinkedHashMap<String, String>> query(String[] fields, String queryString, int limit)
            throws IOException, ParseException {
        Query query = this.parseQuery(queryString);

        for (ScoreDoc result : this.findMatchingDocuments(query, limit)) {
            LinkedHashMap<String, String> document = new LinkedHashMap<>();
            for (String field : fields) {
                document.put(field, this.searcher.doc(result.doc).get(field));
            }
            results.add(document);
        }

        return results;
    }

    private ScoreDoc[] findMatchingDocuments(Query query, int maxHits) throws IOException {
        return this.searcher.search(query, maxHits, Sort.RELEVANCE).scoreDocs;
    }

    private Query parseQuery(String queryString) throws ParseException {
        return this.queryParser.parse(queryString);
    }
}
