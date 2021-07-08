package tests.base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import steps.LoginSteps;
import utils.PropertyReader;
import utils.TestListener;

@Listeners(TestListener.class)
public class BaseTest {

    public static String USER, PASSWORD;
    protected LoginSteps loginSteps;


    @BeforeClass(description = "Open browser")
    public void setup() {
        Configuration.baseUrl = System.getenv().getOrDefault("QASE_URL", PropertyReader.getProperty("qase.url"));
        USER = utils.PropertyReader.getProperty("QASE_USER", "qase.user");
        PASSWORD = utils.PropertyReader.getProperty("QASE_PASSWORD", "qase.password");
        Configuration.browser = "chrome";
        Configuration.clickViaJs = true;
        Configuration.headless = false;
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
//        Configuration.holdBrowserOpen = true;
        loginSteps = new LoginSteps();
    }

    @AfterClass(alwaysRun = true, description = "Close browser")
    public void tearDown() {
//        getWebDriver().quit();
    }
}
