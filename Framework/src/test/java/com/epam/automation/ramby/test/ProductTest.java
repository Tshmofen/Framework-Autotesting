package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.ProductPage;
import com.epam.automation.ramby.provider.TestDataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductTest extends CommonDriverTest {
    @DataProvider
    public Object[][] productPairLinksData() throws FileNotFoundException {
        return TestDataProvider.getPairProductsLinks();
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
