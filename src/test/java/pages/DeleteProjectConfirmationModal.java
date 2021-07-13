package pages;

import elements.Button;
import pages.base.BaseModal;

import static com.codeborne.selenide.Condition.visible;

public class DeleteProjectConfirmationModal extends BaseModal {

    @Override
    public DeleteProjectConfirmationModal isOpened() {
        new Button(" Delete project").shouldBe(visible);
        return this;
    }

    public ProjectsListPage confirmDeleting() {
        new Button(" Delete project").click();
        return new ProjectsListPage();
    }

}
