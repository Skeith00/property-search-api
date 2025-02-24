package com.sergimontanes.api.documents;

public interface Document {
    String getIndexName(); // Elasticsearch index name
    String getId();        // Document ID
}
