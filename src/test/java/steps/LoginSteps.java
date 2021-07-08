package steps;

import io.qameta.allure.Step;
import pages.LoginPage;
import steps.base.BaseSteps;

public class LoginSteps extends BaseSteps {

    LoginPage loginPage;

    public LoginSteps() {
        loginPage = new LoginPage();
    }

    @Step("Logging in by user: {email}")
    public void login(String email, String password) {
        loginPage
                .open()
                .login(email, password)
                .isOpened();
    }

    @Step("Logging in by user: {email}")
    public LoginPage login(String email, String password, String errorMessage) {
        loginPage
                .open()
                .invalidLogin(email, password)
                .isErrorAppeared()
                .validateErrorMessage(errorMessage);
        return loginPage;
    }
}
