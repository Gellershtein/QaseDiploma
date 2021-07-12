package pages;

import elements.Button;
import pages.base.BaseModal;
import pages.base.BasePage;

import static com.codeborne.selenide.Condition.visible;

public class DeleteConfirmationPage extends BaseModal {

    @Override
    public DeleteConfirmationPage isOpened() {
        new Button(" Delete project").shouldBe(visible);
        return this;
    }

    public ProjectsListPage confirmDeleting() {
        new Button(" Delete project").click();
        return new ProjectsListPage();
    }

}
