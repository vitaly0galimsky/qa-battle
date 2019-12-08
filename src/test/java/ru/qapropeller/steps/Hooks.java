package ru.qapropeller.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.qapropeller.TestContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Hooks {
    private static final Logger LOG = LoggerFactory.getLogger(Hooks.class);

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before(order = 10)
    public static void setUpSelenide() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://172.29.16.170:8080";
        Configuration.browserSize = "1920x1080";
        // Много где специально стоят большие ожидания. Проще один раз задать
        Configuration.timeout = TimeUnit.SECONDS.toMillis(10);
    }

    @Before(order = 20)
    public static void openPage() {
        Selenide.open();
    }

    @After
    public void embedTestEnvironment(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File screenshot = Screenshots.takeScreenShotAsFile();
            scenario.embed(IOUtils.toByteArray(new FileInputStream(screenshot)), "image/png");

            String consoleLog = "\n\nЛоги браузера: " +
                    String.join("\n", Selenide.getWebDriverLogs(LogType.BROWSER, Level.WARNING));
            String testParametersLog = "\n\nПараметры тестов: " + context.toString();

            scenario.write(consoleLog + testParametersLog);
            LOG.error(consoleLog + testParametersLog);
        }
    }

    @After(order = 1)
    public void clearBrowser() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        WebDriverRunner.clearBrowserCache();
    }
}
