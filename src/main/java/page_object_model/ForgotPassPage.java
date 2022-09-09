package page_object_model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPassPage extends BasePageObjectClass{
    private final By logInLink = By.xpath("//a[text() = 'Войти']");

    public ForgotPassPage(WebDriver driver, int timeoutDurationInSeconds) {
        super(driver, timeoutDurationInSeconds);
    }

    private WebElement getLogInLink() {
        return driver.findElement(logInLink);
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void pressLogInLink() {
        waitForLoadElement(getLogInLink());
        getLogInLink().click();
    }
}
