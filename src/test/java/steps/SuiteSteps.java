package steps;

import io.qameta.allure.Step;
import models.Suite;
import pages.RepositoryPage;
import steps.base.BaseSteps;

public class SuiteSteps extends BaseSteps {
    RepositoryPage repositoryPage;

    public SuiteSteps() {
        repositoryPage = new RepositoryPage();
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

    @Step("Deleting suite: '{suiteName}'")
    public SuiteSteps deleteSuite(String suiteName) {
        repositoryPage
                .deleteSuite(suiteName)
                .confirmDeletingSuite();
        return this;
    }

    @Step("Updating suite '{suiteName}' with new fields. Title: {updateSuite.title} Description: {updateSuite.description}")
    public SuiteSteps updateSuite(String suiteName, Suite updateSuite) {
        repositoryPage
                .clickEditSuiteButton(suiteName)
                .updateSuite(updateSuite);
        return this;
    }

    @Step("Verifying is '{suiteName}' suite deleted")
    public SuiteSteps isSuiteDeleted(String suiteName) {
        repositoryPage
                .isSuiteDeleted(suiteName);
        return this;
    }
}
