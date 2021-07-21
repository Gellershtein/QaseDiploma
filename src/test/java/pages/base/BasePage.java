package pages.base;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import pages.ProjectSettingsPage;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage extends AbstractPage {

    public abstract BasePage open() throws Exception;
    public static final String leftMenuButton = "//li[@class='submenu-item']/following::a[contains(@title,'%s')]/i";

    public BasePage alertShouldBeHidden() {
        $(".flash-message").shouldNotBe(Condition.visible);
        return this;
    }
}
