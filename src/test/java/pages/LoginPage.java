package pages;

import com.codeborne.selenide.Selenide;
import elements.Button;
import elements.Input;
import io.qameta.allure.Step;
import pages.base.BasePage;

import static com.codeborne.selenide.Condition.visible;

public class LoginPage extends BasePage {

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
}
