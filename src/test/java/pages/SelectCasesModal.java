package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import pages.base.BaseModal;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class SelectCasesModal extends BaseModal {

    private final SelenideElement doneSelectCasesButton = $("#select-cases-done-button");
    private final SelenideElement selectTestCasesCheckbox = $(".custom-checkbox");
    private final SelenideElement statusCheckbox = $("[type='checkbox']");
    private final String searchInput = "//*[contains(@class,'filters-block')]/descendant::div[contains(@class, 'case-search')]";

    @Override
    public SelectCasesModal isOpened() {
        doneSelectCasesButton.shouldBe(visible);
        return this;
    }

    public CreateNewTestPlanPage addRandomTestCase() {
        if (!statusCheckbox.isSelected()) {
            selectTestCasesCheckbox.click();
        }
        doneSelectCasesButton.click();
        return new CreateNewTestPlanPage();
    }

    public CreateNewTestPlanPage addSearchedTestCase(String caseName) {
        searchTestCase(caseName);
        doneSelectCasesButton.click();
        return new CreateNewTestPlanPage();
    }

    public SelectCasesModal searchTestCase(String caseName) {
        log.info(String.format("Searching Test Case: %s ", caseName));
        $x(searchInput).setValue(caseName);
        return this;
    }

    private void selectRandomTestCase() {
        executeJavaScript("arguments[0].click();", selectTestCasesCheckbox);
    }
}


