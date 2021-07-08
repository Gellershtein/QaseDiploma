package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static pages.LoginPage.INVALID_CREDENTIALS_ERROR_MESSAGE;

public class LoginTest extends BaseTest {

    @Test(description = "Successful Login into {Configuration.baseUrl}")
    public void successfulLogin() {
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
