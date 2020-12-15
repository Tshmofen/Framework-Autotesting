package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.ProductPage;
import com.epam.automation.ramby.provider.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductTest extends CommonDriverTest {
    @org.testng.annotations.DataProvider
    public Object[][] productPairLinksData() throws FileNotFoundException {
        return DataProvider.getPairProductsLinks();
    }

    @Test(dataProvider = "productPairLinksData")
    public void correctAppearOfProductInPreviousProducts(String firstProduct, String secondProduct) {
        boolean isInPrevious = new ProductPage(driverProvider.getContextDriver())
                .openProductPage(firstProduct)
                .openProductPage(secondProduct)
                .isProductInPreviousProducts(firstProduct);

        assertThat(isInPrevious, is(true));
    }
}
