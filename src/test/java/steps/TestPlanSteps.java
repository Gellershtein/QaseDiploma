package steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import models.Project;
import models.TestPlan;
import pages.TestPlanPage;
import steps.base.BaseSteps;

import static com.codeborne.selenide.Selenide.refresh;

public class TestPlanSteps extends BaseSteps {
    TestPlanPage testPlanPage;

    public TestPlanSteps() {
        testPlanPage = new TestPlanPage();
    }

    @Step("Creating a new Test Plan: {testPlan.testPlanTitle}")
    public TestPlanSteps createNewTestPlan(Project project, TestPlan testPlan) {
        testPlanPage
//                .clickTestPlanLeftMenuButton()
                .openCreatePlan(project)
                .isOpened()
                .clickCreateNewTestPlanButton()
                .createNewTestPlan(testPlan);
        return this;
    }

    @Step("Validating fields of Test Plan: Title: {testPlan.testPlanTitle} Description: {testPlan.description}")
    public TestPlanSteps validateTestPlanFields(TestPlan testPlan) {
        testPlanPage
                .validateTestPlanFields(testPlan);
        return this;
    }

    @Step("Deleting Test Plan: '{testPlanName}'")
    public TestPlanSteps deleteTestPlan(String testPlanName) {
        testPlanPage
                .deleteTestPlan(testPlanName)
                .confirmDeletingTestPlan();
        return this;
    }

    @Step("Updating Test Plan '{testPlanName}' with new fields. Title: {updateTestPlan.testPlanTitle} Description: {updateTestPlan.description}")
    public TestPlanSteps updateTestPlan(String testPlanName, TestPlan updateTestPlan) {
        testPlanPage
                .clickEditTestPlan(testPlanName)
                .updateTestPlan(updateTestPlan);
        return this;
    }

    @Step("Verifying is '{testPlanName}' Test Plan deleted")
    public TestPlanSteps isTestPlanDeleted(String testPlanName) {
        testPlanPage
                .isTestPlanDeleted(testPlanName);
        return this;
    }
}
