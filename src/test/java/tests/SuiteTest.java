package tests;

import factories.ProjectFactory;
import factories.SuiteFactory;
import models.Project;
import models.Suite;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class SuiteTest extends BaseTest {

    @Test(description = "Test suite lifecycle (CRUD)")
    public void testSuiteShouldBeCreatedThenUpdatedAndDeleted() {
        ProjectFactory projectFactory = new ProjectFactory();
        SuiteFactory suiteFactory = new SuiteFactory();
        Project newProject = projectFactory.getProject();
        Suite newSuite = suiteFactory.getSuite();
        Suite updateSuite = suiteFactory.getSuite();

        loginSteps
                .login(USER, PASSWORD);
        projectsSteps
                .createNewProject(newProject);
        suiteSteps
                .createNewSuite(newSuite)
                .updateSuite(newSuite.getTitle(), updateSuite)
                .validateSuiteFields(updateSuite)
                .deleteSuite(updateSuite.getTitle())
                .isSuiteDeleted(updateSuite.getTitle());
        projectsSteps
                .deleteProject(newProject.getCode())
                .isProjectDeleted(newProject.getTitle());
    }
}
