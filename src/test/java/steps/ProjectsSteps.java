package steps;

import adapters.ProjectAdapter;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.api.ProjectResult;
import org.testng.Assert;
import pages.ProjectSettingsPage;
import pages.ProjectsListPage;
import steps.base.BaseSteps;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

@Log4j2
public class ProjectsSteps extends BaseSteps {
    ProjectsListPage projectsListPage;
    ProjectSettingsPage projectSettingsPage;
    ProjectAdapter projectAdapter;
    ProjectResult result;
    private List<Project> projectsCodes = new ArrayList<>();

    public ProjectsSteps() {
        projectsListPage = new ProjectsListPage();
        projectSettingsPage = new ProjectSettingsPage();
        projectAdapter = new ProjectAdapter();
    }

    @Step("Open project '{project.title}' with code '{project.code}'")
    public ProjectsSteps open(Project project) {
        projectsListPage
                .open(project.getCode());
        return this;
    }

    @Step("Create project '{project.title}' with code '{project.code}'")
    public ProjectsSteps createNewProject(Project project) {
        projectsListPage
                .open()
                .clickCreateNewProjectButton()
                .createNewProject(project);
        return this;
    }

    @Step("Validating fields of project: {project.code}")
    public ProjectsSteps validateProjectFields(Project project) {
        projectSettingsPage
                .open(project.getCode())
                .validateProjectSettings(project);
        return this;
    }

    @Step("Updating project '{existedProject}' with new fields: '{updateProject.title}' with code '{updateProject.code}'")
    public ProjectsSteps updateProject(String existedProject, Project updateProject) {
        projectSettingsPage
                .open(existedProject)
                .editProject(updateProject);
        return this;
    }

    @Step("Deleting project with code: '{projectCode}'")
    public ProjectsSteps deleteProject(String projectCode) {
        projectSettingsPage
                .open(projectCode)
                .clickDeleteProjectButton()
                .confirmDeleting();
        return this;
    }

    @Step("Validating is project '{projectName}' deleted")
    public ProjectsSteps isProjectDeleted(String projectName) {
        projectsListPage
                .isProjectDeleted(projectName);
        return this;
    }

    // API STEPS METHODS
    @Step("Create project '{project.title}' with code '{project.code}'")
    public ProjectsSteps createNewProjectViaApi(Project project) {
        result = projectAdapter
                .post(project);
        log.info("Result " + result.getResult());
        assertEquals(result.getResult().getCode(), project.getCode());
        return this;
    }

    @Step("Getting project '{project.title}' with code '{project.code}'")
    public ProjectsSteps getProjectViaApi(Project project) {
        result = projectAdapter
                .get(project);
        log.info("Expected Result " + project);
        log.info("Actual Result " + result.getResult());
        assertEquals(result.getResult().getCode(), project.getCode());
        return this;
    }

    @Step("Getting limited numbers of project")
    public ProjectsSteps getLimitedProjectsViaApi(int limit) {
        log.info(String.format("Get %s projects. Result list of query: %s", limit, getProjectsCodesUsingAPI(limit)));
        return this;
    }

    private List<Project> getProjectsCodesUsingAPI(int limit) {
        Response response = projectAdapter.getLimited(limit);
        if (response.getStatusCode() == SC_OK) {
            List<Project> codes = response.getBody().path("result.entities");
            projectsCodes.addAll(codes);
            return projectsCodes;
        }
        Assert.fail(String.format("Project codes '%s' is not received", projectsCodes));
        return projectsCodes;
    }

    @Step("Deleting project with code: '{projectCode}'")
    public ProjectsSteps deleteProjectViaApi(Project project) {
        result = projectAdapter
                .delete(project);
        log.info("Expected Result " + project);
        log.info("Actual Result " + result.getResult());
        try {
            assertEquals(result.getResult().getCode(), project.getCode());
        } catch (NullPointerException exception) {
            log.info(String.format("Project %s with code %s was deleted ", project.getTitle(), project.getCode()));
        }
        return this;
    }
}
