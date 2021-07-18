package adapters;

import adapters.base.BaseAdapter;
import io.restassured.response.Response;
import models.Project;
import models.api.CaseResult;

public class CaseAdapter extends BaseAdapter {
    private static final String URI = "v1/case/";

    public CaseResult get(Project project, int caseId, int statusCode) {
        Response response = super.get(String.format("%s%s/%s", URI, project.getCode(), caseId), statusCode);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), CaseResult.class);
    }

    public Response getAll(Project project, int statusCode) {
        Response response = super.get(String.format("%s%s", URI, project.getCode()), statusCode);
        validateTrueStatus(response);
        return response;
    }

    public CaseResult delete(Project project, int caseId, int statusCode) {
        Response response = super.delete(String.format("%s%s/%s", URI, project.getCode(), caseId), statusCode);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), CaseResult.class);
    }
}

