package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import elements.MirrorInput;
import models.TestPlan;
import pages.base.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CreateNewTestPlanPage extends BasePage {
    private SelenideElement saveTestPlanButton = $("#save-plan");

    @Override
    public CreateNewTestPlanPage isOpened() {
        new Button("Create plan").shouldBe(visible);
        return this;
    }

    public CreateNewTestPlanPage open(String code) {
        Selenide.open("plan/" + code + "/create");
        return new CreateNewTestPlanPage();
    }

    public TestPlanPage createNewTestPlan(TestPlan testPlan) {
        new Input("Title").clear().write(testPlan.getTestPlanTitle());
        new MirrorInput("Description").clear().write(testPlan.getDescription());
        clickAddCasesButton()
                .addRandomTestCase();
        saveTestPlanButton.click();
        return new TestPlanPage();
    }

    public SelectCasesModal clickAddCasesButton() {
        new Button("Add cases").click();
        return new SelectCasesModal();
    }

    public TestPlanPage updateTestPlan(TestPlan testPlan) {
        return createNewTestPlan(testPlan);
    }


    @Deprecated
    @Override
    public BasePage open() throws Exception {
        return null;
    }
}
