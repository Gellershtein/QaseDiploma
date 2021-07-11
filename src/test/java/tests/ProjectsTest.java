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
        Project newProject = projectsFactory.getProject();
        Project updateProject = projectsFactory.getProject();

        loginSteps
                .login(USER, PASSWORD);

        projectsSteps
                .createNewProject(newProject)
                .validateProjectFields(newProject)
                .updateProject(newProject.getCode(), updateProject)
                .validateProjectFields(updateProject)
                .deleteProject(updateProject.getCode())
                .isProjectDeleted(updateProject.getTitle());
    }
}

