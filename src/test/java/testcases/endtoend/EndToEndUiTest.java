package testcases.endtoend;

import com.mynotes.data.enums.NoteCategory;
import com.mynotes.models.dto.Note;
import com.mynotes.models.request.CreateNoteRequestFormParams;
import com.mynotes.models.request.GetTokenRequestFormParams;
import com.mynotes.pageobjects.LoginPage;
import com.mynotes.pageobjects.WelcomePage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import testcases.BaseUiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.mynotes.data.TestData.*;
import static com.mynotes.utils.RandomStringGenerator.generateRandomString;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("UI")
class EndToEndUiTest extends BaseUiTest {

    private ThreadLocal<Note> note;

    @BeforeEach
    void beforeEachTest() {
        note = ThreadLocal.withInitial(Note::new);
        note.set(Note.builder()
                .title(generateRandomString(40))
                .description(generateRandomString(256))
                .category(NoteCategory.PERSONAL)
                .isCompleted(false)
                .build());
    }

    @Tag("P2")
    @Test
    void checkEndToEndEndScenarioWithRemovingNoteOnlyUiApproach() {
        boolean noteExists = Allure.step("Add and delete note using UI", () -> new WelcomePage().openPage()
                .openLoginPage()
                .loginWithEmailAndPassword(TEST_USER_EMAIL, TEST_USER_PASSWORD)
                .addNewNote()
                .createNote(note.get())
                .getNoteCardByCondition(noteCard -> Objects.equals(noteCard.getTitle(), note.get().getTitle()))
                .deleteNote()
                .clickDeleteButton()
                .doesNoteCartExist(noteCard -> Objects.equals(noteCard.getTitle(), note.get().getTitle())));

        Allure.step("Validate that the note does not exist", () -> {
            assertThat(noteExists).isFalse();
        });
    }

    @Tag("P2")
    @Test
    void checkEndToEndEndScenarioWithRemovingNoteOnlyHybridApproach() {
        String token = Allure.step("Get token using API", () -> usersClient.get().getToken(GetTokenRequestFormParams.builder()
                .email(TEST_USER_EMAIL)
                .password(TEST_USER_PASSWORD)
                .build()));

        Allure.step("Create note using API", () -> notesClient.get().createNote(CreateNoteRequestFormParams.builder()
                .title(note.get().getTitle())
                .description(note.get().getDescription())
                .category(note.get().getCategory().getCategoryName())
                .build(), token));

        boolean noteExists = Allure.step("Delete note using UI", () -> new LoginPage()
                .openPage()
                .loginWithToken(token)
                .getNoteCardByCondition(noteCard -> Objects.equals(noteCard.getTitle(), note.get().getTitle()))
                .deleteNote()
                .clickDeleteButton()
                .doesNoteCartExist(noteCard -> Objects.equals(noteCard.getTitle(), note.get().getTitle())));

        Allure.step("Validate that the note does not exist", () -> {
            assertThat(noteExists).isFalse();
        });
    }

    @AfterEach
    public void afterEachTest() {
        closeWebDriver();
    }
}