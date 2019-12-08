package ru.qapropeller;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage implements Page {
    private static final String CONFIRM_TEXT = "Are you sure you want to login?";
    private static final String AGAIN_CONFIRM_TEXT = "Really sure?";

    @FindBy(id = "loginInput")
    private SelenideElement userField;

    @FindBy(id = "passwordInput")
    private SelenideElement passField;

    @FindBy(css = ".card-footer button:last-of-type")
    private SelenideElement submitWaitButton;

    @FindBy(css = ".card-footer img")
    private SelenideElement submitButton;

    @FindBy(id = "loader")
    private SelenideElement loaderImage;

    public static LoginPage open() {
        return Selenide.open("/", LoginPage.class);
    }

    public void login(String user, String password) {
        enterCredentialsAndConfirm(user, password);
        confirmLogin(true);
        confirmSecondModal(true);
    }

    public void enterCredentialsAndConfirm(String user, String password) {
        userField.parent().hover().click();
        userField.setValue(user);

        passField.parent().hover().click();
        passField.setValue(password);

        submitWaitButton.hover();

        submitButton.click();
    }

    public void confirmLogin(boolean confirm) {
        if (confirm) {
            Selenide.confirm(CONFIRM_TEXT);
        } else {
            Selenide.dismiss(CONFIRM_TEXT);
        }
    }

    public void confirmSecondModal(boolean confirm) {
        loaderImage.shouldBe(Condition.visible);
        if (confirm) {
            Selenide.confirm(AGAIN_CONFIRM_TEXT);
        } else {
            Selenide.dismiss(AGAIN_CONFIRM_TEXT);
        }
    }

    @Override
    public void checkIsOpen() {
        userField.shouldBe(Condition.visible);
    }
}
