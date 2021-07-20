package tests.base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;
import steps.*;
import utils.PropertyReader;
import utils.TestListener;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {

    public static String USER = utils.PropertyReader.getProperty("QASE_USER", "qase.user");
    public static String PASSWORD = utils.PropertyReader.getProperty("QASE_PASSWORD", "qase.password");
    public static String HOME_URL;

    protected StartSteps startSteps;
    protected LoginSteps loginSteps;
    protected ProjectsSteps projectsSteps;
    protected SuiteSteps suiteSteps;
    protected CaseSteps caseSteps;
    protected TestPlanSteps testPlanSteps;
    protected TestRunSteps testRunSteps;

    @BeforeTest(description = "Open browser")
    public void setup() {
        Configuration.baseUrl = System.getenv().getOrDefault("QASE_URL", PropertyReader.getProperty("qase.url"));
//        USER = utils.PropertyReader.getProperty("QASE_USER", "qase.user");
//        PASSWORD = utils.PropertyReader.getProperty("QASE_PASSWORD", "qase.password");
        HOME_URL = System.getenv().getOrDefault("QASE_HOME_URL", PropertyReader.getProperty("qase.homeUrl"));
        Configuration.browser = "chrome";
        Configuration.clickViaJs = false;
        Configuration.headless = false;
        Configuration.startMaximized = true;
        Configuration.timeout = 30000;
//        Configuration.holdBrowserOpen = true;
        loginSteps = new LoginSteps();
        projectsSteps = new ProjectsSteps();
        startSteps = new StartSteps();
        suiteSteps = new SuiteSteps();
        caseSteps = new CaseSteps();
        testPlanSteps = new TestPlanSteps();
        testRunSteps = new TestRunSteps();
    }

    @AfterTest(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        getWebDriver().quit();
    }
}
