package com.test.client;

import feign.RequestLine;

import java.util.List;
import java.util.stream.Collectors;

public interface GovClient {

    class GovAmount {
        String amount;
    }

    @RequestLine("GET /")
    List<Gov> findAll();

    @RequestLine("GET /")
    List<GovAmount> findAllAmount();

    default List<String> str() {
        return findAllAmount().stream()
                .map(c -> c.amount)
                .distinct()
                .collect(Collectors.toList());
    }
}
