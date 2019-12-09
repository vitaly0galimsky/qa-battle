package ru.qapropeller;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.qapropeller.elements.ArticlesMenu;

public class MainPage implements Page {

    @FindBy(id = "avatarContainer")
    SelenideElement avatarIcon;

    @Override
    public void checkIsOpen() {
        avatarIcon.shouldBe(Condition.visible);
    }

    public ArticlesMenu getArticlesMenu() {
        return Selenide.page(ArticlesMenu.class);
    }
}
