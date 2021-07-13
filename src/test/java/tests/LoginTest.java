package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static pages.LoginPage.INVALID_CREDENTIALS_ERROR_MESSAGE;

@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(description = "Open login form for QASE project")
    public void loginFormShouldBeOpened() {
        startSteps
                .open()
                .openLoginForm()
                .isOpened();
    }

    @Test(description = "Successful Login into QASE project")
    public void userShouldSuccessfullyLoggedIn() {
        loginSteps
                .login(USER, PASSWORD);
    }

    @DataProvider(name = "invalid Email And Password Data Provider")
    public Object[][] getInvalidLoginData() {
        return new Object[][]{
                {USER, "Invalid password", INVALID_CREDENTIALS_ERROR_MESSAGE},
                {"invalidEmail@email.com", PASSWORD, INVALID_CREDENTIALS_ERROR_MESSAGE},
        };
    }

    @Test(description = "Verify that error is appeared on attempt to login with invalid credentials",
            dataProvider = "invalid Email And Password Data Provider")
    public void errorShouldBeAppearedOnAttemptLoginWithInvalidCredentials(String user, String password, String errorMessage) {
        loginSteps
                .login(user, password, errorMessage);
    }
}
