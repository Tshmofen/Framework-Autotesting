package com.epam.automation.ramby.provider;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TestDataProvider {
    private static final String PRODUCTS_LINKS_PATH = "test-data/products_links.json";
    private static final String PAIR_PRODUCTS_LINKS_PATH = "test-data/pair_products_links.json";
    private static final String CALCULATOR_VTB_PATH = "test-data/calculator_vtb_input.json";
    private static final String CALCULATOR_ECREDIT_PATH = "test-data/calculator_ecredit_input.json";

    public static Object[][] getProductsLinks() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + PRODUCTS_LINKS_PATH);
        return new Gson().fromJson(getJsonResourceReader(PRODUCTS_LINKS_PATH), Object[][].class);
    }

    public static Object[][] getCalculatorVTBInput() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + CALCULATOR_VTB_PATH);
        return new Gson().fromJson(getJsonResourceReader(CALCULATOR_VTB_PATH), Object[][].class);
    }

    public static Object[][] getCalculatorECreditInput() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + CALCULATOR_ECREDIT_PATH);
        return new Gson().fromJson(getJsonResourceReader(CALCULATOR_ECREDIT_PATH), Object[][].class);
    }

    public static Object[][] getPairProductsLinks() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + PAIR_PRODUCTS_LINKS_PATH);
        return new Gson().fromJson(getJsonResourceReader(PAIR_PRODUCTS_LINKS_PATH), Object[][].class);
    }

    private static JsonReader getJsonResourceReader(String jsonResourcePath) throws FileNotFoundException {
        URL fileResource  = TestDataProvider.class.getClassLoader().getResource(jsonResourcePath);
        if (fileResource == null) throw new FileNotFoundException();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileResource.getFile()), StandardCharsets.UTF_8)
        );
        return new JsonReader(reader);
    }
}
