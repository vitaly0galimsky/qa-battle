package ru.qapropeller;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(id = "loginInput")
    private SelenideElement userField;

    @FindBy(id = "passwordInput")
    private SelenideElement passField;

    @FindBy(css = ".card-footer button:last-of-type")
    private SelenideElement submitWaitButton;

    @FindBy(css = ".card-footer img")
    private SelenideElement submitButton;

    public static LoginPage open() {
        return Selenide.open("/", LoginPage.class);
    }

    public void login() {
        login("test", "test");
    }

    public void login(String user, String password) {
        userField.parent().hover().click();
        userField.setValue(user);

        passField.parent().hover().click();
        passField.setValue(password);

        submitWaitButton.hover();

        submitButton.click();

        Selenide.confirm("Are you sure you want to login?");
        Selenide.confirm("Really sure?");
    }
}
