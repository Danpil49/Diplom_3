package page_object_model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePageObjectClass{
    private final By emailInputField = By.xpath("//input[@name='name']");
    private final By passwordInputField = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver, int timeoutDurationInSeconds) {
        super(driver, timeoutDurationInSeconds);
    }

    private WebElement getEmailInputField() {
        return driver.findElement(emailInputField);
    }

    private WebElement getPasswordInputField() {
        return driver.findElement(passwordInputField);
    }

    private WebElement getLoginButton() {
        return driver.findElement(loginButton);
    }

    @Step("Процедура логина на соответствующей форме")
    public void login(String email, String password) {
        waitForRedirect("/login");
        waitForLoadElement(getEmailInputField());

        getEmailInputField().sendKeys(email);
        getPasswordInputField().sendKeys(password);

        getLoginButton().click();
    }

    @Step("Проверка удачного логина")
    public Boolean checkLogin() {
        pressStellarLogo();
        HomePage home = new HomePage(driver, 3);

        try {
            return home.getPlaceAnOrderButton().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
