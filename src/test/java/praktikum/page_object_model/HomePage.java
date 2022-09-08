package praktikum.page_object_model;

import com.beust.ah.A;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePageObjectClass{
    private final Actions actions = new Actions(driver);
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By placeAnOrderButton = By.xpath("//button[text()='Оформить заказ']");

    private final By bunTab = By.xpath("//span[text() = 'Булки']");
    private final By sauceTab = By.xpath("//span[text() = 'Соусы']");
    private final By fillingTab = By.xpath("//span[text() = 'Начинки']");

    public HomePage(WebDriver driver, int timeoutDurationInSeconds) {
        super(driver, timeoutDurationInSeconds);
    }

    public WebElement getPlaceAnOrderButton() {
        return driver.findElement(placeAnOrderButton);
    }

    private WebElement getBunTab() {
        return driver.findElement(bunTab);
    }
    @Step("Нажатие на вкладку 'Булки'")
    public void pressOnBunsTab() {
        waitForLoadElement(getBunTab());
        actions.moveToElement(getBunTab()).click().perform();
    }
    private WebElement getIngredient(String ingredientNum) {
        return driver.findElement(By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/ul[" + ingredientNum + "]/a[1]/img"));
    }
    @Step("Проверка что указанный в аргументе ингредиент отображен на экране")
    public Boolean checkIngredientDisplayed(String ingredientNum) {
        waitForLoadElement(getIngredient(ingredientNum));
        return getIngredient(ingredientNum).isDisplayed();
    }

    private WebElement getLoginButton() {
        return driver.findElement(loginButton);
    }

    private WebElement getSauceTab() {
        return driver.findElement(sauceTab);
    }
    @Step("Нажатие на вкладку 'Соусы'")
    public void pressOnSauceTab() {
        waitForLoadElement(getSauceTab());
        actions.moveToElement(getSauceTab()).click().perform();
    }

    private WebElement getFillingTab() {
        return driver.findElement(fillingTab);
    }
    @Step("Нажатие на вкладку 'Начинки'")
    public void pressOnFillingTab() {
        waitForLoadElement(getFillingTab());
        actions.moveToElement(getFillingTab()).click().perform();
    }
    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void pressLogInButton() {
        waitForLoadElement(getLoginButton());
        getLoginButton().click();
    }
}
