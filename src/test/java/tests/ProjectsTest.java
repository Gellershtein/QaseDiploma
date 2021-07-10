package tests;

import factories.ProjectFactory;
import io.qameta.allure.Feature;
import models.Project;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Feature("Projects")
public class ProjectsTest extends BaseTest {

    @Test(description = "Project lifecycle (CRUD)")
    public void projectShouldBeCreatedThenUpdatedAndDeleted() {
        ProjectFactory projectsFactory = new ProjectFactory();
        Project project = projectsFactory.getProject();
        Project updateProject = projectsFactory.getProject();

        loginSteps
                .login(USER, PASSWORD);

        projectsSteps
                .createNewProject(project)
                .validateProjectFields(project)
                .updateProject(project.getCode(), updateProject)
                .validateProjectFields(updateProject)
                .deleteProject(updateProject.getCode())
                .isProjectDeleted(updateProject.getTitle());
    }
}

