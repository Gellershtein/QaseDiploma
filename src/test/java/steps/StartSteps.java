package steps;

import io.qameta.allure.Step;
import pages.HomePage;
import pages.LoginPage;
import steps.base.BaseSteps;

public class StartSteps extends BaseSteps {

    private HomePage homePage;

    public StartSteps() {
        homePage = new HomePage();
    }

    @Step("Open start page for QASE project")
    public StartSteps open() {

        homePage
                .open()
                .isOpened();
        return this;
    }

    @Step("Open login form")
    public LoginPage openLoginForm() {
        homePage
                .clickOnLoginButton();
        return new LoginPage();
    }
}
