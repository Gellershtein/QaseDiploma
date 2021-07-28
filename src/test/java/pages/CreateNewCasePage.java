package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Dropdown;
import elements.Input;
import elements.MirrorInput;
import lombok.extern.log4j.Log4j2;
import models.Case;
import models.Project;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class CreateNewCasePage extends BasePage {
    private final SelenideElement createTestCaseLabel = $x("//*[contains(text(), 'Create test case')]");
    private final SelenideElement saveCaseButton = $("#save-case");
    private final SelenideElement addStepButton = $("#add-step");

    @Override
    public CreateNewCasePage isOpened() {
        new Input("Title").shoulBe(Condition.visible);
        return this;
    }

    public CreateNewCasePage open(Project project) {
        Selenide.open("case/" + project.getCode() + "/create");
        return this;
    }

    public RepositoryPage createNewCase(Case newCase) {
        fillCaseForm(newCase);
        saveCaseButton.click();
        return new RepositoryPage();
    }

    public RepositoryPage createNewCase(Project project, Case newCase) {
        open(project);
        createNewCase(newCase);
        return new RepositoryPage();
    }


    private void fillCaseForm(Case testCase) {
        new Input("Title").clear().write(testCase.getTitle());
        new MirrorInput("Description").clear().write(testCase.getDescription());
        new Dropdown("Status").setDropdown(testCase.getStatus());
        new Dropdown("Severity").setDropdown(testCase.getSeverity());
        new Dropdown("Priority").setDropdown(testCase.getPriority());
        new Dropdown("Type").setDropdown(testCase.getType());
        new MirrorInput("Pre-conditions").clear().write(testCase.getPreconditions());
        new Dropdown("Layer").setDropdown(testCase.getLayer());
        new Dropdown("Is Flaky").setDropdown(testCase.getIsFlaky());
        new Dropdown("Behavior").setDropdown(testCase.getBehavior());
        new MirrorInput("Post-conditions").clear().write(testCase.getPostconditions());
        new Dropdown("Automation status").setDropdown(testCase.getAutomationStatus());
        log.info(String.format("New test case: %s", testCase));
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
        new MirrorInput("Pre-conditions").shouldHave(theCase.getPreconditions());
//        new MirrorInput("Post-conditions").shouldHave(theCase.getPostconditions());
        new Button("Cancel").click();
        new Button("Close form").click();
        alertShouldBeHidden();
        return new RepositoryPage();
    }

    @Override
    public CreateNewCasePage open() throws Exception {
        return this;
    }
}
