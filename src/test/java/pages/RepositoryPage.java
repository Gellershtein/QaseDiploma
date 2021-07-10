package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class RepositoryPage extends BasePage {

    private String header = "//*[contains(text(),'%s')]";

    public RepositoryPage isOpened(String projectName) {
        $x(String.format(header, projectName)).shouldBe(Condition.exactText(projectName));
        return this;
    }

    public RepositoryPage open(String code) {
        Selenide.open("project/" + code);
        return this;
    }

    @Override
    public RepositoryPage open() throws Exception {
        return this;
    }

    @Override
    public RepositoryPage isOpened() {
        return this;
    }
}
