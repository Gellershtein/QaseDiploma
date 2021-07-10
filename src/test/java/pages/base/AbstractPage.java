package pages.base;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public abstract class AbstractPage {

    public abstract AbstractPage isOpened();

    public String getTextOfElement(By locator) {
        log.debug(String.format("Getting text for element with locator '%s'", locator));
        String text = $(locator).shouldBe(visible).getText();
        log.debug(String.format("Text of element %s", text));
        return text;
    }
}
