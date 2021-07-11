package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import elements.Button;
import models.Suite;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class RepositoryPage extends BasePage {

    private String header = "//*[contains(text(),'%s')]";
    private String suiteNameLabel = "//a[@class='suite-header-title' and contains(text(),'%s')]";
    private String suiteDescription = "//*[@class='suite-description']/child::p[contains(text(),'%s')]";
    private String suiteDeleteButton = "//*[@class='suite-header-title' and contains(text(),'%s')]/following-sibling::button/i[contains(@class, 'fa-trash')]";
    private String suiteEditButton = "//*[@class='suite-header-title' and contains(text(),'%s')]/following-sibling::button/i[contains(@class, 'fa-pencil-alt')]";

    public RepositoryPage isOpened(String projectName) {
        $x(String.format(header, projectName)).shouldBe(Condition.exactText(projectName));
        return this;
    }

    public RepositoryPage open(String code) {
        Selenide.open("project/" + code);
        return this;
    }

    @Override
    public RepositoryPage open() throws Exception {
        return this;
    }

    @Override
    public RepositoryPage isOpened() {
        return this;
    }

    //SUITE METHODS
    public SuiteModal clickCreateNewSuiteButton() {
        new Button("Create new suite").click();
        return new SuiteModal();
    }

    public RepositoryPage validateSuiteFields(Suite suite) {
        $x(String.format(suiteNameLabel, suite.getTitle())).shouldHave(Condition.exactText(suite.getTitle()));
        $x(String.format(suiteDescription, suite.getDescription())).shouldHave(Condition.exactText(suite.getDescription()));
        return this;
    }

    public RepositoryPage hoverOnSuiteName(String suiteName) {
        $x(String.format(suiteNameLabel, suiteName)).hover();
        return this;
    }

    public SuiteModal clickEditSuiteButton(String suiteName) {
        hoverOnSuiteName(suiteName);
        $x(String.format(suiteEditButton, suiteName)).click();
        return new SuiteModal();
    }

    public RepositoryPage deleteSuite(String suiteName) {
        hoverOnSuiteName(suiteName);
        $x(String.format(suiteDeleteButton, suiteName)).click();
        return this;
    }

    public RepositoryPage confirmDeletingSuite() {
        new Button("Delete suite").click();
        return this;
    }

    public RepositoryPage isSuiteDeleted(String suiteName) {
        $x(String.format(suiteNameLabel, suiteName)).shouldNotBe(Condition.visible);
        return this;
    }
}
