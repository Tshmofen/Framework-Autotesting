package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.RamByCartingPage;
import com.epam.automation.ramby.service.DataReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RamByCartingTest extends CommonDriverTest {
    @DataProvider
    public Object[][] productLinksData() throws FileNotFoundException {
        return DataReader.getProductsLinks();
    }

    @Test(dataProvider = "productLinksData")
    public void addProductToCartTest(String productPage) {
        boolean productPresence = new RamByCartingPage(driver, productPage)
                .openProductPage()
                .addProductToCart()
                .openCartPage()
                .isProductInCart();

        assertThat(productPresence, is(true));
    }
}
