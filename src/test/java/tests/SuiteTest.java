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

    @BeforeMethod(alwaysRun = true, description = "Login and create project before test")
    public void loginAndCreateNewProject() {
        ProjectFactory projectFactory = new ProjectFactory();
        newProject = projectFactory.getProject();

        loginSteps
                .login(USER, PASSWORD);
        projectsSteps
                .createNewProjectViaApi(newProject)
                .open(newProject);
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

    @AfterMethod(description = "Delete project after test")
    public void deleteProject() {
        projectsSteps
                .deleteProjectViaApi(newProject);
    }
}
