package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.ProductPage;
import com.epam.automation.ramby.provider.TestDataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CartingTest extends CommonDriverTest {

    @Test(dataProvider = "getProductsLinks", dataProviderClass = TestDataProvider.class)
    public void onlyOneProductAddedToCartTest(String productPage) {
        int productLinksNumber = new ProductPage(driverProvider.getContextDriver())
                .openProductPage(productPage)
                .addProductToCart()
                .goToCartingPage()
                .getAllProductLinks(productPage)
                .size();

        assertThat(productLinksNumber, is(equalTo(1)));
    }

    @Test(dataProvider = "getPairProductsLinks", dataProviderClass = TestDataProvider.class)
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
