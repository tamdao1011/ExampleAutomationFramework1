package common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static common.BrowserFactory.*;

public class CommonFunctions {

    //Declare and set value for global variables
    public static String g_browserName;
    public static String g_url;

    public static void setGlobalVariables(String browser, String url) {
        g_browserName = browser;
        g_url = url;
    }

    //Navigate to the Home page
    public static void navigateToHomePage() {
        //Check the exist of the driver, if no, start a new one
        if (driver == null)
            launchBrowser(g_browserName);
        visit(g_url);
    }

    //Convert WebElement type to By type
    public static By toByVal(WebElement we) {
        //Get string of WebElement
        //Split by "->"
        //Delete the "]" character
        //Split by ":"
        String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
        //Return By type
        String locator = data[0];
        String term = data[1];
        switch (locator) {
            case "xpath":
                return By.xpath(term);
            case "css selector":
                return By.cssSelector(term);
            case "id":
                return By.id(term);
            case "tag name":
                return By.tagName(term);
            case "name":
                return By.name(term);
            case "link text":
                return By.linkText(term);
            case "class name":
                return By.className(term);
        }
        return (By) we;
    }

    //Create basic Web Element functions
    public static WebElement find(By by) {
        return driver.findElement(by);
    }

    public static void click(WebElement we) {
        Actions actions = new Actions(driver);
        actions.click(we).perform();
    }

    public static boolean checkElementExist(WebElement we) {
        return driver.findElements(toByVal(we)).size() > 0;
    }

    public static void dragMouse(WebElement target) {
        Actions actions = new Actions(driver);
        actions.moveToElement(target).perform();
    }

    public static String getText(WebElement we) {
        return driver.findElement(toByVal(we)).getText();
    }

    //Create basic verify functions
    @Step("Verify the page title")
    public static boolean verifyPageTitle(String expectedTitle) {
        System.out.println("The expected title: " + expectedTitle);
        System.out.println("The actual title: " + getTitle());
        return getTitle().matches(expectedTitle);
    }

    @Step("Verify the text value")
    public static boolean verifyTextValue(WebElement we, String expectedText) {
        System.out.println("The expected text: " + expectedText);
        System.out.println("The actual text: " + getText(we));
        return getText(we).matches(expectedText);
    }

}
