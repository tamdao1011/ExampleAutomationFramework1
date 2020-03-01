package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static common.BrowserFactory.refreshBrowser;
import static common.CommonFunctions.*;

public class HomePage {

    //Declare and set value for element variables
    protected static WebElement SIGN_IN_FLYOUT;
    protected static WebElement SIGN_IN_FLYOUT_SIGN_IN_BUTTON;
    protected static WebElement SIGN_IN_FLYOUT_START_HERE_LINK;
    protected static WebElement ACCOUNT_LIST_FLYOUT;
    protected static WebElement ACCOUNT_LIST_FLYOUT_SIGN_IN_BUTTON;
    protected static WebElement ACCOUNT_LIST_FLYOUT_START_HERE_LINK;

    public enum signInOrRegister {Register, SignIn}

    public enum flyoutLocation {SignInFlyout, AccountListFlyout}

    private static void setValueForElements() {
        SIGN_IN_FLYOUT = find(By.cssSelector("div.nav-flyout[style*=\"block\"]"));
        SIGN_IN_FLYOUT_SIGN_IN_BUTTON = find(By.cssSelector("a[data-nav-ref=nav_custrec_signin]"));
        SIGN_IN_FLYOUT_START_HERE_LINK = find(By.cssSelector("a[href*=\"nav_custrec_newcust\"]"));
        ACCOUNT_LIST_FLYOUT = find(By.id("nav-link-accountList"));
        ACCOUNT_LIST_FLYOUT_SIGN_IN_BUTTON = find(By.cssSelector("a[data-nav-ref=nav_signin]"));
        ACCOUNT_LIST_FLYOUT_START_HERE_LINK = find(By.cssSelector("a[href*=\"nav_newcust\"]"));
    }

    @Step("Navigate to the Sign In/Register page from the Home page")
    public static void navigateToSignInOrRegisterPageFromHomePage(flyoutLocation flyoutLocation, signInOrRegister signInOrRegister) {
        setValueForElements();
        if (flyoutLocation.equals(HomePage.flyoutLocation.SignInFlyout)) {
            while (!checkElementExist(SIGN_IN_FLYOUT)) {
                refreshBrowser();
            }
            dragMouseAndClick(flyoutLocation, signInOrRegister);
        } else if (flyoutLocation.equals(HomePage.flyoutLocation.AccountListFlyout))
            dragMouseAndClick(flyoutLocation, signInOrRegister);
        else throw new IllegalStateException("Invalid the location of Sign In button: " + flyoutLocation);
    }

    private static void dragMouseAndClick(flyoutLocation flyoutLocation, signInOrRegister signInOrRegister) {
        if (flyoutLocation.equals(HomePage.flyoutLocation.SignInFlyout)) {
            dragMouse(SIGN_IN_FLYOUT);
            if (signInOrRegister.equals(HomePage.signInOrRegister.SignIn))
                click(SIGN_IN_FLYOUT_SIGN_IN_BUTTON);
            else if (signInOrRegister.equals(HomePage.signInOrRegister.Register))
                click(SIGN_IN_FLYOUT_START_HERE_LINK);
            else throw new IllegalStateException("Unexpected value: " + signInOrRegister);
        } else if (flyoutLocation.equals(HomePage.flyoutLocation.AccountListFlyout)) {
            dragMouse(ACCOUNT_LIST_FLYOUT);
            if (signInOrRegister.equals(HomePage.signInOrRegister.SignIn))
                click(ACCOUNT_LIST_FLYOUT_SIGN_IN_BUTTON);
            else if (signInOrRegister.equals(HomePage.signInOrRegister.Register))
                click(ACCOUNT_LIST_FLYOUT_START_HERE_LINK);
            else throw new IllegalStateException("Unexpected value: " + signInOrRegister);
        }
    }

}