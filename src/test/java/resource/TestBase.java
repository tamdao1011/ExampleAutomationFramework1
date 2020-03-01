package resource;

import io.qameta.allure.Step;
import org.testng.annotations.*;

import static common.BrowserFactory.closeBrowser;
import static common.CommonFunctions.*;

public class TestBase {

    @Parameters({"browser", "url"})
    @BeforeMethod
    @Step("Navigate to the Home page")
    public void setup(String browser, String url) {
        setGlobalVariables(browser, url);
        navigateToHomePage();
    }

    @AfterSuite
    @Step("Close the browser")
    public void tearDown() {
        closeBrowser();
    }

}
