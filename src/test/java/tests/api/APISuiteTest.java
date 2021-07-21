package tests.api;

import factories.ProjectFactory;
import factories.SuiteFactory;
import io.qameta.allure.Feature;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.Suite;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.ProjectsSteps;
import steps.SuiteSteps;

@Log4j2
@Feature("Test Suite")
public class APISuiteTest {
    ProjectsSteps projectsSteps = new ProjectsSteps();
    SuiteSteps suiteSteps = new SuiteSteps();
    Project newProject;
    Suite newSuite, updateSuite;

    @BeforeMethod(alwaysRun = true, description = "Create project before test")
    public void createNewProject() {
        ProjectFactory projectFactory = new ProjectFactory();
        newProject = projectFactory.getProject();
        SuiteFactory suiteFactory = new SuiteFactory();
        newSuite = suiteFactory.getSuite();
        updateSuite = suiteFactory.getSuite();

        projectsSteps
                .createNewProjectViaApi(newProject);
    }

    @Test(description = "Test Suite lifecycle (CRUD)")
    public void testSuiteShouldBeCreatedThenUpdatedAndDeletedViaApi() {
        suiteSteps
                .createNewSuiteViaApi(newProject, newSuite)
//                .getSuiteWithIdAndValidateItViaApi(newProject, newSuite, 1)
                .updateSuiteByIdViaApi(newProject, updateSuite, 1)
                .getAllSuitesUsingAPI(newProject)
                .deleteCaseByIdViaApi(newProject, 1);
    }

    @AfterMethod(description = "Delete project after test")
    public void deleteProject(ITestResult result) {
        projectsSteps
                .deleteProjectViaApi(newProject);
//        if (result.getStatus() == ITestResult.SUCCESS) {
//            projectsSteps
//                    .deleteProjectViaApi(newProject);
//        } else {
//            log.debug("Test was FAILED, project {} still alive for debugging", newProject.getTitle());
//        }
    }
}
