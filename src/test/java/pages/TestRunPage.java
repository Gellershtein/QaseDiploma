package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import elements.Button;
import elements.TreeDotsDropdown;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.TestRun;
import pages.base.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TestRunPage extends BasePage {
    private final String testRunNameLabel = "//a[@class='defect-title' and contains(text(),'%s')]";
    private final String testRunLeftMenuButton = String.format(leftMenuButton, "Test Runs");
//            $(By.id("menu-link-test-runs"));


    @Override
    @Step("Validation that the Test Run is opened")
    public TestRunPage isOpened() {
        new Button("Start new test run").shouldBe(visible);
        return this;
    }

    @Step("Open Test Run Page")
    public TestRunPage open(Project project) {
        Selenide.open("run/" + project.getCode());
        return this;
    }

    public CreateNewTestRunPage clickCreateNewTestRunButton() {
        new Button("Start new test run").shouldBe(visible).click();
        return new CreateNewTestRunPage();
    }

    public TestRunPage validateTestRunFields(TestRun testRun) {
        $x(String.format(testRunNameLabel, testRun.getTestRunTitle())).shouldHave(Condition.exactText(testRun.getTestRunTitle()));
        return this;
    }

    public TestRunPage clickTestRunLeftMenuButton() {
//        Configuration.clickViaJs = true;
        $x(testRunLeftMenuButton).click();
//        Configuration.clickViaJs = false;
        return new TestRunPage();
    }

    public CreateNewTestRunPage clickEditTestRun(String testRunName) {
        new TreeDotsDropdown().setDropdown("Edit run");
        return new CreateNewTestRunPage();
    }

    public DeleteTestRunConfirmationModal deleteTestRun(String testRunName) {
        new TreeDotsDropdown().setDropdown("Delete");
        return new DeleteTestRunConfirmationModal();
    }

    public TestRunPage isTestRunDeleted(String testRunName) {
        $x(String.format(testRunNameLabel, testRunName)).shouldNotBe(Condition.visible);
        return this;
    }

    @Deprecated
    @Override
    public BasePage open() throws Exception {
        return null;
    }
}
