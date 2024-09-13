package com.mynotes.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

import static com.mynotes.data.EndpointsRoutes.getBaseUrl;

@Getter
public abstract class BaseClient {

    static {
        RestAssured.baseURI = getBaseUrl();
    }

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private Response response;

    private RequestSpecification getSpecification(Header... headers) {
        RequestSpecification requestSpecification = RestAssured.given().filter(new AllureRestAssured());

        Arrays.stream(headers).forEach(requestSpecification::header);
        return requestSpecification;
    }

    protected Response get(String url, Header... headers){
        response = getSpecification(headers)
                .log().all()
                .when().get(url)
                .then().log().all()
                .extract().response();
        return response;
    }

    protected Response postWithFormParams(String url, Object body, Header... headers) {
        response = getSpecification(headers)
                .formParams(convertRequestParamsIntoMap(body))
                .log().all()
                .when().post(url)
                .then().log().all()
                .extract().response();
        return response;
    }

    protected Response putWithFormParams(String url, Object body, Header... headers) {
        response = getSpecification(headers)
                .formParams(convertRequestParamsIntoMap(body))
                .log().all()
                .when().put(url,body)
                .then().log().all()
                .extract().response();
        return response;
    }

    protected Response patchWithFormParams(String url, Object body, Header... headers) {
        response = getSpecification(headers)
                .formParams(convertRequestParamsIntoMap(body))
                .log().all()
                .when().patch(url,body)
                .then().log().all()
                .extract().response();
        return response;
    }

    protected Response delete(String url, Header... headers) {
        response = getSpecification(headers)
                .log().all()
                .when().delete(url)
                .then().log().all()
                .extract().response();
        return response;
    }

    private Map<String, Object> convertRequestParamsIntoMap(Object object) {
        return OBJECT_MAPPER.convertValue(object, new TypeReference<>() {});
    }

    public String getResponseValueByPath(String jsonPath) {
        Object result = getResponse().jsonPath().get(jsonPath);

        if (result == null) {
            throw new IllegalArgumentException("JSON path '" + jsonPath + "' not found in the response.");
        }
        return result.toString();
    }
}
