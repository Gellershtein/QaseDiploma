package elements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MirrorInput {
    String label;
    String locator = "//*[contains(text(), '%s')]/parent::div/descendant::p";
    String parent = "//*[contains(text(), '%s')]/following-sibling::input/preceding-sibling::div";

    public MirrorInput(String label) {
        this.label = label;
    }

    public void setFocus() {
        $x(String.format(parent, label)).click();
    }

    public void write(String text) {
        setFocus();
        $x(String.format(locator, label)).shouldBe(visible).sendKeys(text);
    }

    public MirrorInput clear() {
        setFocus();
        $x(String.format(locator, label)).shouldBe(visible).clear();
        return this;
    }

    public void shouldHave(String text) {
        $x(String.format(locator, label)).shouldHave(Condition.exactText(text));
    }
}
