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
    private static final String USER = "test";
    private static final String PASS = "test";

    private final TestContext context;
    private LoginPage page;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @When("^пользователь авторизируется в системе$")
    public void login() {
        page = LoginPage.open();
        page.login(USER, PASS);
        Selenide.page(MainPage.class).checkIsOpen();
    }

    @When("^пользователь пытается авторизоваться в системе с некорректными (логином|паролем)$")
    public void tryIncorrectLogin(String type) {
        page = LoginPage.open();
        switch (type) {
            case "логином":
                page.login("incorrect_login", PASS);
                break;
            case "паролем":
                page.login(USER, "incorrect_pass");
                break;
        }
    }

    @When("^пользователь вводит данные авторизации$")
    public void enterCredentials() {
        page = LoginPage.open();
        page.enterCredentialsAndConfirm(USER, PASS);
    }

    @When("^не подтверждает авторизацию$")
    public void dismissLogin() {
        page.confirmLogin(false);
    }

    @Then("^пользователь авторизован$")
    public void checkLogin() {
        Selenide.page(MainPage.class).checkIsOpen();
    }

    @Then("^пользователь не авторизован$")
    public void checkDoNotLogin() {
        page.checkIsOpen();
    }
}
