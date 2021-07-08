package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private SelenideElement loginButton = $("#signin");

    public HomePage open() {
        Selenide.open();
        return this;
    }

    public LoginPage pressLoginButton() {
        loginButton.click();
        return new LoginPage();
    }
}
