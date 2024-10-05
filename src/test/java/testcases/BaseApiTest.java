package testcases;

import com.mynotes.client.NotesClient;
import com.mynotes.client.UsersClient;
import com.mynotes.models.request.GetTokenRequestFormParams;
import com.mynotes.validator.NotesValidator;
import com.mynotes.validator.UsersValidator;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static com.mynotes.data.TestData.TEST_USER_EMAIL;
import static com.mynotes.data.TestData.TEST_USER_PASSWORD;

public class BaseApiTest {

    private static final ThreadLocal<Logger> logger = ThreadLocal.withInitial(() -> LogManager.getLogger("ASYNC_CONSOLE_APPENDER"));

    protected static ThreadLocal<UsersClient> usersClient;
    protected static ThreadLocal<NotesClient> notesClient;
    protected static ThreadLocal<UsersValidator> usersValidator;
    protected static ThreadLocal<NotesValidator> notesValidator;
    protected static ThreadLocal<String> token = ThreadLocal.withInitial(String::new);

    @BeforeAll
    static void setUp() {
        usersClient = ThreadLocal.withInitial(UsersClient::new);
        notesClient = ThreadLocal.withInitial(NotesClient::new);
        usersValidator = ThreadLocal.withInitial(() -> new UsersValidator(usersClient.get()));
        notesValidator = ThreadLocal.withInitial(() -> new NotesValidator(notesClient.get()));

        step("Get token");
        token.set(usersClient.get().getToken(GetTokenRequestFormParams.builder()
                .email(TEST_USER_EMAIL)
                .password(TEST_USER_PASSWORD)
                .build()));
    }

    @BeforeEach
    void beforeEachTest(TestInfo testInfo) {
        step("Starting test:" + testInfo.getDisplayName());
    }

    @AfterEach
    void afterEachTest(TestInfo testInfo) {
        Allure.step("Cleanup: Delete all notes", () -> {
            notesClient.get().getNotes(token.get());
            notesClient.get().getResponse().jsonPath().getList("data.id").forEach(noteId -> {
                notesClient.get().deleteNote(noteId.toString(), token.get());
                notesValidator.get().validateStatusCode(200);
            });
        });

        step("Finishing test:" + testInfo.getDisplayName());
    }

    protected static void step(String message) {
        logger.get().info(message);
    }
}
