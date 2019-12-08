package ru.qapropeller;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class MainPage implements Page {

    @FindBy(id = "avatarContainer")
    SelenideElement avatarIcon;

    @Override
    public void checkIsOpen() {
        avatarIcon.shouldBe(Condition.visible);
    }
}
