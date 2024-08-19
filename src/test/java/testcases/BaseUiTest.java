package testcases;

import com.codeborne.selenide.Configuration;
import com.mynotes.client.NotesClient;
import com.mynotes.client.UsersClient;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.mynotes.data.PropertiesManager.IS_REMOTE_RUN;
import static com.mynotes.data.PropertiesManager.TEST_BROWSER;

public abstract class BaseUiTest {
    protected static ThreadLocal<UsersClient> usersClient;
    protected static ThreadLocal<NotesClient> notesClient;

    @BeforeAll
    public static void setUp(){
        if ("true".equals(IS_REMOTE_RUN)) {
            configureRemoteTestRun();
        }

        Configuration.browser = TEST_BROWSER;
        Configuration.timeout = 10000;
        Configuration.screenshots = true;
        Configuration.fastSetValue = false;
        Configuration.headless = true;
        Configuration.reopenBrowserOnFail = true;
    }

    private static void configureRemoteTestRun() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.WIN11);
        Configuration.browserCapabilities = desiredCapabilities;
    }
}
