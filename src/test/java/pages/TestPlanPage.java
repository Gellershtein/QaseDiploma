package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.TreeDotsDropdown;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestPlan;
import pages.base.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TestPlanPage extends BasePage {
    private final String testPlanNameLabel = "//a[@class='defect-title' and contains(text(),'%s')]";
    private final String testPlanDescription = "//*[contains(text(),'%s')]";
    private final SelenideElement testPlanLeftMenuButton = $("#menu-link-test-plans");

    @Override
    @Step("Validation that the Test Plan is opened")
    public TestPlanPage isOpened() {
        new Button("Create test plan").shouldBe(visible);
        return this;
    }

    @Step("Open Test Plan Page")
    public TestPlanPage open(String code) {
        Selenide.open("plan/" + code);
        return this;
    }

    public CreateNewTestPlanPage clickCreateNewTestPlanButton() {
        new Button("Create test plan").click();
        return new CreateNewTestPlanPage();
    }

    public TestPlanPage validateTestPlanFields(TestPlan testPlan) {
        $x(String.format(testPlanNameLabel, testPlan.getTestPlanTitle())).shouldHave(Condition.exactText(testPlan.getTestPlanTitle()));
        $x(String.format(testPlanDescription, testPlan.getDescription())).shouldHave(Condition.exactText(testPlan.getDescription()));
        return this;
    }

    public TestPlanPage clickTestPlanLeftMenuButton() {
        testPlanLeftMenuButton.click();
        return new TestPlanPage();
    }

    public CreateNewTestPlanPage clickEditTestPlan(String testPlanName) {
        $x(String.format(testPlanNameLabel, testPlanName)).hover().click();
        return new CreateNewTestPlanPage();
    }

    public DeleteTestPlanConfirmationModal deleteTestPlan(String testPlanName) {
        new TreeDotsDropdown().setDropdown("Delete");
        return new DeleteTestPlanConfirmationModal();
    }

    public TestPlanPage isTestPlanDeleted(String testPlanName) {
        $x(String.format(testPlanNameLabel, testPlanName)).shouldNotBe(Condition.visible);
        return this;
    }

    @Deprecated
    @Override
    public BasePage open() throws Exception {
        return null;
    }
}
