package com.example.imagesearcher.lucene.indexer;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import static com.example.imagesearcher.util.Constants.ORIGINAL_SIZE;

public class CSVReader implements Iterator<Document> {

    private final CSVParser csvParser;
    private final Document document;

    private String[] csvFieldNames;
    private Field[] csvFields;

    private Iterator<CSVRecord> csvIterator;

    CSVReader(String inputCsvFilePath) throws IOException {
        this.csvParser = Components.CSV.getCsvParser(inputCsvFilePath);
        this.document = Components.Lucene.getEmptyDocument();
        this.csvIterator = csvParser.iterator();

        this.initCsvFieldNames();
        this.initDocumentFields();
    }

    private void initCsvFieldNames() {
        Object[] header = this.csvParser.getHeaderMap().keySet().toArray();
        this.csvFieldNames = Arrays.copyOf(header, header.length, String[].class);
        this.csvFields = new Field[this.csvFieldNames.length];
    }

    private void initDocumentFields() {
        for (int i = 0; i < this.csvFieldNames.length; i++) {
            this.csvFields[i] = new TextField(this.csvFieldNames[i], "", Field.Store.YES);
            this.document.add(this.csvFields[i]);
        }
    }

    @Override
    public boolean hasNext() {
        return this.csvIterator.hasNext();
    }

    @Override
    public Document next() {
        CSVRecord row = this.csvIterator.next();
        for (int i = 0; i < this.csvFields.length; i++) {
            if (csvFields[i].toString().equals(ORIGINAL_SIZE)) {
                this.csvFields[i].setIntValue(Integer.valueOf(row.get(i)));
            } else {
                this.csvFields[i].setStringValue(row.get(i));
            }
        }

        return this.document;
    }
}
