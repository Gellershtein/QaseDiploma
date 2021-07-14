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

    @Step("Creating a new suite: {testRun.testRunTitle}")
    public TestRunSteps createNewTestRun(Project project, TestRun testRun) {
        testRunPage
                .clickTestRunLeftMenuButton()
//                .open(project.getCode())
                .isOpened()
                .clickCreateNewTestRunButton()
                .createNewTestRun(testRun);
        return this;
    }

    @Step("Validating fields of Test Plan: Title: {testRun.testRunTitle}")
    public TestRunSteps validateTestRunFields(TestRun testRun) {
        testRunPage
                .validateTestRunFields(testRun);
        return this;
    }

    @Step("Deleting Test Plan: '{testRunName}'")
    public TestRunSteps deleteTestRun(String testRunName) {
        testRunPage
                .deleteTestRun(testRunName)
                .confirmDeletingTestPlan();
        return this;
    }

    @Step("Updating Test Plan '{testRunName}' with new fields. Title: {updateTestRun.testRunTitle}")
    public TestRunSteps updateTestPlan(String testRunName, TestRun updateTestRun) {
        testRunPage
                .clickEditTestRun(testRunName)
                .updateTestRun(updateTestRun);
        return this;
    }

    @Step("Verifying is '{testRunName}' Test Plan deleted")
    public TestRunSteps isTestRunDeleted(String testRunName) {
        testRunPage
                .isTestRunDeleted(testRunName);
        return this;
    }
}
