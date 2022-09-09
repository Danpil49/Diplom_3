package page_object_model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountProfile extends BasePageObjectClass{
    private final By profileButton = By.xpath("//a[text()='Профиль']");
    private final By orderHistoryButton = By.xpath("//a[text()='История заказов']");
    private final By signOutButton = By.xpath("//button[text()='Выход']");

    public AccountProfile(WebDriver driver, int timeoutDurationInSeconds) {
        super(driver, timeoutDurationInSeconds);
    }

    private WebElement getProfileButton() {
        return driver.findElement(profileButton);
    }

    private WebElement getOrderHistoryButton() {
        return driver.findElement(orderHistoryButton);
    }

    private WebElement getSignOutButton() {
        return driver.findElement(signOutButton);
    }
    @Step("Нажатие на кнопку 'Выход'")
    public void signOut() {
        waitForRedirect("account/profile");
        waitForLoadElement(getSignOutButton());
        getSignOutButton().click();
        waitForRedirect("/login");
    }

    @Step("Проверка, что профиль аккаунта отображается")
    public Boolean checkAccountProfileEnabled() {
        waitForRedirect("account/profile");
        waitForLoadElement(getProfileButton());
        try {
            return getProfileButton().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
