package tests;

import factories.CaseFactory;
import factories.ProjectFactory;
import factories.TestRunFactory;
import io.qameta.allure.Feature;
import models.Case;
import models.Project;
import models.TestRun;
import org.testng.ITestResult;
import org.testng.annotations.*;
import tests.base.BaseTest;

@Feature("Test Run")
public class TestRunTest extends BaseTest {

    Project newProject;
    Case newCase;

    @BeforeMethod(alwaysRun = true, description = "Login and create project before test")
    public void loginAndCreateNewProject() {
        ProjectFactory projectFactory = new ProjectFactory();
        newProject = projectFactory.getProject();

        CaseFactory caseFactory = new CaseFactory();
        newCase = caseFactory.getCase();

        loginSteps
                .login(USER, PASSWORD);
        projectsSteps
                .createNewProjectViaApi(newProject)
                .open(newProject);
        caseSteps
                .createNewCaseWithoutSuite(newCase);
    }

    @Test(description = "Test Run lifecycle (CRUD)")
    public void testRunShouldBeCreatedThenUpdatedAndDeleted() {
        TestRunFactory testRunFactory = new TestRunFactory();

        TestRun newTestRun = testRunFactory.getTestRun();
        TestRun updTestRun = testRunFactory.getTestRun();

        testRunSteps
                .createNewTestRun(newProject, newTestRun)
                .validateTestRunFields(newTestRun)
                .updateTestPlan(newTestRun.getTestRunTitle(), updTestRun)
                .validateTestRunFields(updTestRun)
                .deleteTestRun(updTestRun.getTestRunTitle())
                .isTestRunDeleted(updTestRun.getTestRunTitle());
    }

    @AfterMethod(description = "Delete project after test")
    public void deleteProject() {
        projectsSteps
                .deleteProjectViaApi(newProject);
    }
}
