package com.test.client;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Client {

    private String url;
    private GovClient govClient;
    Logger logger = LoggerFactory.getLogger(Client.class);

    public Client(String myURL) {
        this.url = myURL;
        govClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(GovClient.class, url);
    }


    private Object getAll() {
        return govClient.findAll();
    }

    public void fileSaver(String pathname) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().disableHtmlEscaping().create();

        FileWriter writer = new FileWriter(new File(pathname));
        try {
            writer.write(gson.toJson(getAll()));
            writer.flush();
        } catch (IOException e) {
            logger.info("IOException {}", e);
        } finally {
            writer.close();
        }
    }

    public void amountConsole() {
        List<String> amounts = govClient.str();
        Double l = 0.0;
        for (String amount : amounts) {
            Double num = Double.parseDouble(amount);
            l = l + num;
        }
        logger.info("Amount {}", l);
    }


}
