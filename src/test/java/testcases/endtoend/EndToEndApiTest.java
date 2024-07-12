package testcases.endtoend;

import org.junit.jupiter.api.*;
import testcases.BaseApiTest;

import static com.mynotes.data.JsonSchema.GET_NOTE_BY_ID_RESPONSE_JSON_SCHEMA;
import static com.mynotes.data.TestData.REQUEST_PARAMS_FOR_DEFAULT_NOTE;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("API")
class EndToEndApiTest extends BaseApiTest {

    private ThreadLocal<String> noteId;

    @BeforeEach
    void beforeEachTest() {
        noteId = ThreadLocal.withInitial(String::new);
    }

    @Tag("P1")
    @Test
    void checkTheAbilityToAddAndRemoveProductFromCart() {

        step("Create a note and get its ID");
        notesClient.get().createNote(REQUEST_PARAMS_FOR_DEFAULT_NOTE,token.get());

        notesValidator.get().validateStatusCode(200);
        noteId.set(notesClient.get().getResponseValueByPath("data.id"));

        step("Get note by ID and validate it");
        notesClient.get().getNote(noteId.get(), token.get());

        notesValidator.get().validateStatusCode(200)
                .validateResponseAgainstJsonSchema(GET_NOTE_BY_ID_RESPONSE_JSON_SCHEMA);
        assertThat(notesClient.get().getResponseValueByPath("data.id")).isEqualTo(noteId.get());

        step("Delete the created note");
        notesClient.get().deleteNote(noteId.get(), token.get());

        notesValidator.get().validateStatusCode(200);

        step("Verify that the note is removed");
        notesClient.get().getNotes(token.get());

        notesValidator.get().validateStatusCode(200)
                .validateNoteByIdNotExist(noteId.get());
    }

    @AfterEach
    void afterEachTest() {
        step("Post conditions");
        notesClient.get().deleteNote(noteId.get(),token.get());
    }
}
