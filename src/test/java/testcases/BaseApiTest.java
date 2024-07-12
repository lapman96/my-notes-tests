package testcases;

import com.mynotes.client.NotesClient;
import com.mynotes.client.UsersClient;
import com.mynotes.models.request.GetTokenRequestFormParams;
import com.mynotes.validator.NotesValidator;
import com.mynotes.validator.UsersValidator;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mynotes.data.TestData.TEST_USER_EMAIL;
import static com.mynotes.data.TestData.TEST_USER_PASSWORD;

public class BaseApiTest {

    private static final ThreadLocal<Logger> logger = ThreadLocal.withInitial(() -> LoggerFactory.getLogger(BaseApiTest.class));

    public static void step(String message) {
        logger.get().info(message);
    }

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
}
