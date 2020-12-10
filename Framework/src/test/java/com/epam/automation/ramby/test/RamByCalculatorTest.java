package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.RamByVTBCalculatorPage;
import com.epam.automation.ramby.service.DataReader;
import com.epam.automation.ramby.listener.TestListener;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Listeners({TestListener.class})
public class RamByCalculatorTest extends CommonDriverTest {
    @DataProvider
    public Object[][] calculatorVTBData() throws FileNotFoundException {
        return DataReader.getCalculatorInput();
    }

    @Test(dataProvider = "calculatorVTBData")
    public void calculatorVTBPriceCorrectnessTest(String productPrice, String initialFee, String expectedPrice) {
        String finalPrice = new RamByVTBCalculatorPage(driver)
                .openPage()
                .sendKeysToVTBForm(productPrice, initialFee)
                .submitVTBCalculatorForm()
                .findFinalVTBCalculatorPrice();

        assertThat(finalPrice, is(equalTo(expectedPrice)));
    }
}
