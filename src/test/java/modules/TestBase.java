package modules;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static common.BrowserFactory.*;
import static common.CommonFunctions.*;

public class TestBase {

    @Parameters({"browser", "url"})
    @BeforeTest
    @Step("Launch the browser and navigate to the Home page")
    public void setup(String browser, String url) {
        setGlobalVariables(browser, url);
        launchBrowser(browser);
        navigateToHomePage();
    }

    @AfterMethod
    public void clear(ITestResult result) throws Exception {
        if (result.isSuccess()) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
            takeScreenShot(result.getInstance().toString());
            saveScreenshot(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES));
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @AfterTest
    @Step("Close the browser")
    public void tearDown() {
        quitDriver();
    }

}
