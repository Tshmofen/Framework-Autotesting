package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.ProductPage;
import com.epam.automation.ramby.service.DataReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductTest extends CommonDriverTest {
    @DataProvider
    public Object[][] productPairLinksData() throws FileNotFoundException {
        return DataReader.getPairProductsLinks();
    }

    @Test(dataProvider = "productPairLinksData")
    public void correctAppearOfProductInPreviousProducts(String firstProduct, String secondProduct) {
        long threadId = Thread.currentThread().getId();

        boolean isInPrevious = new ProductPage(drivers.get(threadId))
                .openProductPage(firstProduct)
                .openProductPage(secondProduct)
                .isProductInPreviousProducts(firstProduct);

        assertThat(isInPrevious, is(true));
    }
}
