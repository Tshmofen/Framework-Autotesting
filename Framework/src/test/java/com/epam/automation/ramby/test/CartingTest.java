package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.ProductPage;
import com.epam.automation.ramby.provider.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CartingTest extends CommonDriverTest {
    @org.testng.annotations.DataProvider
    public Object[][] productLinksData() throws FileNotFoundException {
        return DataProvider.getProductsLinks();
    }

    @Test(dataProvider = "productLinksData")
    public void addProductToCartTest(String productPage) {
        boolean productPresence = new ProductPage(driverProvider.getContextDriver())
                .openProductPage(productPage)
                .addProductToCart()
                .openCartPage()
                .isProductInCart(productPage);

        assertThat(productPresence, is(true));
    }
}
