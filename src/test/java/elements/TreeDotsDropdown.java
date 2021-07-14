package elements;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TreeDotsDropdown {
    String label;
    String dropdownLocator = ".btn-dropdown";
    String optionLocator = "//a[@aria-expanded='true']//following::div[contains(@class,'dropdown-item')]/a[text()='%s']";

    public TreeDotsDropdown() {
    }

    public TreeDotsDropdown setDropdown(String optionName) {
        $(dropdownLocator).click();
        $x(String.format(optionLocator, optionName)).click();
        return this;
    }
}
