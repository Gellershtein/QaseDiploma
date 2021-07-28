package elements;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TextArea {
    String label;
    String locator = "//*[contains(text(), '%s')]/following-sibling::textarea";

    public TextArea(String label) {
        this.label = label;
    }

    public void write(String text) {
        log.info(String.format("Writing text '%s' into TextArea with label '%s'", text, label));
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
