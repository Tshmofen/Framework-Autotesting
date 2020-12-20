package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.ProductPage;
import com.epam.automation.ramby.provider.TestDataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductTest extends CommonDriverTest {

    @Test(dataProvider = "getPairProductsLinks", dataProviderClass = TestDataProvider.class)
    public void appearsOneProductInPreviousProductsTest(String firstProduct, String secondProduct) {
        int productLinksNumber = new ProductPage(driverProvider.getContextDriver())
                .openProductPage(firstProduct)
                .openProductPage(secondProduct)
                .getProductLinksInPreviousList(firstProduct)
                .size();

        assertThat(productLinksNumber, is(equalTo(1)));
    }
}
