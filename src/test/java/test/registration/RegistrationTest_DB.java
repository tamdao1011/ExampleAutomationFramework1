package test.registration;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.HomePage;
import test.TestBase;

import static database.SQLConnector.query;
import static pages.HomePage.navigateToSignInOrRegistrationPageFromHomePage;
import static pages.RegistrationPage.*;

public class RegistrationTest_DB extends TestBase {

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF01_Verify the invalid input - all empty")
    @Test
    public void InvalidValues1_AllEmpty() {
        createAccountFromDB("InvalidValues1_AllEmpty");
        verifyWarningMessageElementDisplay(WARNING_EMPTY_YOUR_NAME);
        verifyWarningMessageElementDisplay(WARNING_EMPTY_EMAIL);
        verifyWarningMessageElementDisplay(WARNING_EMPTY_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF02_Verify the invalid input - empty name")
    @Test
    public void InvalidValues2_EmptyName() {
        createAccountFromDB("InvalidValues2_EmptyName");
        verifyWarningMessageElementDisplay(WARNING_EMPTY_YOUR_NAME);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF03_Verify the invalid input - empty email")
    @Test
    public void InvalidValues3_EmptyEmail() {
        createAccountFromDB("InvalidValues3_EmptyEmail");
        verifyWarningMessageElementDisplay(WARNING_EMPTY_EMAIL);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF04_Verify the invalid input - empty password")
    @Test
    public void InvalidValues4_EmptyPassword() {
        createAccountFromDB("InvalidValues4_EmptyPassword");
        verifyWarningMessageElementDisplay(WARNING_EMPTY_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF05_Verify the invalid input - empty re-enter password")
    @Test
    public void InvalidValues5_EmptyReenterPassword() {
        createAccountFromDB("InvalidValues5_EmptyReenterPassword");
        verifyWarningMessageElementDisplay(WARNING_EMPTY_REENTER_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF06_Verify the invalid input - not match password")
    @Test
    public void InvalidValues6_NotMatchPassword() {
        createAccountFromDB("InvalidValues6_NotMatchPassword");
        verifyWarningMessageElementDisplay(WARNING_NOT_MATCH_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF07_Verify the invalid input - invalid email")
    @Test
    public void InvalidValues7_InvalidEmail() {
        createAccountFromDB("InvalidValues7_InvalidEmail");
        verifyWarningMessageElementDisplay(WARNING_INVALID_EMAIL);
    }

    private void createAccountFromDB(String valueType) {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        String[] valueArray = query(valueType);
        for (int i = 0; i < valueArray.length; i++) {
            if (valueArray[i] == null)
                valueArray[i] = "";
        }
        createAccount_InputInfo(valueArray[0], valueArray[1], valueArray[2], valueArray[3]);
    }
}
