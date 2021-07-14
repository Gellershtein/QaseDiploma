package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Dropdown;
import elements.Input;
import elements.MirrorInput;
import models.Case;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreateNewCasePage extends BasePage {
    private final SelenideElement createTestCaseLabel = $x("//*[contains(text(), 'Create test case')]");
    private final SelenideElement saveCaseButton = $("#save-case");
    private final SelenideElement addStepButton = $("#add-step");

    @Override
    public CreateNewCasePage isOpened() {
        createTestCaseLabel.shouldBe(Condition.visible);
        return this;
    }

    public CreateNewCasePage open(String projectCode) {
        Selenide.open("case/" + projectCode + "/create");
        return this;
    }

    public RepositoryPage createNewCase(Case newCase) {
        fillCaseForm(newCase);
        saveCaseButton.click();
        return new RepositoryPage();
    }

    private void fillCaseForm(Case theCase) {
        new Input("Title").clear().write(theCase.getTitle());
        new MirrorInput("Description").clear().write(theCase.getDescription());
        new Dropdown("Status").setDropdown(theCase.getStatus());
        new Dropdown("Severity").setDropdown(theCase.getSeverity());
        new Dropdown("Priority").setDropdown(theCase.getPriority());
        new Dropdown("Type").setDropdown(theCase.getType());
        new Dropdown("Layer").setDropdown(theCase.getLayer());
        new Dropdown("Is Flaky").setDropdown(theCase.getIsFlaky());
        new Dropdown("Behavior").setDropdown(theCase.getBehavior());
        new MirrorInput("Pre-conditions").clear().write(theCase.getPreConditions());
        new MirrorInput("Post-conditions").clear().write(theCase.getPostConditions());
        new Dropdown("Automation status").setDropdown(theCase.getAutomationStatus());
    }

    public RepositoryPage updateCase(Case theCase) {
        return createNewCase(theCase);
    }

    public RepositoryPage validateCaseFields(Case theCase) {
        new Input("Title").shouldHave(theCase.getTitle());
        new MirrorInput("Description").shouldHave(theCase.getDescription());
        new Dropdown("Status").shouldHave(theCase.getStatus());
        new Dropdown("Severity").shouldHave(theCase.getSeverity());
        new Dropdown("Priority").shouldHave(theCase.getPriority());
        new Dropdown("Type").shouldHave(theCase.getType());
        new Dropdown("Layer").shouldHave(theCase.getLayer());
        new Dropdown("Is Flaky").shouldHave(theCase.getIsFlaky());
        new Dropdown("Behavior").shouldHave(theCase.getBehavior());
        new Dropdown("Automation status").shouldHave(theCase.getAutomationStatus());
        new MirrorInput("Pre-conditions").shouldHave(theCase.getPreConditions());
        new MirrorInput("Post-conditions").shouldHave(theCase.getPostConditions());
        new Button("Cancel").click();
        new Button("Close form").click();
        return new RepositoryPage();
    }

    @Override
    public CreateNewCasePage open() throws Exception {
        return this;
    }
}
