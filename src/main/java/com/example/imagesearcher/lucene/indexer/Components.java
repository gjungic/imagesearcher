package com.example.imagesearcher.lucene.indexer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

class Components {

    static class CSV {
        static CSVParser getCsvParser(String inputFilePath) throws IOException {
            return CSVParser.parse(new File(inputFilePath), Charset.defaultCharset(), CSVFormat.RFC4180.withHeader());
        }
    }

    static class Lucene {

        static Document getEmptyDocument() {
            return new Document();
        }

        static IndexWriter getIndexWriter(String outputIndexDirPath) throws IOException {
            Directory outputDir = FSDirectory.open(Paths.get(outputIndexDirPath));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);

            return new IndexWriter(outputDir, config);
        }
    }

    static CSVDocumentWriter getOutputWriter(String outputDirPath) throws IOException {
        return new CSVDocumentWriter(outputDirPath);
    }

    static CSVReader getInputReader(String inputFilePath) throws IOException {
        return new CSVReader(inputFilePath);
    }
}
