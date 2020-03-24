package common;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static common.CommonFunctions.timeInSeconds;

public class BrowserFactory {

    public static WebDriver driver;

    private enum browserName {chrome, firefox, ie, edge, safari}

    //Create basic browser functions
    public static void launchBrowser(@NotNull String browserName) {
        if (browserName.equalsIgnoreCase(String.valueOf(BrowserFactory.browserName.chrome))) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase(String.valueOf(BrowserFactory.browserName.firefox))) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase(String.valueOf(BrowserFactory.browserName.ie))) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browserName.equalsIgnoreCase(String.valueOf(BrowserFactory.browserName.edge))) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase(String.valueOf(BrowserFactory.browserName.safari))) {
            driver = new SafariDriver();
        } else {
            System.err.println("Browser " + browserName + " is not defined");
        }
        driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(timeInSeconds, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void visit(String url) {
        driver.get(url);
    }

    public static void waitForPageLoadComplete() {
        new WebDriverWait(driver, timeInSeconds).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static void refreshBrowser() {
        driver.navigate().refresh();
    }

    public static String getTitle() {
        return driver.getTitle();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
