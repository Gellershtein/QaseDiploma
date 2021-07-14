package elements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class Dropdown {
    String label;
    String dropdownLocator = "//*[text()='%s']/parent::div/div[contains(@class, 'container')]";
    String optionLocator = "//*[contains(@id, 'react-select') and contains(text(),'%s')]";
    String selectedOption = "//*[text()='%s']/parent::div/div[contains(@class, 'container')]/descendant::div[contains(text(),'%s')]";

    public Dropdown(String label) {
        this.label = label;
    }

    public Dropdown setDropdown(String optionName) {
        $x(String.format(dropdownLocator, label)).click();
        $x(String.format(optionLocator, optionName)).click();
        return this;
    }

    public void shouldHave(String text) {
        $x(String.format(selectedOption, label, text)).shouldHave(Condition.exactText(text));
    }
}
