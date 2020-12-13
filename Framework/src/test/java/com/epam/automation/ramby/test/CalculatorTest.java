package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.calculator.CalculatorECredit;
import com.epam.automation.ramby.page.calculator.CalculatorVTB;
import com.epam.automation.ramby.service.DataReader;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CalculatorTest extends CommonDriverTest {
    @DataProvider
    public Object[][] calculatorVTBInputData() throws FileNotFoundException {
        return DataReader.getCalculatorVTBInput();
    }

    @DataProvider
    public Object[][] calculatorECreditInputData() throws FileNotFoundException {
        return DataReader.getCalculatorECreditInput();
    }

    @Test(dataProvider = "calculatorVTBInputData")
    public void calculatorVTBPriceCorrectnessTest(String productPrice, String initialFee, String expectedPrice) {
        String finalPrice = new CalculatorVTB(driver)
                .openPage()
                .sendKeysToForm(productPrice, initialFee)
                .submitCalculatorForm()
                .findFinalCalculatorPrice();

        assertThat(finalPrice, is(equalTo(expectedPrice)));
    }

    @Test(dataProvider = "calculatorECreditInputData")
    public void calculatorECreditPriceCorrectnessTest(String productPrice, String initialFee, String expectedPrice) {
        String finalPrice = new CalculatorECredit(driver)
                .openPage()
                .sendKeysToForm(productPrice, initialFee)
                .submitCalculatorForm()
                .findFinalCalculatorPrice();

        assertThat(finalPrice, is(equalTo(expectedPrice)));
    }

    @Test
    public void calculatorECreditCantEnterIncorrectPrice() {
        String productPrice = "250";
        String initialFee = "450";

        boolean warnPresence = new CalculatorECredit(driver)
                .openPage()
                .sendKeysToForm(productPrice, initialFee)
                .submitCalculatorForm()
                .getPresenceOfFeeWarning();

        assertThat(warnPresence, is(equalTo(true)));
    }
}
