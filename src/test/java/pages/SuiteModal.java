package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Input;
import elements.MirrorInput;
import models.Suite;
import pages.base.BaseModal;

import static com.codeborne.selenide.Selenide.$;

public class SuiteModal extends BaseModal {

    private SelenideElement saveSuiteButton = $("#save-suite-button");

    @Override
    public SuiteModal isOpened() {
        saveSuiteButton.shouldBe(Condition.visible);
        return this;
    }

    public RepositoryPage createNewSuite(Suite suite) {
        fillingSuiteForm(suite);
        saveSuiteButton.click();
        return new RepositoryPage();
    }

    public RepositoryPage updateSuite(Suite suite) {
        return createNewSuite(suite);
    }

    //TODO
    //QUESTIONS HERE
    private void fillingSuiteForm(Suite suite) {
        try {
            Thread.sleep(500);
            new Input("Suite name").clear().write(suite.getTitle());
            Thread.sleep(500);
            new MirrorInput("Description").clear().write(suite.getDescription());
            Thread.sleep(500);
            new MirrorInput("Preconditions").clear().write(suite.getPreconditions());
        } catch (InterruptedException error) {
            error.printStackTrace();
        }
    }
}


