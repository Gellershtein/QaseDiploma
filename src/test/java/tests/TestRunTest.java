package tests;

import factories.CaseFactory;
import factories.ProjectFactory;
import factories.TestRunFactory;
import models.Case;
import models.Project;
import models.TestRun;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;


public class TestRunTest extends BaseTest {

    Project newProject;
    Case newCase;

    //TODO При падении теста, ретрай аналайзер не может продолжить тест, т.к. браузер остается открытым
    @BeforeMethod(alwaysRun = true, description = "Login and create project before test")
    public void loginAndCreateNewProject() {
        ProjectFactory projectFactory = new ProjectFactory();
        newProject = projectFactory.getProject();

        CaseFactory caseFactory = new CaseFactory();
        newCase = caseFactory.getCase();

        loginSteps
                .login(USER, PASSWORD);
        projectsSteps
                .createNewProject(newProject);
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
    public void deleteProject(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            projectsSteps
                    .deleteProject(newProject.getCode())
                    .isProjectDeleted(newProject.getTitle());
        }
    }
}
