package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.calculator.CalculatorECredit;
import com.epam.automation.ramby.page.calculator.CalculatorVTB;
import com.epam.automation.ramby.provider.TestDataProvider;
import org.testng.annotations.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CalculatorTest extends CommonDriverTest {

    @Test(dataProvider = "getCalculatorVTBInput", dataProviderClass = TestDataProvider.class)
    public void calculatorVTBPriceIsCorrectTest(String productPrice, String initialFee, String expectedPrice) {
        String finalPrice = new CalculatorVTB(driverProvider.getContextDriver())
                .openPage()
                .sendKeysToForm(productPrice, initialFee)
                .submitCalculatorForm()
                .findFinalCalculatorPrice();

        assertThat(finalPrice, is(equalTo(expectedPrice)));
    }

    @Test(dataProvider = "getCalculatorECreditInput", dataProviderClass = TestDataProvider.class)
    public void calculatorECreditPriceIsCorrectTest(String productPrice, String initialFee, String expectedPrice) {
        String finalPrice = new CalculatorECredit(driverProvider.getContextDriver())
                .openPage()
                .sendKeysToForm(productPrice, initialFee)
                .submitCalculatorForm()
                .findFinalCalculatorPrice();

        assertThat(finalPrice, is(equalTo(expectedPrice)));
    }

    @Test
    public void calculatorECreditCantEnterIncorrectFeePrice() {
        // incorrect data
        String productPrice = "250";
        String initialFee = "450";

        boolean warnPresence = new CalculatorECredit(driverProvider.getContextDriver())
                .openPage()
                .sendKeysToForm(productPrice, initialFee)
                .submitCalculatorForm()
                .getPresenceOfFeeWarning();

        assertThat(warnPresence, is(true));
    }
}
