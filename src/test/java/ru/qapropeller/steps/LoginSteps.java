package ru.qapropeller.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.qapropeller.LoginPage;
import ru.qapropeller.MainPage;
import ru.qapropeller.TestContext;

public class LoginSteps {
    private static final Logger LOG = LoggerFactory.getLogger(LoginSteps.class);

    private final TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @When("пользователь авторизируется в системе")
    public void login() {
        LoginPage.open().login();
    }

    @Then("пользователь авторизован")
    public void checkLogin() {
        Selenide.page(MainPage.class).checkIsOpen();
    }
}
