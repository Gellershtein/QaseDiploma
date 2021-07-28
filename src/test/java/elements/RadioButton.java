package elements;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class RadioButton {
    String option;
    String locator = "//*[contains(@type, 'radio') and contains(@value,'%s')]";

    public RadioButton(String option) {
        this.option = option;
    }

    public void setRadioButton() {
        log.info(String.format("Ser option '%s' for RadioButton", option));
        $x(String.format(locator, option)).click();
    }

    public void shouldBe(Condition selected) {
        $x(String.format(locator, option)).shouldBe(selected);
    }
}
