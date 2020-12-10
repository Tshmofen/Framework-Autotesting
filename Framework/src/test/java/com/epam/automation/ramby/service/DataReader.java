package com.epam.automation.ramby.service;

import com.epam.automation.ramby.provider.LogProvider;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class DataReader {
    private static final String PRODUCTS_LINKS_PATH = "test-data/products_links.json";
    private static final String CALCULATOR_DATA_PATH = "test-data/calculator_input.json";

    public static Object[][] getProductsLinks() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + PRODUCTS_LINKS_PATH);
        return new Gson().fromJson(getJsonResourceReader(PRODUCTS_LINKS_PATH), Object[][].class);
    }

    public static Object[][] getCalculatorInput() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + CALCULATOR_DATA_PATH);
        return new Gson().fromJson(getJsonResourceReader(CALCULATOR_DATA_PATH), Object[][].class);
    }

    private static JsonReader getJsonResourceReader(String jsonResourcePath) throws FileNotFoundException {
        URL fileResource  = DataReader.class.getClassLoader().getResource(jsonResourcePath);
        if (fileResource == null) throw new FileNotFoundException();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileResource.getFile()), StandardCharsets.UTF_8)
        );
        return new JsonReader(reader);
    }
}
