package steps;

import adapters.SuiteAdapter;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.Suite;
import models.api.SuiteResult;
import org.testng.Assert;
import pages.RepositoryPage;
import steps.base.BaseSteps;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SuiteSteps extends BaseSteps {
    RepositoryPage repositoryPage;
    SuiteAdapter suiteAdapter;
    SuiteResult result, status;

    public SuiteSteps() {
        repositoryPage = new RepositoryPage();
        suiteAdapter = new SuiteAdapter();
    }

    @Step("Creating a new suite: {suite.title}")
    public SuiteSteps createNewSuite(Suite suite) {
        repositoryPage
                .clickCreateNewSuiteButton()
                .createNewSuite(suite);
        return this;
    }

    @Step("Validating fields of suite: Title: {suite.title} Description: {suite.description}")
    public SuiteSteps validateSuiteFields(Suite suite) {
        repositoryPage
                .validateSuiteFields(suite);
        return this;
    }

    @Step("Updating suite '{suiteName}' with new fields. Title: {updateSuite.title} Description: {updateSuite.description}")
    public SuiteSteps updateSuite(String suiteName, Suite updateSuite) {
        repositoryPage
                .clickEditSuiteButton(suiteName)
                .updateSuite(updateSuite);
        return this;
    }

    @Step("Deleting suite: '{suiteName}'")
    public SuiteSteps deleteSuite(String suiteName) {
        repositoryPage
                .deleteSuite(suiteName)
                .confirmDeletingSuite();
        return this;
    }

    @Step("Verifying is '{suiteName}' suite deleted")
    public SuiteSteps isSuiteDeleted(String suiteName) {
        repositoryPage
                .isSuiteDeleted(suiteName);
        return this;
    }

    //API METHODS

    @Step("Create new suite for project '{project.title}' with code '{project.code}'")
    public SuiteSteps createNewSuiteViaApi(Project newProject, Suite newSuite) {
        status = suiteAdapter
                .post(newProject, newSuite);
        log.info("Result " + status.getResult());
        assertTrue(status.isStatus());
        return this;
    }

    @Step("Getting suite from '{project.title}' with code '{project.code}'")
    public SuiteSteps getSuiteWithIdAndValidateItViaApi(Project project, Suite newSuite, int suiteId) {
        result = suiteAdapter
                .get(project, suiteId, 200);
        log.info("Result " + result.getResult());
        assertEquals(result.getResult().getTitle(), newSuite.getTitle());
        assertEquals(result.getResult().getDescription(), newSuite.getDescription());
        return this;
    }

    @Step("Update suite from '{project.title}' with code '{project.code}'")
    public SuiteSteps updateSuiteByIdViaApi(Project project, Suite updateSuite, int suiteId) {
        result = suiteAdapter
                .patch(project, updateSuite, suiteId);
        log.info("Result " + result.getResult());
        assertTrue(status.isStatus());
        return this;
    }

    @Step("Getting ALL suite from '{project.title}' with code '{project.code}'")
    public SuiteSteps getAllSuitesUsingAPI(Project project) {
        Response response = suiteAdapter.getAll(project, 200);
        if (response.getStatusCode() == SC_OK) {
            List<Suite> suites = response.getBody().path("result.entities");
            log.info(String.format("Get All suites for project with code: %s. Result list of query: %s", project.getCode(), suites));
            return this;
        }
        Assert.fail("Project codes is not received");
        return this;
    }

    @Step("Deleting project with code: '{projectCode}'")
    public SuiteSteps deleteCaseByIdViaApi(Project project, int suiteId) {
        result = suiteAdapter
                .delete(project, suiteId);
        log.info("Actual Result " + result.getResult());
        log.info(String.format("Case with ID %s for project %s with code %s was deleted ", suiteId, project.getTitle(), project.getCode()));
        return this;
    }
}
