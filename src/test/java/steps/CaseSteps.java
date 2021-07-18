package steps;

import adapters.CaseAdapter;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.Case;
import models.Project;
import models.api.CaseResult;
import org.testng.Assert;
import pages.CreateNewCasePage;
import pages.RepositoryPage;
import steps.base.BaseSteps;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

@Log4j2
public class CaseSteps extends BaseSteps {
    RepositoryPage repositoryPage;
    CreateNewCasePage createNewCasePage;
    CaseAdapter caseAdapter;
    CaseResult result;

    public CaseSteps() {
        repositoryPage = new RepositoryPage();
        createNewCasePage = new CreateNewCasePage();
        caseAdapter = new CaseAdapter();
    }

    @Step("Creating '{newCase.title}' case")
    public CaseSteps createNewCaseWithoutSuite(Case newCase) {
        repositoryPage
                .clickCreateNewCaseButton()
                .createNewCase(newCase);
        return this;
    }

    @Step("Creating '{newCase.title}' case")
    public CaseSteps createNewCaseForProject(Project project, Case newCase) {
        createNewCasePage
                .createNewCase(project, newCase);
        return this;
    }

    @Step("Updating case '{caseName}' with new fields")
    public CaseSteps updateCase(String caseName, Case theCase) {
        repositoryPage
                .clickEditCaseButton(caseName)
                .updateCase(theCase);
        return this;
    }

    @Step("Validating fields of case: '{caseName}'")
    public CaseSteps validateCaseFields(String caseName, Case theCase) {
        repositoryPage
                .clickEditCaseButton(caseName)
                .validateCaseFields(theCase);
        return this;
    }

    @Step("Deleting '{caseName}' case")
    public CaseSteps deleteCase(String caseName) {
        repositoryPage
                .deleteCase(caseName)
                .confirmDeletingCase();
        return this;
    }

    @Step("Verifying is '{caseName}' case deleted")
    public CaseSteps isCaseDeleted(String caseName) {
        repositoryPage
                .isCaseDeleted(caseName);
        return this;
    }

    //API METHODS
    @Step("Getting case from '{project.title}' with code '{project.code}'")
    public CaseSteps getCaseWithIdAndValidateItViaApi(Project project, Case newCase, int caseId) {
        result = caseAdapter
                .get(project, caseId, 200);
//        assertEquals(result.getResult(), newCase);
        assertEquals(result.getResult().getTitle(), newCase.getTitle());
        assertEquals(result.getResult().getDescription(), newCase.getDescription());
        return this;
    }

    @Step("Getting ALL cases from '{project.title}' with code '{project.code}'")
    public CaseSteps getAllCasesUsingAPI(Project project) {
        Response response = caseAdapter.getAll(project, 200);
        if (response.getStatusCode() == SC_OK) {
            List<Case> cases = response.getBody().path("result.entities");
            log.info(String.format("Get All cases for project with code: %s. Result list of query: %s", project.getCode(), cases));
            return this;
        }
        Assert.fail("Project codes is not received");
        return this;
    }

    @Step("Deleting project with code: '{projectCode}'")
    public CaseSteps deleteCaseByIdViaApi(Project project, int caseId) {
        result = caseAdapter
                .delete(project, caseId, 200);
        log.info("Actual Result " + result.getResult());
        try {
            assertEquals(result.getResult().getId(), caseId);
        } catch (NullPointerException exception) {
            log.info(String.format("Case with ID %s for project %s with code %s was deleted ", caseId, project.getTitle(), project.getCode()));
        }
        return this;
    }
}
