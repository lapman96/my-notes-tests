package com.mynotes.validator;

import com.mynotes.client.BaseClient;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseValidator {

    protected BaseClient client;

    protected BaseValidator(BaseClient client) {
        this.client = client;
    }

    public BaseValidator validateStatusCode(int expectedStatusCode) {
        assertThat(client.getResponse().statusCode()).isEqualTo(expectedStatusCode);
        return this;
    }

    public BaseValidator validateValueByJsonPath(String jsonPath, String expectedValue) {
        assertThat(client.getResponse().jsonPath().getString(jsonPath)).isEqualTo(expectedValue);
        return this;
    }

    public void validateResponseAgainstJsonSchema(File jsonSchema) {
        client.getResponse().then().body(matchesJsonSchema(jsonSchema));
    }
}
