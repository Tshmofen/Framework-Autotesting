package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.SearchPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchTest extends CommonDriverTest{
    private final static String SEARCH_WORD = "Блок питания";

    @Test
    public void correctSearchKeywordsTest() {
        String firstItemTitle = new SearchPage(driver)
                .openPage()
                .sendKeyToSearchForm(SEARCH_WORD)
                .submitSearchButton()
                .getFirstItemTitle();

        assertThat(firstItemTitle, containsString(SEARCH_WORD));
    }

    @Test
    public void correctSortingPriceAscendingTest() {
        boolean isPriceAscending = new SearchPage(driver)
                .openPage()
                .sendKeyToSearchForm(SEARCH_WORD)
                .submitSearchButton()
                .selectSortingByLowPrice()
                .isItemsPriceAscending();

        assertThat(isPriceAscending, is(true));
    }
}
