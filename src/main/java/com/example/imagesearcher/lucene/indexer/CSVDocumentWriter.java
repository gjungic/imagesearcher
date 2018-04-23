package com.example.imagesearcher.lucene.indexer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;

class CSVDocumentWriter {

    private IndexWriter indexWriter;

    CSVDocumentWriter(String indexDirPath) throws IOException {
        this.indexWriter = Components.Lucene.getIndexWriter(indexDirPath);
    }

    void insert(Document doc) throws IOException {
        this.indexWriter.addDocument(doc);
    }

    void close() throws IOException {
        this.indexWriter.close();
    }
}
