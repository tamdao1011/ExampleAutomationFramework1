package test.homepage;

import resource.TestBase;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.RegistrationPage;
import pages.SignInPage_Account;

import static common.CommonFunctions.*;
import static pages.HomePage.navigateToSignInOrRegisterPageFromHomePage;

public class SignInFlyoutTest extends TestBase {

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the Sign In fly out")
    @Description("SI01_Verify the Sign In button on the Sign In fly out after navigating for the first time")
    @Test
    public void verifySignInButtonOnSignInFlyout() {
        navigateToSignInOrRegisterPageFromHomePage(HomePage.flyoutLocation.SignInFlyout, HomePage.signInOrRegister.SignIn);
        verifySignInFlyout("Amazon Sign-In", SignInPage_Account.SIGN_IN_TEXT, "Sign-In");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the Sign In fly out")
    @Description("SI02_Verify the Start here link on the Sign In fly out after navigating for the first time")
    @Test
    public void verifyStartHereLinkOnSignInFlyout() {
        navigateToSignInOrRegisterPageFromHomePage(HomePage.flyoutLocation.SignInFlyout, HomePage.signInOrRegister.Register);
        verifySignInFlyout("Amazon Registration", RegistrationPage.CREATE_ACCOUNT_TEXT, "Create account");
    }

    @Step("Check the page title and the text exist")
    public static void verifySignInFlyout(String expectedTitle, WebElement textEle, String expectedText) {
        SoftAssert sortAssertion = new SoftAssert();
        sortAssertion.assertTrue(verifyTextValue(textEle, expectedText), "Wrong the text value");
        sortAssertion.assertTrue(verifyPageTitle(expectedTitle), "Wrong the page title");
        sortAssertion.assertAll();
    }

}
