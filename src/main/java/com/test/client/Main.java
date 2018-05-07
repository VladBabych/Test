package com.test.client;

import java.io.IOException;

public class Main {
    public static void main(String... arg) throws IOException {
        String url = "http://api.spending.gov.ua/api/v2/api/transactions/top100";
        String path = "test.json";
        Client client = new Client(url);
        client.fileSaver(path);
        client.amountConsole();

    }
}
