package adapters.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class BaseAdapter {
    public static final String TOKEN = System.getenv().getOrDefault("QASE_TOKEN", PropertyReader.getProperty("qase.token"));
    public static final String URL = System.getenv().getOrDefault("QASE_API_URL", PropertyReader.getProperty("qase.apiUrl"));
    protected Gson gson =
            new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
    public static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .addHeader("Token", TOKEN)
                    .build();

    @Step("Doing the get request to: '{request}', validating status code: '{statusCode}'")
    protected Response get(String request, int statusCode) {

        return
                given()
                        .spec(REQ_SPEC)
                        .when()
                        .get(String.format("%s%s", URL, request))
                        .then()
                        .log().body()
                        .statusCode(statusCode)
                        .extract().response();
    }

    @Step("Doing the delete request, validating status code: '{statusCode}'")
    protected Response delete(String request, int statusCode) {

        return
                given()
                        .spec(REQ_SPEC)
                        .when()
                        .delete(String.format("%s%s", URL, request))
                        .then()
                        .log().body()
                        .statusCode(statusCode)
                        .extract().response();
    }

    @Step("Doing the post request to: '{request}', validating status code: '{statusCode}'")
    protected Response post(String request, String body, int statusCode) {

        return
                given()
                        .spec(REQ_SPEC)
                        .body(body)
                        .log().body()
                        .when()
                        .post(String.format("%s%s", URL, request))
                        .then()
                        .log().body()
                        .statusCode(statusCode)
                        .extract().response();
    }

    @Step("Doing the patch request, validating status code: '{statusCode}'")
    protected Response patch(String request, String body, int statusCode) {

        return
                given()
                        .spec(REQ_SPEC)
                        .body(body)
                        .log().body()
                        .when()
                        .patch(String.format("%s%s", URL, request))
                        .then()
                        .log().body()
                        .statusCode(statusCode)
                        .extract().response();
    }


    @Step("Validating that response status via JsonPath is TRUE")
    protected BaseAdapter validateTrueStatus(Response response) {
        assertTrue(response.jsonPath().getBoolean("status"));
        return this;
    }

    @Step("Validating that response status via JsonPath is FALSE")
    protected BaseAdapter validateFalseStatus(Response response) {
        assertFalse(response.jsonPath().getBoolean("status"));
        return this;
    }
}
