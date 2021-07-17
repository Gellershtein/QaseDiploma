package tests.api;

import adapters.ProjectAdapter;
import com.github.javafaker.Faker;
import factories.ProjectFactory;
import io.qameta.allure.Feature;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.api.ProjectResult;
import org.testng.annotations.Test;
import steps.ProjectsSteps;

import static org.testng.Assert.assertEquals;

@Log4j2
@Feature("Projects")
public class APIProjectTest {
    ProjectsSteps projectsSteps = new ProjectsSteps();
    ProjectAdapter projectAdapter = new ProjectAdapter();
    Faker faker = new Faker();

    @Test(enabled = false)
    public void getProjectByName() {
        Project expectedResult = Project.builder()
                .title("Genji")
                .code("AGAVESYRUP")
                .build();

        ProjectResult result = projectAdapter
                .get(expectedResult);
        log.info("Result " + result.getResult());

        assertEquals(result.getResult(), expectedResult);
    }

    @Test(enabled = false)
    public void getProjectByName2() {
        ProjectFactory projectsFactory = new ProjectFactory();
        Project newProject = Project.builder()
                .title("Genji")
                .code("AGAVESYRUP")
                .build();

        ProjectResult result = projectAdapter
                .get(newProject);
        log.info("Result " + result.getResult());

//        assertEquals(result.getResult(), ProjectResult.builder().result(newProject));
    }

    @Test(description = "Project lifecycle (CRUD)")
    public void projectShouldBeCreatedThenReadAndDeletedViaApi() {
        ProjectFactory projectsFactory = new ProjectFactory();
        Project newProject = projectsFactory.getProject();
        Project updateProject = projectsFactory.getProject();

        projectsSteps
                .createNewProjectViaApi(newProject)
                .getProjectViaApi(newProject)
                .deleteProjectViaApi(newProject);
//                .getLimitedProjectsViaApi(2);
    }
}
