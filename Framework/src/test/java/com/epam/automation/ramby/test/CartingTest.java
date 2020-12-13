package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.CartingPage;
import com.epam.automation.ramby.service.DataReader;
import com.epam.automation.ramby.listener.TestListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Listeners({TestListener.class})
public class CartingTest extends CommonDriverTest {
    @DataProvider
    public Object[][] productLinksData() throws FileNotFoundException {
        return DataReader.getProductsLinks();
    }

    @Test(dataProvider = "productLinksData")
    public void addProductToCartTest(String productPage) {
        boolean productPresence = new CartingPage(driver)
                .openProductPage(productPage)
                .addProductToCart()
                .openCartPage()
                .isProductInCart();

        assertThat(productPresence, is(true));
    }
}
