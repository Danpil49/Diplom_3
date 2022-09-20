package page_object_model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePageObjectClass{
    private final By nameInputField = By.xpath("//label[text()='Имя']/../input");
    private final By emailInputField = By.xpath("//label[text()='Email']/../input");
    private final By passwordInputField = By.xpath("//label[text()='Пароль']/../input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By logInLink = By.xpath("//a[text() = 'Войти']");

    public RegisterPage(WebDriver driver, int timeoutDurationInSeconds) {
        super(driver, timeoutDurationInSeconds);
    }

    private WebElement getNameInputField() {
        return driver.findElement(nameInputField);
    }

    private WebElement getEmailInputField() {
        return driver.findElement(emailInputField);
    }

    private WebElement getPasswordInputField() {
        return driver.findElement(passwordInputField);
    }

    private WebElement getRegisterButton() {
        return driver.findElement(registerButton);
    }
    private WebElement getLogInLink() {
        return driver.findElement(logInLink);
    }

    @Step("Процедура регистрации нового пользователя")
    public void registerUser(String name, String email, String password) {

        getNameInputField().sendKeys(name);
        getEmailInputField().sendKeys(email);
        getPasswordInputField().sendKeys(password);

        waitForLoadElement(getRegisterButton());
        getRegisterButton().click();
    }

    @Step("Проверка удачной регистрации")
    public Boolean checkRegister(String email, String password) {
        LoginPage login = new LoginPage(driver, 3);
        login.login(email, password);

        return login.checkLogin();
    }
    @Step("Нажатие на кнопку 'Войти'")
    public void pressLogInLink() {
        waitForLoadElement(getLogInLink());
        getLogInLink().click();
    }

}
