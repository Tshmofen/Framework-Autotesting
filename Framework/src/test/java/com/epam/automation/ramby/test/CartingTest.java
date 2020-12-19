package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.ProductPage;
import com.epam.automation.ramby.provider.TestDataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CartingTest extends CommonDriverTest {
    @DataProvider
    public Object[][] productLinksData() throws FileNotFoundException {
        return TestDataProvider.getProductsLinks();
    }

    @DataProvider
    public Object[][] productPairLinksData() throws FileNotFoundException {
        return TestDataProvider.getPairProductsLinks();
    }

    @Test(dataProvider = "productLinksData")
    public void onlyOneProductAddedToCartTest(String productPage) {
        int productLinksNumber = new ProductPage(driverProvider.getContextDriver())
                .openProductPage(productPage)
                .addProductToCart()
                .goToCartingPage()
                .getAllProductLinks(productPage)
                .size();

        assertThat(productLinksNumber, is(equalTo(1)));
    }

    @Test(dataProvider = "productPairLinksData")
    public void cartCounterProperIncreaseTest(String firstProductPage, String secondProductPage) {
        int counterValue = new ProductPage(driverProvider.getContextDriver())
                .openProductPage(firstProductPage)
                .addProductToCart()
                .openProductPage(secondProductPage)
                .addProductToCart()
                .goToCartingPage()
                .getProductsCounterValue();

        assertThat(counterValue, is(equalTo(2)));
    }
}
