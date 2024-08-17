package com.mynotes.data;

import com.mynotes.models.request.CreateNoteRequestFormParams;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestData extends PropertiesManager {

    public static final String TEST_USER_EMAIL = properties.getProperty("test_user_email");

    public static final String TEST_USER_PASSWORD = properties.getProperty("test_user_password");

    public static final CreateNoteRequestFormParams REQUEST_PARAMS_FOR_DEFAULT_NOTE = CreateNoteRequestFormParams.builder()
            .title("New title")
            .description("New description")
            .category("Home")
            .build();
}
