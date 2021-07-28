package elements;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TreeDotsDropdown {
    String label;
    String dropdownLocator = ".btn-dropdown";
    String optionLocator = "//a[@aria-expanded='true']//following::div[contains(@class,'dropdown-item')]/a[text()='%s']";

    public TreeDotsDropdown() {
    }

    public TreeDotsDropdown setDropdown(String optionName) {
        $(dropdownLocator).click();
        log.info(String.format("Ser option '%s' for TreeDotsDropdown", optionName));
        $x(String.format(optionLocator, optionName)).click();
        return this;
    }
}
