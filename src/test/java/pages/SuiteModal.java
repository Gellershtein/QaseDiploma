package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Input;
import elements.MirrorInput;
import lombok.extern.log4j.Log4j2;
import models.Suite;
import pages.base.BaseModal;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class SuiteModal extends BaseModal {

    private final SelenideElement saveSuiteButton = $("#save-suite-button");

    @Override
    public SuiteModal isOpened() {
        saveSuiteButton.shouldBe(Condition.visible);
        return this;
    }

    public RepositoryPage createNewSuite(Suite suite) {
        fillingSuiteForm(suite);
        saveSuiteButton.click();
        log.info(String.format("Suite: %s", suite));
        return new RepositoryPage();
    }

    public RepositoryPage updateSuite(Suite suite) {
        return createNewSuite(suite);
    }

    private void fillingSuiteForm(Suite suite) {
        new Input("Suite name").clear().write(suite.getTitle());
        new MirrorInput("Description").clear().write(suite.getDescription());
        new MirrorInput("Preconditions").clear().write(suite.getPreconditions());
    }
}


