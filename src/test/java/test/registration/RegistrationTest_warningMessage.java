package test.registration;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.HomePage;
import resource.TestBase;

import static pages.HomePage.navigateToSignInOrRegistrationPageFromHomePage;
import static pages.RegistrationPage.*;

public class RegistrationTest_warningMessage extends TestBase {

    private String name = "First Last";
    private String email = "abc@mnb.com";
    private String password = "123456";
    private String emptyValue = "";

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF01_Verify the invalid input - all empty")
    @Test
    public void InvalidValues1_AllEmpty() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(emptyValue, emptyValue, emptyValue);
        verifyWarningMessageElementExist(WARNING_EMPTY_YOUR_NAME);
        verifyWarningMessageElementExist(WARNING_EMPTY_EMAIL);
        verifyWarningMessageElementExist(WARNING_EMPTY_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF02_Verify the invalid input - empty name")
    @Test
    public void InvalidValues2_EmptyName() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(emptyValue, email, password);
        verifyWarningMessageElementExist(WARNING_EMPTY_YOUR_NAME);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF03_Verify the invalid input - empty email")
    @Test
    public void InvalidValues3_EmptyEmail() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(name, emptyValue, password);
        verifyWarningMessageElementExist(WARNING_EMPTY_EMAIL);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF04_Verify the invalid input - empty password")
    @Test
    public void InvalidValues4_EmptyPassword() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(name, email, emptyValue);
        verifyWarningMessageElementExist(WARNING_EMPTY_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF05_Verify the invalid input - empty re-enter password")
    @Test
    public void InvalidValues5_EmptyReenterPassword() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(name, email, emptyValue);
        verifyWarningMessageElementExist(WARNING_EMPTY_REENTER_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF06_Verify the invalid input - not match password")
    @Test
    public void InvalidValues6_NotMatchPassword() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(name, email, emptyValue);
        verifyWarningMessageElementExist(WARNING_NOT_MATCH_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF07_Verify the invalid input - invalid email")
    @Test
    public void InvalidValues7_InvalidEmail() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(name, email, emptyValue);
        verifyWarningMessageElementExist(WARNING_INVALID_EMAIL);
    }

}
