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
    public void appearsOneProductInPreviousProductsTest(String firstProduct, String secondProduct) {
        int productLinksNumber = new ProductPage(driverProvider.getContextDriver())
                .openProductPage(firstProduct)
                .openProductPage(secondProduct)
                .getProductLinksInPreviousList(firstProduct)
                .size();

        assertThat(productLinksNumber, is(equalTo(1)));
    }
}
