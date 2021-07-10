package elements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TextArea {
    String label;
    String locator = "//*[contains(text(), '%s')]/following-sibling::textarea";

    public TextArea(String label) {
        this.label = label;
    }

    public void write(String text) {
        $x(String.format(locator, label)).sendKeys(text);
    }

    public TextArea clear() {
        $x(String.format(locator, label)).shouldBe(visible).clear();
        return this;
    }

    public void shouldHave(String text) {
        $x(String.format(locator, label)).shouldHave(Condition.attribute("value", text));
    }
}
