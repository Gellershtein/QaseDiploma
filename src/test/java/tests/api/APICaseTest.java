package tests.api;

import factories.CaseFactory;
import factories.ProjectFactory;
import lombok.extern.log4j.Log4j2;
import models.Case;
import models.Project;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2

public class APICaseTest extends BaseTest {
    Project newProject;
    Case newCase;

    @BeforeMethod(alwaysRun = true, description = "Create project before test")
    public void createNewProject() {
        ProjectFactory projectFactory = new ProjectFactory();
        newProject = projectFactory.getProject();
        CaseFactory caseFactory = new CaseFactory();
        newCase = caseFactory.getCase();


        projectsSteps
                .createNewProjectViaApi(newProject)
                .getProjectViaApi(newProject);

        loginSteps
                .login(USER, PASSWORD);
        caseSteps
                .createNewCaseForProject(newProject, newCase);
    }

    @Test(description = "Get case from project: {newProject.title} by ID and validate it")
    public void getCaseByIdViaApi() {
        caseSteps
                .getCaseWithIdAndValidateItViaApi(newProject, newCase, 1)
                .getAllCasesUsingAPI(newProject)
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
