package com.mynotes.validator;

import com.mynotes.client.BaseClient;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public abstract class BaseValidator {

    protected BaseClient client;

    protected BaseValidator(BaseClient client) {
        this.client = client;
    }

    protected abstract BaseValidator validateStatusCode(int expectedStatusCode);

    public void validateResponseAgainstJsonSchema(File jsonSchema) {
        client.getResponse().then().body(matchesJsonSchema(jsonSchema));
    }
}
