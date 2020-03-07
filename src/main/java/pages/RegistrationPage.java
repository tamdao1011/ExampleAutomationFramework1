package pages;

import io.qameta.allure.Step;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import static common.CommonFunctions.*;

public class RegistrationPage {

    //Declare and set value for element variables
    public static WebElement YOUR_NAME_TEXTBOX;
    public static WebElement EMAIL_TEXTBOX;
    public static WebElement PASSWORD_TEXTBOX;
    public static WebElement REENTER_PASSWORD_TEXTBOX;
    public static WebElement CREATE_YOUR_AMAZON_ACCOUNT_BUTTON;
    public static WebElement CREATE_ACCOUNT_TEXT = find(By.cssSelector("h1.a-spacing-small"));
    ;
    public static WebElement WARNING_ENTER_YOUR_NAME = find(By.xpath("//div[contains (text(), \"Enter your name\")]"));
    public static WebElement WARNING_ENTER_YOUR_EMAIL = find(By.xpath("//div[contains (text(), \"Enter your email\")]"));
    public static WebElement WARNING_ENTER_YOUR_PASSWORD = find(By.xpath("//div[contains (text(), \"Enter your password\")]"));

    private static void setValueForElements() {
        YOUR_NAME_TEXTBOX = find(By.id("ap_customer_name"));
        EMAIL_TEXTBOX = find(By.id("ap_email"));
        PASSWORD_TEXTBOX = find(By.id("ap_password"));
        REENTER_PASSWORD_TEXTBOX = find(By.id("ap_password_check"));
        CREATE_YOUR_AMAZON_ACCOUNT_BUTTON = find(By.id("continue"));
    }

    public static void createAccount_InputInfo(String yourName, String email, String password) {
        setValueForElements();
        fill(YOUR_NAME_TEXTBOX, yourName);
        fill(EMAIL_TEXTBOX, email);
        fill(PASSWORD_TEXTBOX, password);
        fill(REENTER_PASSWORD_TEXTBOX, password);
        click(CREATE_YOUR_AMAZON_ACCOUNT_BUTTON);
    }

    @Step("Verify the warning messages exist")
    public static void verifyWarningMessageExist(@Nullable String name, @Nullable String email, @Nullable String password) {
        SoftAssert sortAssertion = new SoftAssert();
        if (name != null)
            sortAssertion.assertTrue(checkElementExist(WARNING_ENTER_YOUR_NAME));
        if (email != null)
            sortAssertion.assertTrue(checkElementExist(WARNING_ENTER_YOUR_EMAIL));
        if (password != null)
            sortAssertion.assertTrue(checkElementExist(WARNING_ENTER_YOUR_PASSWORD));
        sortAssertion.assertAll();
    }

}
