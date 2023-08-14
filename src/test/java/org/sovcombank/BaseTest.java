package org.sovcombank;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeAll
    public static void before() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://people.sovcombank.ru";
    }

    @Test
    public void vacationFilterCheck() {
        open("/");
        $(By.xpath("//a[contains(@href, '/vacancies')]")).click();
        $(By.cssSelector("#app > div > div > div.page-vacancies-container.app-container > div.page-vacancies > div.page-vacancies__sections.mb-lg-30.mb-16.page-block > div.section-vacancies-filter.px-6.d-none.d-sm-flex > div.find-city-selector.section-vacancies-filter__select.mb-4 > div.v-input.search-cities.theme--light.v-text-field.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-text-field--outlined.v-text-field--placeholder.v-select.v-autocomplete > div > div.v-input__slot > div.v-select__slot > div"))
                .click();
        $(By.xpath("//*[contains(text(), 'Москва') and contains(@class, 'subtext')]")).click();
        $(By.xpath("(//*[contains(@class, 'v-select__selections')])[1]")).click();
        $(By.xpath("//*[@class='v-list-item__title' and contains(text(), 'Совкомбанк Технологии')]")).click();
        sleep(10000);
        ElementsCollection results = $$(By.xpath("//*[contains(@class, 'vacancy-main')]"));
        System.out.println(results.size());
        results.forEach(result -> System.out.println(result.text()));
        results.forEach(result -> Assertions.assertThat(result.text()).contains("Совкомбанк Технологии"));
    }

}
