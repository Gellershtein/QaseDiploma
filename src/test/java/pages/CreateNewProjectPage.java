package pages;

import com.codeborne.selenide.Selenide;
import elements.Button;
import elements.Input;
import elements.RadioButton;
import elements.TextArea;
import lombok.extern.log4j.Log4j2;
import models.Project;
import pages.base.BasePage;

import static com.codeborne.selenide.Condition.visible;

@Log4j2
public class CreateNewProjectPage extends BasePage {

    @Override
    public CreateNewProjectPage isOpened() {
        new Button("Create project").shouldBe(visible);
        return this;
    }

    @Override
    public CreateNewProjectPage open() {
        Selenide.open("project/create");
        return new CreateNewProjectPage();
    }

    public RepositoryPage createNewProject(Project project) {
        new Input("Project name").write(project.getTitle());
        new Input("Project Code").clear().write(project.getCode());
        new TextArea("Description").write(project.getDescription());
        new RadioButton(project.getAccess()).setRadioButton();
        new Button("Create project").click();
        log.info(String.format("New Project: %s", project));
        return new RepositoryPage();
    }
}
