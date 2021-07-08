package steps;

import io.qameta.allure.Step;
import pages.LoginPage;

public class LoginSteps {

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

}
