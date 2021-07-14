package pages;

import elements.Button;
import pages.base.BaseModal;

import static com.codeborne.selenide.Condition.visible;

public class DeleteTestPlanConfirmationModal extends BaseModal {

    @Override
    public DeleteTestPlanConfirmationModal isOpened() {
        new Button(" Delete plan").shouldBe(visible);
        return this;
    }

    public TestPlanPage confirmDeletingTestPlan() {
        new Button(" Delete plan").click();
        return new TestPlanPage();
    }

}
