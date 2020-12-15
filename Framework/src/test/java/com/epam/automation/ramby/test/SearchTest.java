package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.SearchPage;
import com.epam.automation.ramby.provider.TestDataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchTest extends CommonDriverTest{
    @DataProvider
    public Object[][] searchKeywordsData() throws FileNotFoundException {
        return TestDataProvider.getSearchKeywords();
    }

    @Test(dataProvider = "searchKeywordsData")
    public void correctSearchKeywordsTest(String searchQuery) {
        String firstItemTitle = new SearchPage(driverProvider.getContextDriver())
                .openPage()
                .sendKeyToSearchForm(searchQuery)
                .submitSearchButton()
                .getFirstItemTitle();

        assertThat(firstItemTitle, containsString(searchQuery));
    }

    @Test(dataProvider = "searchKeywordsData")
    public void correctSortingPriceAscendingTest(String searchQuery) {
        boolean isPriceAscending = new SearchPage(driverProvider.getContextDriver())
                .openPage()
                .sendKeyToSearchForm(searchQuery)
                .submitSearchButton()
                .selectSortingByLowPrice()
                .isItemsPriceAscending();

        assertThat(isPriceAscending, is(true));
    }
}
