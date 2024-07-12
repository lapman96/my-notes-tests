package com.mynotes.pageobjects;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.mynotes.data.PagePaths.*;

@NoArgsConstructor
public class WelcomePage extends BasePage {
    @Getter
    private static final String PAGE_URL = EXPAND_TESTING_BASE_UI_URL + EXPAND_TESTING_MY_NOTES_PAGE_URL;

    private final SelenideElement loginButton = $(By.cssSelector("a[href*=\"login\"]"));

    private final SelenideElement createAccountButton = $(By.cssSelector("a[data-testid=\"open-register-view\"]"));

    @Override
    public WelcomePage openPage() {
        open(PAGE_URL);
        return this;
    }

    public LoginPage openLoginPage(){
        loginButton.click();
        return new LoginPage();
    }
}
