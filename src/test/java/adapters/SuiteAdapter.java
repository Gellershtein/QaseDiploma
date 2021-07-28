package adapters;

import adapters.base.BaseAdapter;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.Suite;
import models.api.SuiteResult;

@Log4j2
public class SuiteAdapter extends BaseAdapter {
    private static final String URL = "v1/suite/";

    @Step("Get suite with ID: {suiteId} for project: {project.title}")
    public SuiteResult get(Project project, int suiteId, int statusCode) {
        Response response = super.get(String.format("%s%s/%s", URL, project.getCode(), suiteId), statusCode);
        if (statusCode == 200) {
            validateTrueStatus(response);
        } else {
            validateFalseStatus(response);
        }
        return gson.fromJson(response.asString(), SuiteResult.class);
    }

    @Step("Get ALL cases for project: {project.title}")
    public Response getAll(Project project, int statusCode) {
        Response response = super.get(String.format("%s%s", URL, project.getCode()), statusCode);
        validateTrueStatus(response);
        return response;
    }

    @Step("Create NEW Suite : {suite.title} for project: {project.title}")
    public SuiteResult post(Project project, Suite suite) {
        Response response = super.post(String.format("%s%s", URL, project.getCode()), gson.toJson(suite), 200);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), SuiteResult.class);
    }

    @Step("Update suite: {suite.title} for project: {project.title}")
    public SuiteResult patch(Project project, Suite suite, int suiteId) {
        log.info(String.format("%s/%s/%s", URL, project.getCode(), suiteId));
        Response response = super.patch(String.format("%s%s/%s", URL, project.getCode(), suiteId), gson.toJson(suite), 200);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), SuiteResult.class);
    }

    @Step("Delete suite with ID: {suiteId} for project: {project.title}")
    public SuiteResult delete(Project project, int suiteId) {
        Response response = super.delete(String.format("%s%s/%s", URL, project.getCode(), suiteId), 200);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), SuiteResult.class);
    }
}
