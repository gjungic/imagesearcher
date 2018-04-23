package com.example.imagesearcher.lucene.searcher;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

import static com.example.imagesearcher.util.Constants.TITLE;

class Components {

    static IndexInputReader getIndexInputReader(String indexDirPath) throws IOException {
        return new IndexInputReader(indexDirPath);
    }

    static QueryParser getQueryParser() {
        return new QueryParser(TITLE, new StandardAnalyzer());
    }

    static IndexSearcher getIndexSearcher(String indexDirPath) throws IOException {
        FSDirectory directory = FSDirectory.open(Paths.get(indexDirPath));
        DirectoryReader reader = DirectoryReader.open(directory);

        return new IndexSearcher(reader);
    }
}
