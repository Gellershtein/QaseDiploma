package elements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class Button {
    String label;
    String locator = "//*[contains(@class, 'btn') and contains(text(),'%s')]";

    public Button(String label) {
        this.label = label;
    }

    public void click() {
        $x(String.format(locator, label)).shouldBe(Condition.enabled).click();
    }

    public void shouldBe(Condition condition) {
        $x(String.format(locator, label)).shouldBe(condition);
    }
}
