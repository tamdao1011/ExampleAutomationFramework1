package test.homepage;

import resource.TestBase;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;
import pages.SignInPage_Account;

import static pages.HomePage.navigateToSignInOrRegisterPageFromHomePage;

public class AccountListFlyoutTest extends TestBase {

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the Account List fly out")
    @Description("SI03_Verify the Sign In button on the Account List fly out")
    @Test
    public void verifySignInButtonOnAccountListFlyout() {
        navigateToSignInOrRegisterPageFromHomePage(HomePage.flyoutLocation.AccountListFlyout, HomePage.signInOrRegister.SignIn);
        SignInFlyoutTest.verifySignInFlyout("Amazon Sign-In", SignInPage_Account.SIGN_IN_TEXT, "Sign-In");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the Account List fly out")
    @Description("SI04_Verify the Start here link on the Account List fly out")
    @Test
    public void verifyStartHereLinkOnAccountListFlyout() {
        navigateToSignInOrRegisterPageFromHomePage(HomePage.flyoutLocation.AccountListFlyout, HomePage.signInOrRegister.Register);
        SignInFlyoutTest.verifySignInFlyout("Amazon Registration", RegistrationPage.CREATE_ACCOUNT_TEXT, "Create account");
    }

}
