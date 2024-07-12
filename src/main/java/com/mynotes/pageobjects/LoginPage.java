package com.mynotes.pageobjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.mynotes.client.UsersClient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import static com.mynotes.data.PagePaths.EXPAND_TESTING_BASE_UI_URL;
import static com.mynotes.data.PagePaths.EXPAND_TESTING_LOGIN_PAGE;
import static com.codeborne.selenide.Selenide.*;

@NoArgsConstructor
public class LoginPage extends BasePage{

    private static final String PAGE_URL = EXPAND_TESTING_BASE_UI_URL + EXPAND_TESTING_LOGIN_PAGE;

    private final SelenideElement emailInputField = $(By.cssSelector("input[data-testid=\"login-email\"]"));

    private final SelenideElement passwordInputField = $(By.cssSelector("input[data-testid=\"login-password\"]"));

    private final SelenideElement loginButton = $(By.cssSelector("button[data-testid=\"login-submit\"]"));

    @Getter
    private final UsersClient usersClient = new UsersClient();

    @Override
    public LoginPage openPage(){
        open(PAGE_URL);
        return this;
    }

    public MyNotesPage loginWithEmailAndPassword(String email, String password) {
        emailInputField.setValue(email);
        passwordInputField.setValue(password);
        loginButton.click();
        return new MyNotesPage();
    }

    public MyNotesPage loginWithToken(String token) {
        Selenide.executeJavaScript(String.format("localStorage.setItem('token', '%s');", token));
        return new MyNotesPage().openPage();
    }
}
