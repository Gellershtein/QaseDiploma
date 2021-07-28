package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
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
        log.info(String.format("Writing text '%s' into Input with label '%s'", text, label));
        $x(String.format(locator, label)).shouldBe(visible).setValue(text);
    }


    public MirrorInput clear() {
        SelenideElement element = $x(String.format(locator, label));
        element.click();
        element.shouldBe(visible).clear();
        return this;
    }

    public void shouldHave(String text) {
        $x(String.format(locator, label)).shouldHave(Condition.exactText(text));
    }
}
