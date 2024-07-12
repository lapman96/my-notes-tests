package com.mynotes.client;

import com.mynotes.models.request.CreateNoteRequestFormParams;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

import static com.mynotes.data.enums.HttpHeaders.CONTENT_TYPE;
import static com.mynotes.data.enums.HttpHeaders.X_AUTH_TOKEN;
import static com.mynotes.data.EndpointsRoutes.noteById;
import static com.mynotes.data.EndpointsRoutes.notes;

public class NotesClient extends BaseClient {

    public void createNote(CreateNoteRequestFormParams createNoteRequestParams, String token) {
        postWithFormParams(notes(), createNoteRequestParams,
                new Header(CONTENT_TYPE.getHeaderName(),ContentType.URLENC.withCharset("UTF-8")),
                new Header(X_AUTH_TOKEN.getHeaderName(), token));
    }

    public void getNote(String noteId, String token) {
        get(noteById(noteId),
                new Header(CONTENT_TYPE.getHeaderName(), ContentType.URLENC.withCharset("UTF-8")),
                new Header(X_AUTH_TOKEN.getHeaderName(), token));
    }

    public void getNotes( String token) {
        get(notes(),
                new Header(CONTENT_TYPE.getHeaderName(), ContentType.URLENC.withCharset("UTF-8")),
                new Header(X_AUTH_TOKEN.getHeaderName(), token));
    }

    public void deleteNote(String noteId, String token) {
        delete(noteById(noteId),
                new Header(CONTENT_TYPE.getHeaderName(), ContentType.URLENC.withCharset("UTF-8")),
                new Header(X_AUTH_TOKEN.getHeaderName(), token));
    }
}
