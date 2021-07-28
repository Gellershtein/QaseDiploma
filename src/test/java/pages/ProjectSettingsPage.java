package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import elements.RadioButton;
import elements.TextArea;
import lombok.extern.log4j.Log4j2;
import models.Project;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class ProjectSettingsPage extends BasePage {
    private final SelenideElement settingsLabel = $x("//*[contains(@class, 'project-settings-tab')]/descendant::h1[text()='Settings']");
    private final SelenideElement deleteProjectButton = $x("//*[contains(@class,'text-start')]/*[contains(@class,'btn-cancel')]");

    @Override
    public ProjectSettingsPage isOpened() {
        settingsLabel.shouldBe(Condition.visible);
        return this;
    }

    public ProjectSettingsPage open(String projectCode) {
        Selenide.open("project/" + projectCode + "/settings/general");
        return this;
    }

    public ProjectSettingsPage editProject(Project project) {
        new Input("Project name").clear().write(project.getTitle());
        new Input("Project Code").clear().write(project.getCode());
        new TextArea("Description").clear().write(project.getDescription());
        new RadioButton(project.getAccess()).setRadioButton();
        new Button("Update settings").click();
        log.info(String.format("Update Project: %s", project));
        return this;
    }

    public void validateProjectSettings(Project project) {
        new Input("Project name").shouldHave(project.getTitle());
        new Input("Project Code").shouldHave(project.getCode());
        new TextArea("Description").shouldHave(project.getDescription());
        new RadioButton(project.getAccess()).shouldBe(Condition.selected);
    }

    public DeleteProjectConfirmationModal clickDeleteProjectButton() {
        deleteProjectButton.click();
        return new DeleteProjectConfirmationModal();
    }

    @Deprecated
    @Override
    public ProjectSettingsPage open() throws Exception {
        throw new Exception("You are using deprecated method");
    }
}
