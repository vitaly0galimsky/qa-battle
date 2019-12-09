package ru.qapropeller;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePage implements Page {

    @FindBy(css = "#dataCard .card-title")
    private SelenideElement name;

    @FindBy(css = "#dataCard div.card-body > p.card-text")
    private SelenideElement shortDescriptionField;

    @FindBy(css = "textarea")
    private SelenideElement fullDescriptionField;

    @FindBy(xpath = "//button[text()='Download info']")
    private SelenideElement fullDescriptionDownloadButton;

    @FindBy(css = "#dataCard img")
    private SelenideElement image;

    @FindBy(xpath = "//button[text()='Move to saved']")
    private SelenideElement saveButton;

    @FindBy(xpath = "//button[text()='Removed from saved']")
    private SelenideElement cancelSaveButton;

    @Override
    public void checkIsOpen() {
        name.shouldBe(Condition.visible);
        shortDescriptionField.shouldBe(Condition.visible);
        fullDescriptionField.shouldBe(Condition.visible);
        fullDescriptionDownloadButton.shouldBe(Condition.visible);
        image.shouldBe(Condition.visible);
        saveButton.shouldBe(Condition.visible);
        cancelSaveButton.shouldBe(Condition.visible);
    }
}
