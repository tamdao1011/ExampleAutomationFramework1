package modules.registration;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import core.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;

import static excel.ReadExcel.getDataFromExcel;
import static pages.HomePage.navigateToSignInOrRegistrationPageFromHomePage;
import static pages.RegistrationPage.*;

public class RegistrationTest_Excel extends TestBase {

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF01_Verify the invalid input - all empty")
    @Test
    public void InvalidValues1_AllEmpty() {
        createAccountFromExcel("InvalidValues1_AllEmpty");
        verifyWarningMessageElementDisplay(WARNING_EMPTY_YOUR_NAME);
        verifyWarningMessageElementDisplay(WARNING_EMPTY_EMAIL);
        verifyWarningMessageElementDisplay(WARNING_EMPTY_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF02_Verify the invalid input - empty name")
    @Test
    public void InvalidValues2_EmptyName() {
        createAccountFromExcel("InvalidValues2_EmptyName");
        verifyWarningMessageElementDisplay(WARNING_EMPTY_YOUR_NAME);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF03_Verify the invalid input - empty email")
    @Test
    public void InvalidValues3_EmptyEmail() {
        createAccountFromExcel("InvalidValues3_EmptyEmail");
        verifyWarningMessageElementDisplay(WARNING_EMPTY_EMAIL);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF04_Verify the invalid input - empty password")
    @Test
    public void InvalidValues4_EmptyPassword() {
        createAccountFromExcel("InvalidValues4_EmptyPassword");
        verifyWarningMessageElementDisplay(WARNING_EMPTY_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF05_Verify the invalid input - empty re-enter password")
    @Test
    public void InvalidValues5_EmptyReenterPassword() {
        createAccountFromExcel("InvalidValues5_EmptyReenterPassword");
        verifyWarningMessageElementDisplay(WARNING_EMPTY_REENTER_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF06_Verify the invalid input - not match password")
    @Test
    public void InvalidValues6_NotMatchPassword() {
        createAccountFromExcel("InvalidValues6_NotMatchPassword");
        verifyWarningMessageElementDisplay(WARNING_NOT_MATCH_PASSWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Verify the Registration function")
    @Description("RF07_Verify the invalid input - invalid email")
    @Test
    public void InvalidValues7_InvalidEmail() {
        createAccountFromExcel("InvalidValues7_InvalidEmail");
        verifyWarningMessageElementDisplay(WARNING_INVALID_EMAIL);
    }

    private void createAccountFromExcel(String valueType) {
        navigateToSignInOrRegistrationPageFromHomePage(HomePage.location.AccountListFlyout, HomePage.signInOrRegister.Register);
        String[] valueArray = getDataFromExcel(valueType);
        createAccount_InputInfo(valueArray[0], valueArray[1], valueArray[2], valueArray[3]);
    }

}
