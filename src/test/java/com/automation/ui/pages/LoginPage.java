package com.automation.ui.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

@DefaultUrl("https://www.saucedemo.com/")
public class LoginPage extends PageObject {

    @FindBy(id = "user-name")
    private WebElementFacade usernameField;

    @FindBy(id = "password")
    private WebElementFacade passwordField;

    @FindBy(id = "login-button")
    private WebElementFacade loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElementFacade errorMessage;

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.type(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.type(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isCurrentlyVisible();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
