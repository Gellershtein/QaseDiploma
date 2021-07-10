package steps;

import io.qameta.allure.Step;
import models.Project;
import pages.ProjectSettingsPage;
import pages.ProjectsListPage;

public class ProjectsSteps {
    ProjectsListPage projectsListPage;
    ProjectSettingsPage projectSettingsPage;

    public ProjectsSteps() {
        projectsListPage = new ProjectsListPage();
        projectSettingsPage = new ProjectSettingsPage();
    }

    @Step("Create project '{project.title}' with code '{project.code}'")
    public ProjectsSteps createNewProject(Project project) {
        projectsListPage
                .open()
                .pressCreateNewProjectButton()
                .createNewProject(project);
        return this;
    }

    @Step("Validating fields of project: {project.code}")
    public ProjectsSteps validateProjectFields(Project project) {
        projectSettingsPage
                .open(project.getCode())
                .validateProjectSettings(project);
        return this;
    }

    @Step("Updating project '{existedProject}' with new fields")
    public ProjectsSteps updateProject(String existedProject, Project updateProject) {
        projectSettingsPage
                .open(existedProject)
                .editProject(updateProject);
        return this;
    }

    @Step("Deleting project: '{projectCode}'")
    public ProjectsSteps deleteProject(String projectCode) {
        projectSettingsPage
                .open(projectCode)
                .clickDeleteProjectButton()
                .confirmDeleting();
        return this;
    }

    @Step("Validating is project '{projectName}' deleted")
    public ProjectsSteps isProjectDeleted(String projectName) {
        projectsListPage
                .isProjectDeleted(projectName);
        return this;
    }
}
