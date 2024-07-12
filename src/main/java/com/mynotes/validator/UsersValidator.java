package com.mynotes.validator;

import com.mynotes.client.UsersClient;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersValidator extends BaseValidator{

    public UsersValidator(UsersClient client) {super(client);}

    @Override
    public UsersValidator validateStatusCode(int expectedStatusCode) {
        assertThat(client.getResponse().statusCode()).isEqualTo(expectedStatusCode);
        return this;
    }
}
