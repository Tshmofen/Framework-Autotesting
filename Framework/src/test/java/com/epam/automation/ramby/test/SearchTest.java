package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.SearchPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchTest extends CommonDriverTest{
    @Test
    public void correctSearchKeywordsTest() {
        String keyword = "Блок питания";

        String firstItemTitle = new SearchPage(driver)
                .openPage()
                .sendKeyToSearchForm(keyword)
                .submitSearchButton()
                .getFirstItemTitle();

        assertThat(firstItemTitle, containsString(keyword));
    }
}
