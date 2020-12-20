package com.epam.automation.ramby.provider;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TestDataProvider {
    private static final String PRODUCTS_LINKS_PATH = "test-data/products_links.json";
    private static final String PAIR_PRODUCTS_LINKS_PATH = "test-data/pair_products_links.json";
    private static final String CALCULATOR_VTB_PATH = "test-data/calculator_vtb_input.json";
    private static final String CALCULATOR_ECREDIT_PATH = "test-data/calculator_ecredit_input.json";
    private static final String SEARCH_KEYWORDS_PATH = "test-data/search_keywords.json";

    @DataProvider
    public static Object[][] getProductsLinks() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + PRODUCTS_LINKS_PATH);
        return getResourceObjects(PRODUCTS_LINKS_PATH);
    }

    @DataProvider
    public static Object[][] getCalculatorVTBInput() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + CALCULATOR_VTB_PATH);
        return getResourceObjects(CALCULATOR_VTB_PATH);
    }

    @DataProvider
    public static Object[][] getCalculatorECreditInput() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + CALCULATOR_ECREDIT_PATH);
        return getResourceObjects(CALCULATOR_ECREDIT_PATH);
    }

    @DataProvider
    public static Object[][] getPairProductsLinks() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + PAIR_PRODUCTS_LINKS_PATH);
        return getResourceObjects(PAIR_PRODUCTS_LINKS_PATH);
    }

    @DataProvider
    public static Object[][] getSearchKeywords() throws FileNotFoundException {
        LogProvider.getLog().info("Reading json: " + SEARCH_KEYWORDS_PATH);
        return getResourceObjects(SEARCH_KEYWORDS_PATH);
    }

    private static Object[][] getResourceObjects(String jsonResourcePath) throws FileNotFoundException {
        URL fileResource  = TestDataProvider.class.getClassLoader().getResource(jsonResourcePath);
        if (fileResource == null) throw new FileNotFoundException();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileResource.getFile()), StandardCharsets.UTF_8)
        );
        return new Gson().fromJson(new JsonReader(reader), Object[][].class);
    }
}
