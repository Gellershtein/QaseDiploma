package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Input {
    String label;
    String locator = "//*[contains(text(), '%s')]/following-sibling::input";

    public Input(String label) {
        this.label = label;
    }

    public Input write(String text) {
        if (!$x(String.format(locator, label)).shouldBe(visible).is(empty)) {
            clear();
        } else {
            log.info(String.format("Writing text '%s' into Input with label '%s'", text, label));
            $x(String.format(locator, label)).shouldBe(visible).setValue(text);
        }
        return this;
    }

    public Input click() {
        $x(String.format(locator, label)).shouldBe(visible).click();
        return this;
    }

    public Input clear() {
        SelenideElement element = $x(String.format(locator, label));
        element.click();
        element.clear();
        return this;
    }

    public void shouldHave(String text) {
        $x(String.format(locator, label)).shouldHave(Condition.attribute("value", text));
    }

    public void shoulBe(Condition condition) {
        $x(String.format(locator, label)).shouldHave(condition);
    }
}
