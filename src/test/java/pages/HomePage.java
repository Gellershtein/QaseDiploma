package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.base.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static tests.base.BaseTest.HOME_URL;

public class HomePage extends BasePage {

    private SelenideElement loginButton = $("#signin");

    public HomePage open() {
        Selenide.open(HOME_URL);
        return this;
    }

    public LoginPage clickOnLoginButton() {
        loginButton.click();
        return new LoginPage();
    }

    @Override
    public HomePage isOpened() {
        loginButton.shouldBe(visible);
        return this;
    }
}
