package praktikum.page_object_model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class BasePageObjectClass {
    protected final WebDriver driver;
    protected final int timeoutDurationInSeconds;
    protected final By stellarLogo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/']");
    protected final By constructorButton = By.xpath("//p[text()='Конструктор']/..");
    protected final By accountProfileButton = By.xpath("//p[text()='Личный Кабинет']/..");

    protected BasePageObjectClass(WebDriver driver, int timeoutDurationInSeconds) {
        this.driver = driver;
        this.timeoutDurationInSeconds = timeoutDurationInSeconds;
    }

    protected void waitForLoadElement(WebElement waitElement) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutDurationInSeconds)).until(ExpectedConditions.visibilityOf(waitElement));
    }

    protected void waitForRedirect(String urlContains) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(urlContains));
    }

    protected WebElement getStellarLogo() {
        return driver.findElement(stellarLogo);
    }

    protected WebElement getConstructorButton() {
        return driver.findElement(constructorButton);
    }

    protected WebElement getAccountProfileButton() {return driver.findElement(accountProfileButton);}

    @Step("Нажатие на кнопку 'Личный Кабинет'")
    public void pressAccountProfileButton() {
        waitForLoadElement(getAccountProfileButton());
        getAccountProfileButton().click();
    }

    @Step("Нажатие на логотип 'Stellar burger'")
    public void pressStellarLogo() {
        waitForLoadElement(getStellarLogo());
        getStellarLogo().click();
    }

    @Step("Нажатие на кнопку 'Конструктор'")
    public void pressConstructorButton() {
        waitForLoadElement(getConstructorButton());
        getConstructorButton().click();
    }
}
