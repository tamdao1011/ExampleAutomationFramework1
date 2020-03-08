package pages;

import io.qameta.allure.Step;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static common.BrowserFactory.refreshBrowser;
import static common.BrowserFactory.waitForPageLoadComplete;
import static common.CommonFunctions.*;

public class HomePage {

    //Declare and set value for element variables
    private static WebElement SIGN_IN_FLYOUT;
    private static WebElement SIGN_IN_FLYOUT_SIGN_IN_BUTTON;
    private static WebElement SIGN_IN_FLYOUT_START_HERE_LINK;
    private static WebElement ACCOUNT_LIST_FLYOUT_SIGN_IN_BUTTON;
    private static WebElement ACCOUNT_LIST_FLYOUT_START_HERE_LINK;
    private static WebElement ACCOUNT_AND_LISTS_ITEM;
    private static WebElement RETURNS_AND_ORDERS_ITEM;
    private static WebElement SIGN_IN_SECURELY_BUTTON;
    private static WebElement SIGN_IN_PERSONALIZED_BUTTON;
    private static WebElement MANAGE_YOUR_CONTENT_DEVICES_FOOTER_ITEM;
    private static WebElement YOUR_ORDERS_FOOTER_ITEM;
    private static WebElement RETURNS_REPLACEMENTS_FOOTER_ITEM;

    public enum signInOrRegister {Register, SignIn}

    public enum location {
        SignInFlyout,
        AccountListFlyout,
        ReturnsAndOrdersItem,
        SignInSecurelyButton,
        SignInPersonalizedButton,
        YourOrdersFooterItem,
        ReturnsReplacementsFooterItem,
        ManageYourContentDevicesFooterItem
    }

    private static void setValueForElements() {
        SIGN_IN_FLYOUT = find(By.cssSelector("div.nav-flyout[style*=\"block\"]"));
        SIGN_IN_FLYOUT_SIGN_IN_BUTTON = find(By.cssSelector("a[data-nav-ref=nav_custrec_signin]"));
        SIGN_IN_FLYOUT_START_HERE_LINK = find(By.cssSelector("a[href*=\"nav_custrec_newcust\"]"));
        ACCOUNT_LIST_FLYOUT_SIGN_IN_BUTTON = find(By.cssSelector("a[data-nav-ref=nav_signin]"));
        ACCOUNT_LIST_FLYOUT_START_HERE_LINK = find(By.cssSelector("a[href*=\"nav_newcust\"]"));
        ACCOUNT_AND_LISTS_ITEM = find(By.id("nav-link-accountList"));
        RETURNS_AND_ORDERS_ITEM = find(By.id("nav-orders"));
        SIGN_IN_SECURELY_BUTTON = find(By.id("a-autoid-0-announce"));
        SIGN_IN_PERSONALIZED_BUTTON = find(By.partialLinkText("personalized"));
        YOUR_ORDERS_FOOTER_ITEM = find(By.xpath("//a[contains(text(),\"Your Orders\")]"));
        RETURNS_REPLACEMENTS_FOOTER_ITEM = find(By.xpath("//a[contains(text(),\"Returns & Replacements\")]"));
        MANAGE_YOUR_CONTENT_DEVICES_FOOTER_ITEM = find(By.xpath("//a[contains(text(),\"Manage Your Content and Devices\")]"));
    }

    @Step("Navigate to the Sign In/Registration page from the Home page")
    public static void navigateToSignInOrRegistrationPageFromHomePage(location location, @Nullable signInOrRegister signInOrRegister) {
        if (!checkHomePageExist())
            navigateToHomePage();
        waitForAllElementsLoaded();
        setValueForElements();

        switch (location) {
            case SignInFlyout:
                while (!checkElementDisplay(SIGN_IN_FLYOUT))
                    refreshBrowser();
                setValueForElements();
                scrollDragMouseAndClick(location, signInOrRegister);
                break;
            case AccountListFlyout:
                scrollDragMouseAndClick(location, signInOrRegister);
                break;
            case ReturnsAndOrdersItem:
                scrollAndClick(RETURNS_AND_ORDERS_ITEM);
                break;
            case SignInSecurelyButton:
                scrollAndClick(SIGN_IN_SECURELY_BUTTON);
                break;
            case SignInPersonalizedButton:
                scrollAndClick(SIGN_IN_PERSONALIZED_BUTTON);
                break;
            case ManageYourContentDevicesFooterItem:
                scrollAndClick(MANAGE_YOUR_CONTENT_DEVICES_FOOTER_ITEM);
                break;
            case YourOrdersFooterItem:
                scrollAndClick(YOUR_ORDERS_FOOTER_ITEM);
                break;
            case ReturnsReplacementsFooterItem:
                scrollAndClick(RETURNS_REPLACEMENTS_FOOTER_ITEM);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + location);
        }
    }

    private static void waitForAllElementsLoaded() {
        //Wait and press End key to load all elements
        waitForPageLoadComplete();
        pressKey(Keys.END);
    }

    private static boolean checkHomePageExist() {
        return verifyPageTitle("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
    }

    private static void scrollDragMouseAndClick(location location, signInOrRegister signInOrRegister) {
        if (location.equals(HomePage.location.SignInFlyout)) {
            scrollHorizontally(SIGN_IN_FLYOUT);
            dragMouse(SIGN_IN_FLYOUT);
            if (signInOrRegister.equals(HomePage.signInOrRegister.SignIn))
                click(SIGN_IN_FLYOUT_SIGN_IN_BUTTON);
            else if (signInOrRegister.equals(HomePage.signInOrRegister.Register))
                click(SIGN_IN_FLYOUT_START_HERE_LINK);
            else throw new IllegalStateException("Unexpected value: " + signInOrRegister);
        } else if (location.equals(HomePage.location.AccountListFlyout)) {
            scrollHorizontally(ACCOUNT_AND_LISTS_ITEM);
            dragMouse(ACCOUNT_AND_LISTS_ITEM);
            if (signInOrRegister.equals(HomePage.signInOrRegister.SignIn))
                click(ACCOUNT_LIST_FLYOUT_SIGN_IN_BUTTON);
            else if (signInOrRegister.equals(HomePage.signInOrRegister.Register))
                click(ACCOUNT_LIST_FLYOUT_START_HERE_LINK);
            else throw new IllegalStateException("Unexpected value: " + signInOrRegister);
        }
    }

}