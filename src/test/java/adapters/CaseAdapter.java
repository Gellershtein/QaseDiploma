package adapters;


import adapters.base.BaseAdapter;
import io.qameta.allure.Attachment;
import io.restassured.response.Response;
import models.Case;
import models.Project;
import models.api.CaseResult;

public class CaseAdapter extends BaseAdapter {
    private static final String URL = "v1/case/";

    @Attachment
    public CaseResult post(Project project, Case newCase, int statusCode) {
        Response response = super.post(String.format("%s%s", URL, project.getCode()), gson.toJson(newCase), statusCode);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), CaseResult.class);
    }

    @Attachment
    public CaseResult get(Project project, int caseId, int statusCode) {
        Response response = super.get(String.format("%s%s/%s", URL, project.getCode(), caseId), statusCode);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), CaseResult.class);
    }

    @Attachment
    public Response getAll(Project project, int statusCode) {
        Response response = super.get(String.format("%s%s", URL, project.getCode()), statusCode);
        validateTrueStatus(response);
        return response;
    }

    @Attachment
    public CaseResult delete(Project project, int caseId, int statusCode) {
        Response response = super.delete(String.format("%s%s/%s", URL, project.getCode(), caseId), statusCode);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), CaseResult.class);
    }
}

