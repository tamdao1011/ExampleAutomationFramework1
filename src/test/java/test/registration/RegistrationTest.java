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

public class RegistrationTest extends TestBase {

    private String name = "First Last";
    private String email = "abc@mnb.com";
    private String password = "123456";
    private String emptyValue = "";
    private String nullValue = null;
    private String warningMessage_Name = "Enter your name";
    private String warningMessage_Email = "Enter your email";
    private String warningMessage_Password = "Enter your password";

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF01_Verify the invalid input - all empty")
    @Test
    public void InvalidValues1_AllEmpty() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(emptyValue, emptyValue, emptyValue);
        verifyWarningMessageExist(warningMessage_Name, warningMessage_Email, warningMessage_Password);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF01_Verify the invalid input - all null")
    @Test
    public void InvalidValues2_EmptyName() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(emptyValue, email, password);
        verifyWarningMessageExist(warningMessage_Name, nullValue, nullValue);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF01_Verify the invalid input - all null")
    @Test
    public void InvalidValues3_EmptyEmail() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(name, emptyValue, password);
        verifyWarningMessageExist(nullValue, warningMessage_Email, nullValue);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF01_Verify the invalid input - all null")
    @Test
    public void InvalidValues4_EmptyPassword() {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        createAccount_InputInfo(name, email, emptyValue);
        verifyWarningMessageExist(nullValue, nullValue, warningMessage_Password);
    }

}
