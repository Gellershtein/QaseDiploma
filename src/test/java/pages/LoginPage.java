package pages;

import com.codeborne.selenide.Selenide;
import elements.Button;
import elements.Input;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.base.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

public class LoginPage extends BasePage {

    public static final By ERROR_MESSAGE_LOCATOR = By.className("form-control-feedback");
    public static final String INVALID_CREDENTIALS_ERROR_MESSAGE = "These credentials do not match our records.";

    public LoginPage fillLoginForm(String email, String password) {

        new Input("Email").write(email);
        new Input("Password").write(password);
        new Button("Login").click();
        return this;
    }

    @Step("Filling login form")
    public ProjectsListPage login(String email, String password) {
        fillLoginForm(email, password);
        return new ProjectsListPage();
    }

    @Step("Filling login form")
    public LoginPage invalidLogin(String email, String password) {
        fillLoginForm(email, password);
        return this;
    }

    @Override
    public LoginPage isOpened() {
        new Button("Login").shouldBe(visible);
        return this;
    }

    @Step("Opening 'Login' page")
    @Override
    public LoginPage open() {
        Selenide.open("login");
        return this;
    }


    public LoginPage isErrorAppeared() {
        $(ERROR_MESSAGE_LOCATOR).shouldBe(visible);
        return this;
    }

    public LoginPage validateErrorMessage(String errorMessage) {
        assertEquals(getTextOfElement(ERROR_MESSAGE_LOCATOR), errorMessage, "Error does not match as expected");
        return this;
    }

}
