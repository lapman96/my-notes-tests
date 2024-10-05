package testcases.endtoend;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import testcases.BaseApiTest;

import static com.mynotes.data.JsonSchema.GET_NOTE_BY_ID_RESPONSE_JSON_SCHEMA;
import static com.mynotes.data.TestData.REQUEST_PARAMS_FOR_DEFAULT_NOTE;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.Assertions.assertThat;

@Tags({@Tag("API"), @Tag("EndToEnd")})
@Epic("EndToEnd")
@Owner("Serhii Lapin")
@Link(name = "Notes API Documentation", url = "https://practice.expandtesting.com/notes/api/api-docs/")
class EndToEndApiTest extends BaseApiTest {

    @Test
    @Tag("P1")
    @DisplayName("[E2E] Check the ability to add and remove a product from the cart")
    @Severity(CRITICAL)
    void checkTheAbilityToAddAndRemoveProductFromCart() {

        String noteId = Allure.step("Create a note and get its ID", () -> {
            notesClient.get().createNote(REQUEST_PARAMS_FOR_DEFAULT_NOTE, token.get());
            notesValidator.get().validateStatusCode(200);
            return notesClient.get().getResponseValueByPath("data.id");
        });

        Allure.step("Get note by ID and validate it", () -> {
            notesClient.get().getNote(noteId, token.get());
            notesValidator.get().validateStatusCode(200)
                    .validateResponseAgainstJsonSchema(GET_NOTE_BY_ID_RESPONSE_JSON_SCHEMA);
            assertThat(notesClient.get().getResponseValueByPath("data.id")).isEqualTo(noteId);
        });

        Allure.step("Delete the created note", () -> {
            notesClient.get().deleteNote(noteId, token.get());
            notesValidator.get().validateStatusCode(200);
        });

        Allure.step("Verify that the note is removed", () -> {
            notesClient.get().getNotes(token.get());
            notesValidator.get().validateStatusCode(200);
            notesValidator.get().validateNoteByIdNotExist(noteId);
        });
    }
}