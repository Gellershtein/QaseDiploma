package steps;

import io.qameta.allure.Step;
import models.Case;
import pages.RepositoryPage;
import steps.base.BaseSteps;

public class CaseSteps extends BaseSteps {
    RepositoryPage repositoryPage;

    public CaseSteps() {
        repositoryPage = new RepositoryPage();
    }

    @Step("Creating '{aCase.title}' case")
    public CaseSteps createNewCaseWithoutSuite(Case newCase) {
        repositoryPage
                .clickCreateNewCaseButton()
                .createNewCase(newCase);
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

}
