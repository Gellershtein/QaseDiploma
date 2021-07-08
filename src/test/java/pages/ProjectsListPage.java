package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import elements.Button;
import pages.base.BasePage;

public class ProjectsListPage extends BasePage {

    @Override
    public ProjectsListPage isOpened() {
        new Button("Create new project").shouldBe(Condition.visible);
        return this;
    }

    @Override
    public ProjectsListPage open() {
        Selenide.open("/projects");
        return this;
    }
}
