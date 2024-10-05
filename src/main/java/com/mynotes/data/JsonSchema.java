package com.mynotes.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonSchema {

    public static final File GET_NOTE_BY_ID_RESPONSE_JSON_SCHEMA = new File("src/test/resources/testdata/jsonschemas/getNoteByIdResponseJsonSchema.json");
}
