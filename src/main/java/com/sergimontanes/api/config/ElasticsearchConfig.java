package com.sergimontanes.api.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

//@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.server-url}")
    private String serverUrl;

    @Value("${elasticsearch.api-key}")
    private String apiKey;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @DependsOn({"objectMapper"})
    public ElasticsearchClient elasticsearchClient(ObjectMapper objectMapper) {
        RestClient restClient = RestClient
                .builder(HttpHost.create(serverUrl))
                .setDefaultHeaders(new BasicHeader[] {
                        new BasicHeader("Authorization", "ApiKey " + apiKey)
                })
                .build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper(objectMapper));

        return new ElasticsearchClient(transport);
    }
}
