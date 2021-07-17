package adapters;

import adapters.base.BaseAdapter;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.api.ProjectResult;

@Log4j2
public class ProjectAdapter extends BaseAdapter {
    public static final String URI = "v1/project";

    @Attachment
    @Step("Create a new project: {project.title}")
    public ProjectResult post(Project project) {
        Response response = super.post(String.format("%s", URI), gson.toJson(project), 200);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), ProjectResult.class);
    }

    @Attachment
    @Step("Get project: {project.title}")
    public ProjectResult get(Project project) {
        log.info(project.getCode());
        Response response = super.get(String.format("%s/%s", URI, project.getCode()), 200);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), ProjectResult.class);
    }

    @Attachment
    @Step("Get {limit} project(s)")
    public Response getLimited(int limit) {
        Response response = super.get(String.format("%s?%s=%s", URI, "limit", limit), 200);
        validateTrueStatus(response);
        return response;
    }


    @Attachment
    @Step("Delete project: {project.title}")
    public ProjectResult delete(Project project) {
        Response response = super.delete(String.format("%s/%s", URI, project.getCode()), 200);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), ProjectResult.class);

    }
}
