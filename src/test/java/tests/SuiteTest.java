package tests;

import factories.ProjectFactory;
import factories.SuiteFactory;
import io.qameta.allure.Feature;
import models.Project;
import models.Suite;
import org.testng.ITestResult;
import org.testng.annotations.*;
import tests.base.BaseTest;

@Feature("Test Suite")
public class SuiteTest extends BaseTest {
    Project newProject;

    @BeforeTest(alwaysRun = true, description = "Login and create project before test")
    public void loginAndCreateNewProject() {
        ProjectFactory projectFactory = new ProjectFactory();
        newProject = projectFactory.getProject();

        loginSteps
                .login(USER, PASSWORD);
        projectsSteps
                .createNewProject(newProject);
    }

    @Test(description = "Test suite lifecycle (CRUD)")
    public void testSuiteShouldBeCreatedThenUpdatedAndDeleted() {
        SuiteFactory suiteFactory = new SuiteFactory();

        Suite newSuite = suiteFactory.getSuite();
        Suite updateSuite = suiteFactory.getSuite();

        suiteSteps
                .createNewSuite(newSuite)
                .validateSuiteFields(newSuite)
                .updateSuite(newSuite.getTitle(), updateSuite)
                .validateSuiteFields(updateSuite)
                .deleteSuite(updateSuite.getTitle())
                .isSuiteDeleted(updateSuite.getTitle());
    }

    @AfterTest(description = "Delete project after test")
    public void deleteProject(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            projectsSteps
                    .deleteProject(newProject.getCode())
                    .isProjectDeleted(newProject.getTitle());
        }
    }
}
