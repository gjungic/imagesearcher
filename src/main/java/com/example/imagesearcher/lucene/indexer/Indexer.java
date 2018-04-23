package com.example.imagesearcher.lucene.indexer;

import java.io.IOException;

public class Indexer {

    private final CSVReader input;
    private final CSVDocumentWriter output;

    public Indexer(String inputFilePath, String outputDirPath) throws IOException {
        this.input  = Components.getInputReader(inputFilePath);
        this.output = Components.getOutputWriter(outputDirPath);
    }

    public void insert() throws IOException {
        while (this.input.hasNext()) {
            this.output.insert(this.input.next());
        }
        this.output.close();
    }
}
