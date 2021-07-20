package steps;

import io.qameta.allure.Step;
import models.Project;
import models.TestRun;
import pages.TestRunPage;
import steps.base.BaseSteps;

public class TestRunSteps extends BaseSteps {
    TestRunPage testRunPage;

    public TestRunSteps() {
        testRunPage = new TestRunPage();
    }

    @Step("Creating a new Test Run: {testRun.testRunTitle}")
    public TestRunSteps createNewTestRun(Project project, TestRun testRun) {
        testRunPage
//                .clickTestRunLeftMenuButton()
                .open(project.getCode())
                .isOpened()
                .clickCreateNewTestRunButton()
                .createNewTestRun(testRun);
        return this;
    }

    @Step("Validating fields of Test Run: Title: {testRun.testRunTitle}")
    public TestRunSteps validateTestRunFields(TestRun testRun) {
        testRunPage
                .validateTestRunFields(testRun);
        return this;
    }

    @Step("Deleting Test Run: '{testRunName}'")
    public TestRunSteps deleteTestRun(String testRunName) {
        testRunPage
                .deleteTestRun(testRunName)
                .confirmDeletingTestPlan();
        return this;
    }

    @Step("Updating Test Run '{testRunName}' with new fields. Title: {updateTestRun.testRunTitle}")
    public TestRunSteps updateTestPlan(String testRunName, TestRun updateTestRun) {
        testRunPage
                .clickEditTestRun(testRunName)
                .updateTestRun(updateTestRun);
        return this;
    }

    @Step("Verifying is '{testRunName}' Test Run deleted")
    public TestRunSteps isTestRunDeleted(String testRunName) {
        testRunPage
                .isTestRunDeleted(testRunName);
        return this;
    }
}
