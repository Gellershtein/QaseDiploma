package tests;

import factories.ProjectFactory;
import io.qameta.allure.Feature;
import models.Project;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Feature("Projects")
public class ProjectsTest extends BaseTest {

    @BeforeTest(alwaysRun = true, description = "Login and create project before test")
    public void login() {
        loginSteps
                .login(USER, PASSWORD);
    }

    @Test(description = "Project lifecycle (CRUD)")
    public void projectShouldBeCreatedThenUpdatedAndDeleted() {
        ProjectFactory projectsFactory = new ProjectFactory();
        Project newProject = projectsFactory.getProject();
        Project updateProject = projectsFactory.getProject();

        projectsSteps
                .createNewProject(newProject)
                .validateProjectFields(newProject)
                .updateProject(newProject.getCode(), updateProject)
                .validateProjectFields(updateProject)
                .deleteProject(updateProject.getCode())
                .isProjectDeleted(updateProject.getTitle());
    }
}

