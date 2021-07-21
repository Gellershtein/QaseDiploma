package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import elements.Button;
import models.Project;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectsListPage extends BasePage {

    private final String projectNameLabel = "//table[contains(@class,'table')]//*[contains(text(), '%s')]";
    private final String threeDotsButton = "//*[contains(text(),'%s')]/parent::div/parent::td/following-sibling::td[@class='text-end']/descendant::a[contains(@class, 'btn')]";
    private final String deleteDropdownOption = "//*[contains(text(),'%s')]/parent::div/parent::td/following-sibling::td[@class='text-end']/descendant::div[contains(@class, 'dropdown-menu')]/descendant::a[contains(text(), 'Delete')]";
    private final String settingsDropdownOption = "//*[contains(text(),'%s')]/parent::div/parent::td/following-sibling::td[@class='text-end']/descendant::div[contains(@class, 'dropdown-menu')]/descendant::a[contains(text(), 'Settings')]";

    @Override
    public ProjectsListPage isOpened() {
        new Button("Create new project").shouldBe(Condition.visible);
        return this;
    }

    @Override
    public ProjectsListPage open() {
        Selenide.open("projects");
        return this;
    }

    public ProjectsListPage open(String code) {
        Selenide.open("project/" + code);
        return this;
    }

    public CreateNewProjectPage clickCreateNewProjectButton() {
        new Button("Create new project").click();
        return new CreateNewProjectPage();
    }

    public ProjectsListPage isProjectExist(Project project) {
        $x(String.format(projectNameLabel, project.getTitle())).shouldBe(Condition.visible);
        return this;
    }

    public ProjectsListPage isProjectNotExist(Project project) {
        $x(String.format(projectNameLabel, project.getTitle())).shouldNotBe(Condition.visible);
        return this;
    }

    public DeleteProjectConfirmationModal findProjectAndPressDeleteButton(String projectName) {
        findProjectAndPressThreeDotsButton(projectName);
        $x(String.format(deleteDropdownOption, projectName)).click();
        return new DeleteProjectConfirmationModal();
    }

    public RepositoryPage openProject(String projectName) {
        $x(String.format(projectNameLabel, projectName)).click();
        return new RepositoryPage();
    }

    public void findProjectAndPressSettingsButton(String projectName) {
        findProjectAndPressThreeDotsButton(projectName);
        $x(String.format(deleteDropdownOption, projectName)).click();

    }

    public ProjectsListPage findProjectAndPressThreeDotsButton(String projectName) {
        $x(String.format(threeDotsButton, projectName)).click();
        return this;
    }

    public ProjectsListPage isProjectDeleted(String projectName) {
        $x(String.format(projectNameLabel, projectName)).shouldNotBe(Condition.visible);
        return this;
    }
}