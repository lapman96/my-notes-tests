package testcases.notes;

import com.mynotes.models.request.CreateNoteRequestFormParams;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import testcases.BaseApiTest;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("Notes")
class CreateNewNoteTest extends BaseApiTest {

    @ParameterizedTest(name = "Title: \"{0}\", Description: \"{1}\", Category: \"{2}\"")
    @Tag("P1")
    @Feature("Create Note")
    @Story("Valid cases")
    @DisplayName("Check the ability to create a new note")
    @CsvFileSource(resources = "/testdata/csv/validNotes.csv", numLinesToSkip = 1, delimiter = '|')
    @Severity(CRITICAL)
    void checkTheAbilityToCreateNewNote(String title, String description, String category) {
        step("Create a note and get its ID");
        CreateNoteRequestFormParams requestFormParams = CreateNoteRequestFormParams.builder()
                .title(title)
                .description(description)
                .category(category)
                .build();
        notesClient.get().createNote(requestFormParams, token.get());
        String noteId = (notesClient.get().getResponseValueByPath("data.id"));

        step("Get note by ID and validate it");
        notesClient.get().getNote(noteId, token.get());
        notesValidator.get().validateStatusCode(200);
        assertThat(notesClient.get().getResponseValueByPath("data.id")).isEqualTo(noteId);
    }

    @ParameterizedTest(name = "Invalid title: \"{0}\"")
    @Tag("P1")
    @Feature("Create Note")
    @Story("Invalid cases")
    @DisplayName("Check the ability to create a new note with invalid title")
    @CsvFileSource(resources = "/testdata/csv/invalidTitles.csv")
    @Severity(NORMAL)
    void checkTheAbilityToCreateNewNoteWithInvalidTitle(String invalidTitle) {
        step("Create a note with invalid title");
        CreateNoteRequestFormParams requestFormParams = CreateNoteRequestFormParams.builder()
                .title(invalidTitle)
                .description("New description")
                .category("Home")
                .build();

        notesClient.get().createNote(requestFormParams, token.get());

        step("Validate response");
        notesValidator.get().validateStatusCode(400)
                .validateValueByJsonPath("success", "false")
                .validateValueByJsonPath("status", "400")
                .validateValueByJsonPath("message", "Title must be between 4 and 100 characters");
    }

    @ParameterizedTest(name = "Invalid description: \"{0}\"")
    @Tag("P1")
    @Feature("Create Note")
    @Story("Invalid cases")
    @DisplayName("Check the ability to create a new note with invalid description")
    @CsvFileSource(resources = "/testdata/csv/invalidDescriptions.csv")
    @Severity(NORMAL)
    void checkTheAbilityToCreateNewNoteWithInvalidDescription(String invalidDescription) {
        step("Create a note with invalid title");
        CreateNoteRequestFormParams requestFormParams = CreateNoteRequestFormParams.builder()
                .title("New title")
                .description(invalidDescription)
                .category("Home")
                .build();

        notesClient.get().createNote(requestFormParams, token.get());

        step("Validate response");
        notesValidator.get().validateStatusCode(400)
                .validateValueByJsonPath("success", "false")
                .validateValueByJsonPath("status", "400")
                .validateValueByJsonPath("message", "Description must be between 4 and 1000 characters");
    }
}