package com.sergimontanes.api.service.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.sergimontanes.api.documents.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticsearchService<T extends Document> {
    private final ElasticsearchClient client;
    public ElasticsearchService(ElasticsearchClient client) {
        this.client = client;
    }

    public void index(T property) throws Exception {
        IndexResponse indexResponse = client.index(i -> i
                .index(property.getIndexName())
                .id(property.getId())
                .document(property)
        );
        System.out.println("Document indexed "+ indexResponse.id());
    }

    public void delete(T property) throws Exception {
        client.delete(d -> d.index(property.getIndexName()).id(property.getId()));
    }

    public List<T> search(String index, String query, Class<T> documentClass) throws Exception {
        SearchRequest request = SearchRequest.of(s -> s
                .index(index)
                .query(q -> q
                        .queryString(qs -> qs.query(query))
                )
        );

        SearchResponse<T> response = client.search(request, documentClass);

        return response.hits().hits().stream()
                .map(Hit::source)
                .toList();
    }

    public void closeClient() {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
