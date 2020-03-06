package test.homepage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;
import resource.TestBase;

import static pages.HomePage.navigateToSignInOrRegistrationPageFromHomePage;
import static test.homepage.NavigateToSignInPageTest.verifySignInFlyout;

public class NavigateToRegistrationPageTest extends TestBase {

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the navigation to the Registration page")
    @Description("RE01_Verify the Registration page opens after clicking on the Start Here link of the Sign In fly out")
    @Test
    public void verifyRegistrationPageOpen1_StartHereLinkOnSignInFlyout() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.SignInFlyout, HomePage.signInOrRegister.Register);
        verifySignInFlyout("Amazon Registration", RegistrationPage.CREATE_ACCOUNT_TEXT, "Create account");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Verify the navigation to the Registration page")
    @Description("RE02_Verify the Registration page opens after clicking on the Start Here link of the Account List fly out")
    @Test
    public void verifyRegistrationPageOpen2_StartHereLinkOnAccountListFlyout() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        verifySignInFlyout("Amazon Registration", RegistrationPage.CREATE_ACCOUNT_TEXT, "Create account");
    }

}
