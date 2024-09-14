package com.mynotes.validator;

import com.mynotes.client.NotesClient;
import com.mynotes.models.response.getnotes.GetNotesResponse;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class NotesValidator extends BaseValidator {

    public NotesValidator(NotesClient client) {
        super(client);
    }

    public void validateNoteByIdNotExist(String noteId) {
        boolean noteExists = client.getResponse().as(GetNotesResponse.class).getData()
                .stream()
                .anyMatch(note -> Objects.equals(note.getId(), noteId));

        assertThat(noteExists).isFalse();
    }
}
