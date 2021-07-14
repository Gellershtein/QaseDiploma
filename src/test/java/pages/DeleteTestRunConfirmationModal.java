package pages;

import elements.Button;
import pages.base.BaseModal;

import static com.codeborne.selenide.Condition.visible;

public class DeleteTestRunConfirmationModal extends BaseModal {

    @Override
    public DeleteTestRunConfirmationModal isOpened() {
        new Button(" Delete run").shouldBe(visible);
        return this;
    }

    public TestPlanPage confirmDeletingTestPlan() {
        new Button(" Delete run").click();
        return new TestPlanPage();
    }

}
