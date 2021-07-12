package steps;

import io.qameta.allure.Step;
import models.Suite;
import pages.RepositoryPage;
import steps.base.BaseSteps;

public class CaseSteps extends BaseSteps {
    RepositoryPage repositoryPage;

    public CaseSteps() {
        repositoryPage = new RepositoryPage();
    }

    @Step("Creating a new suite: {suite.title}")
    public CaseSteps createNewSuite(Suite suite) {
        repositoryPage
                .clickCreateNewSuiteButton()
                .createNewSuite(suite);
        return this;
    }

    @Step("Validating fields of suite: Title: {suite.title} Description: {suite.description}")
    public CaseSteps validateSuiteFields(Suite suite) {
        repositoryPage
                .validateSuiteFields(suite);
        return this;
    }

    @Step("Deleting suite: '{suiteName}'")
    public CaseSteps deleteSuite(String suiteName) {
        repositoryPage
                .deleteSuite(suiteName)
                .confirmDeletingSuite();
        return this;
    }

    @Step("Updating suite '{suiteName}' with new fields. Title: {updateSuite.title} Description: {updateSuite.description}")
    public CaseSteps updateSuite(String suiteName, Suite updateSuite) {
        repositoryPage
                .clickEditSuiteButton(suiteName)
                .updateSuite(updateSuite);
        return this;
    }

    @Step("Verifying is '{suiteName}' suite deleted")
    public CaseSteps isSuiteDeleted(String suiteName) {
        repositoryPage
                .isSuiteDeleted(suiteName);
        return this;
    }
}
