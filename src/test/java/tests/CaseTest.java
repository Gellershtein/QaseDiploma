package tests;

import factories.CaseFactory;
import factories.ProjectFactory;
import io.qameta.allure.Feature;
import lombok.extern.log4j.Log4j2;
import models.Case;
import models.Project;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
@Feature("Test Case")
public class CaseTest extends BaseTest {
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

    @Test(description = "Test case lifecycle (CRUD)")
    public void testCaseShouldBeCreatedThenUpdatedAndDeleted() {

        CaseFactory caseFactory = new CaseFactory();
        Case newCase = caseFactory.getCase();
        Case updateCase = caseFactory.getCase();

        caseSteps
                .createNewCaseWithoutSuite(newCase)
                .validateCaseFields(newCase.getTitle(), newCase)
                .updateCase(newCase.getTitle(), updateCase)
                .validateCaseFields(updateCase.getTitle(), updateCase)
                .deleteCase(updateCase.getTitle())
                .isCaseDeleted(updateCase.getTitle());
    }

    @AfterMethod(description = "Delete project after test")
    public void deleteProject(ITestResult result) {
        projectsSteps
                .deleteProjectViaApi(newProject);
    }
}
