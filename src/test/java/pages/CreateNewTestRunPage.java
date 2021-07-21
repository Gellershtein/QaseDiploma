package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import elements.MirrorInput;
import models.TestRun;
import pages.base.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CreateNewTestRunPage extends BasePage {
    private final SelenideElement saveTestRunButton = $("#save-run-button");

    @Override
    public CreateNewTestRunPage isOpened() {
        new Button("Start run").shouldBe(visible);
        return this;
    }

    public CreateNewTestRunPage open(String code) {
        Selenide.open("run/" + code + "/create");
        return new CreateNewTestRunPage();
    }

    public TestRunPage createNewTestRun(TestRun testRun) {
        new Input("Run title").clear().write(testRun.getTestRunTitle());
        new MirrorInput("Description").clear().write(testRun.getDescription());
        clickAddCasesButton()
                .addRandomTestCase();
        saveTestRunButton.click();
        return new TestRunPage();
    }

    public SelectCasesModal clickAddCasesButton() {
        new Button("Add cases").click();
        return new SelectCasesModal();
    }

    public TestRunPage updateTestRun(TestRun testRun) {
        return createNewTestRun(testRun);
    }

    @Deprecated
    @Override
    public CreateNewTestRunPage open() throws Exception {
        return null;
    }
}
