package ru.qapropeller.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ArticlesMenu {

    public void openArticle(String articleName) {
        SelenideElement articleBlock = $(By.xpath(
                String.format("//button[text()='%s']/ancestor-or-self::div[@class='tree-main-node']", articleName)));

        // open articles block if not
        if (!articleBlock.$("div.sub-tree").isDisplayed()) {
            articleBlock.$("button.tree-main-button").click();
        }
        articleBlock.$("button.tree-button").click();
    }
}
