package ru.qapropeller.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.qapropeller.ArticlePage;
import ru.qapropeller.MainPage;
import ru.qapropeller.TestContext;

public class ArticleSteps {
    private final TestContext context;
    private ArticlePage articlePage = Selenide.page(ArticlePage.class);
    private MainPage mainPage = Selenide.page(MainPage.class);

    public ArticleSteps(TestContext context) {
        this.context = context;
    }

    @When("^пользователь открывает статью '(.+)'$")
    public void openArticle(String articleName) {
        mainPage.getArticlesMenu().openArticle(articleName);
    }

    @Then("^пользователь видит содержимое статьи$")
    public void checkArticle() {
        articlePage.checkIsOpen();
    }
}
