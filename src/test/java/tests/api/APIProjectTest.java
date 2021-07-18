package tests.api;

import factories.ProjectFactory;
import io.qameta.allure.Feature;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.testng.annotations.Test;
import steps.ProjectsSteps;

@Log4j2
@Feature("Projects")
public class APIProjectTest {
    ProjectsSteps projectsSteps = new ProjectsSteps();

    @Test(description = "Project lifecycle (CRUD)")
    public void projectShouldBeCreatedThenReadAndDeletedViaApi() {
        ProjectFactory projectsFactory = new ProjectFactory();
        Project newProject = projectsFactory.getProject();

        projectsSteps
                .createNewProjectViaApi(newProject)
                .getProjectViaApi(newProject)
                .deleteProjectViaApi(newProject);
//                .getLimitedProjectsViaApi(2);
    }
}
