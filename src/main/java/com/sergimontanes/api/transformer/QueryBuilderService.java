package com.sergimontanes.api.transformer;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.util.ObjectBuilder;

import java.util.Map;

public class QueryBuilderService {

    public static Query buildBoolQuery(String textField, String textValue, String filterField, String filterValue) {
        return QueryBuilders.bool(b -> mustMatch(textField, textValue, b)
                .filter(f -> f.term(t -> t.field(filterField).value(filterValue)))
        );
    }

    public static Query buildDynamicQuery(Map<String, String> fields) {
        return QueryBuilders.bool(b -> {
            for (Map.Entry<String, String> entry : fields.entrySet()) {
                mustMatch(entry.getKey(), entry.getValue(), b);
            }
            return b;
        });
    }

    private static BoolQuery.Builder mustMatch(String textField, String textValue, BoolQuery.Builder b) {
        return b.must(m -> match(textField, textValue, m));
    }

    private static ObjectBuilder<Query> match(String textField, String textValue, Query.Builder m) {
        return m.match(t -> t.field(textField).query(textValue));
    }
}
