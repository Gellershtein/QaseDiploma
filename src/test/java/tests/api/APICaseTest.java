package tests.api;

import factories.CaseFactory;
import factories.ProjectFactory;
import io.qameta.allure.Feature;
import lombok.extern.log4j.Log4j2;
import models.Case;
import models.Project;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.CaseSteps;
import steps.ProjectsSteps;

@Log4j2
@Feature("Test Case")
public class APICaseTest {
    Project newProject;
    Case newCase;
    ProjectsSteps projectsSteps = new ProjectsSteps();
    CaseSteps caseSteps = new CaseSteps();

    @BeforeMethod(alwaysRun = true, description = "Create project before test")
    public void preconditions() {
        ProjectFactory projectFactory = new ProjectFactory();
        newProject = projectFactory.getProject();
        CaseFactory caseFactory = new CaseFactory();
        newCase = caseFactory.getCase();


        projectsSteps
                .createNewProjectViaApi(newProject)
                .getProjectViaApi(newProject);

    }

    @Test(description = "API Test Case lifecycle (RD)")
    public void getCaseByIdViaApi() {
        caseSteps
                .createNewCaseViaApi(newProject, newCase)
                .getCaseWithIdAndValidateItViaApi(newProject, newCase, 1)
                .getAllCasesUsingAPI(newProject)
                .deleteCaseByIdViaApi(newProject, 1);
    }

    @AfterMethod(description = "Delete project after test")
    public void postconditions() {
        projectsSteps
                .deleteProjectViaApi(newProject);
    }
}
