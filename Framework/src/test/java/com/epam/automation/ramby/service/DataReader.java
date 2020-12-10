package com.epam.automation.ramby.service;

import com.epam.automation.ramby.provider.LogProvider;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
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
        File file = new File(Objects.requireNonNull(fileResource).getFile());
        return new JsonReader(new FileReader(file));
    }
}
