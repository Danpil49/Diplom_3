package praktikum.autotests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page_object_model.HomePage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTestClass{
    HomePage homePage;

    @Before
    public void setUp() {
        startUp();
        homePage = new HomePage(driver, 3);
    }

    @Test
    @DisplayName("Проверка перехода по нажатию на вкладку 'Булки'")
    public void bunsTabTest() {
        driver.get(homePageUrl);
        homePage.pressOnBunsTab();

        assertTrue("Ingredient is not displayed", homePage.checkIngredientDisplayed(bun));
    }

    @Test
    @DisplayName("Проверка перехода по нажатию на вкладку 'Соусы'")
    public void sauceTabTest() {
        driver.get(homePageUrl);
        homePage.pressOnSauceTab();

        assertTrue("Ingredient is not displayed", homePage.checkIngredientDisplayed(sauce));
    }

    @Test
    @DisplayName("Проверка перехода по нажатию на вкладку 'Начинки'")
    public void fillingTabTest() {
        driver.get(homePageUrl);
        homePage.pressOnFillingTab();

        assertTrue("Ingredient is not displayed", homePage.checkIngredientDisplayed(filling));
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
