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

    public static WebElement WARNING_EMPTY_YOUR_NAME = find(By.xpath("//div[contains (text(), \"Enter your name\")]"));
    public static WebElement WARNING_EMPTY_EMAIL = find(By.xpath("//div[contains (text(), \"Enter your email\")]"));
    public static WebElement WARNING_EMPTY_PASSWORD = find(By.xpath("//div[contains (text(), \"Enter your password\")]"));
    public static WebElement WARNING_EMPTY_REENTER_PASSWORD = find(By.xpath("//div[contains (text(), \"Type your password again\")]"));
    public static WebElement WARNING_NOT_MATCH_PASSWORD = find(By.xpath("//div[contains (text(), \"Passwords must match\")]"));
    public static WebElement WARNING_INVALID_EMAIL = find(By.xpath("//div[contains (text(), \"Enter a valid email address\")]"));

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
    public static void verifyWarningMessageElementExist(WebElement warningElement) {
        SoftAssert sortAssertion = new SoftAssert();
        sortAssertion.assertTrue(checkElementExist(warningElement));
        sortAssertion.assertAll();
    }

}
