package tests;

import factories.CaseFactory;
import factories.ProjectFactory;
import factories.TestPlanFactory;
import io.qameta.allure.Feature;
import models.Case;
import models.Project;
import models.TestPlan;
import org.testng.ITestResult;
import org.testng.annotations.*;
import tests.base.BaseTest;

@Feature("Test Plan")
public class TestPlanTest extends BaseTest {
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

    @Test(description = "Test Plan lifecycle (CRUD)")
    public void testPlanShouldBeCreatedThenUpdatedAndDeleted() {
        TestPlanFactory testPlanFactory = new TestPlanFactory();

        TestPlan newTestPlan = testPlanFactory.getTestPlan();
        TestPlan updTestPlan = testPlanFactory.getTestPlan();

        testPlanSteps

                .createNewTestPlan(newProject, newTestPlan)
                .validateTestPlanFields(newTestPlan)
                .updateTestPlan(newTestPlan.getTestPlanTitle(), updTestPlan)
                .validateTestPlanFields(updTestPlan)
                .deleteTestPlan(updTestPlan.getTestPlanTitle())
                .isTestPlanDeleted(updTestPlan.getTestPlanTitle());
    }

    @AfterMethod(description = "Delete project after test")
    public void deleteProject() {
        projectsSteps
                .deleteProjectViaApi(newProject);
    }
}
