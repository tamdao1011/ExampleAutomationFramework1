package modules.homepage;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage_Account;
import modules.TestBase;

import static common.CommonFunctions.*;
import static pages.HomePage.navigateToSignInOrRegistrationPageFromHomePage;

public class NavigateToSignInPageTest extends TestBase {

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the navigation to Sign In page")
    @Description("SI01_Verify the Sign In page opens after clicking on the Sign In button of the Sign In fly out")
    @Test
    public void verifySignInPageOpen1_SignInButtonOnSignInFlyout() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.SignInFlyout, HomePage.signInOrRegister.SignIn);
        verifyPageDisplay("Amazon Sign-In", SignInPage_Account.SIGN_IN_TEXT, "Sign-In");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the navigation to Sign In page")
    @Description("SI02_Verify the Sign In page opens after clicking on the Sign In button of the Account List fly out")
    @Test
    public void verifySignInPageOpen2_SignInButtonOnAccountListFlyout() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.SignIn);
        verifyPageDisplay("Amazon Sign-In", SignInPage_Account.SIGN_IN_TEXT, "Sign-In");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the navigation to Sign In page")
    @Description("SI03_Verify the Sign In page opens after clicking on the Returns & Orders item")
    @Test
    public void verifySignInPageOpen3_ReturnsAndOrdersItem() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.ReturnsAndOrdersItem, null);
        verifyPageDisplay("Amazon Sign-In", SignInPage_Account.SIGN_IN_TEXT, "Sign-In");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the navigation to Sign In page")
    @Description("SI04_Verify the Sign In page opens after clicking on the Sign In Securely button")
    @Test
    public void verifySignInPageOpen4_SignInSecurelyButton() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.SignInSecurelyButton, null);
        verifyPageDisplay("Amazon Sign-In", SignInPage_Account.SIGN_IN_TEXT, "Sign-In");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the navigation to Sign In page")
    @Description("SI05_Verify the Sign In page opens after clicking on the Sign In To See Personalized Recommendations button")
    @Test
    public void verifySignInPageOpen5_SignInPersonalizedButton() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.SignInPersonalizedButton, null);
        verifyPageDisplay("Amazon Sign-In", SignInPage_Account.SIGN_IN_TEXT, "Sign-In");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the navigation to Sign In page")
    @Description("SI06_Verify the Sign In page opens after clicking on the Your Orders footer item")
    @Test
    public void verifySignInPageOpen6_YourOrdersFooterItem() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.YourOrdersFooterItem, null);
        verifyPageDisplay("Amazon Sign-In", SignInPage_Account.SIGN_IN_TEXT, "Sign-In");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the navigation to Sign In page")
    @Description("SI07_Verify the Sign In page opens after clicking on the Returns and Replacements footer item")
    @Test
    public void verifySignInPageOpen7_ReturnsReplacementsFooterItem() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.ReturnsReplacementsFooterItem, null);
        verifyPageDisplay("Amazon Sign-In", SignInPage_Account.SIGN_IN_TEXT, "Sign-In");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the navigation to Sign In page")
    @Description("SI08_Verify the Sign In page opens after clicking on the Manage Your Content and Devices footer item")
    @Test
    public void verifySignInPageOpen8_ManageYourContentDevicesFooterItem() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.ManageYourContentDevicesFooterItem, null);
        verifyPageDisplay("Amazon Sign-In", SignInPage_Account.SIGN_IN_TEXT, "Sign-In");
    }

}
