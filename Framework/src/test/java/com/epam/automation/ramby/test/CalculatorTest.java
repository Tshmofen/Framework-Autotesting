package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.calculator.CalculatorECredit;
import com.epam.automation.ramby.page.calculator.CalculatorVTB;
import com.epam.automation.ramby.provider.DataProvider;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CalculatorTest extends CommonDriverTest {
    @org.testng.annotations.DataProvider
    public Object[][] calculatorVTBInputData() throws FileNotFoundException {
        return DataProvider.getCalculatorVTBInput();
    }

    @org.testng.annotations.DataProvider
    public Object[][] calculatorECreditInputData() throws FileNotFoundException {
        return DataProvider.getCalculatorECreditInput();
    }

    @Test(dataProvider = "calculatorVTBInputData")
    public void calculatorVTBPriceCorrectnessTest(String productPrice, String initialFee, String expectedPrice) {
        String finalPrice = new CalculatorVTB(driverProvider.getContextDriver())
                .openPage()
                .sendKeysToForm(productPrice, initialFee)
                .submitCalculatorForm()
                .findFinalCalculatorPrice();

        assertThat(finalPrice, is(equalTo(expectedPrice)));
    }

    @Test(dataProvider = "calculatorECreditInputData")
    public void calculatorECreditPriceCorrectnessTest(String productPrice, String initialFee, String expectedPrice) {
        String finalPrice = new CalculatorECredit(driverProvider.getContextDriver())
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

        boolean warnPresence = new CalculatorECredit(driverProvider.getContextDriver())
                .openPage()
                .sendKeysToForm(productPrice, initialFee)
                .submitCalculatorForm()
                .getPresenceOfFeeWarning();

        assertThat(warnPresence, is(equalTo(true)));
    }
}
