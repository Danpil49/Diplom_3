package praktikum.autotests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page_object_model.AccountProfile;
import page_object_model.HomePage;
import page_object_model.LoginPage;
import page_object_model.RegisterPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static praktikum.CleanUp.delete;

public class AccountProfileTest extends BaseTestClass{
    RegisterPage registerPage;
    HomePage homePage;
    LoginPage loginPage;
    AccountProfile accountProfile;

    @Before
    public void setUp() {
        startUp();
        homePage = new HomePage(driver, 3);
        loginPage = new LoginPage(driver, 3);
        accountProfile = new AccountProfile(driver, 3);

        driver.get(registerPageUrl);
        registerPage = new RegisterPage(driver, 3);
        registerPage.registerUser(name, email, password);
    }

    @Test
    @DisplayName("Проверка перехода на страницу 'Личный кабинет' после логина")
    @Description("Переход после логина с домашней страницы на страницу 'Личный кабинет', через кнопку 'Личный кабинет'")
    public void moveToAccountProfileFromHomePage() {
        driver.get(loginPageUrl);
        loginPage.login(email, password);
        homePage.pressAccountProfileButton();

        assertTrue("Failed to get to account profile page", accountProfile.checkAccountProfileEnabled());
    }

    @Test
    @DisplayName("Проверка работы кнопки выхода из аккаунта")
    @Description("Проверка нажатия кнопки 'Выход' на странице личного кабинета")
    public void signOutButtonTest() {
        driver.get(loginPageUrl);
        loginPage.login(email, password);
        homePage.pressAccountProfileButton();
        accountProfile.signOut();

        String expectedURL = "https://stellarburgers.nomoreparties.site/login";
        assertEquals("URL's are not the same", expectedURL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка перехода на страницу конструктора нажатием на логотип 'Stellar burgers'")
    public void moveToConstructorFromAccountProfileByLogo() {
        driver.get(loginPageUrl);
        loginPage.login(email, password);
        homePage.pressAccountProfileButton();
        accountProfile.pressStellarLogo();

        assertEquals("URL's are not the same", homePageUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка перехода на страницу конструктора нажатием на кнопку 'Конструктор'")
    public void moveToConstructorFromAccountProfileByConstructorButton() {
        driver.get(loginPageUrl);
        loginPage.login(email, password);
        homePage.pressAccountProfileButton();
        accountProfile.pressConstructorButton();

        assertEquals("URL's are not the same", homePageUrl, driver.getCurrentUrl());
    }

    @After
    public void cleanUp() {
        delete(email, password);
        driver.quit();
    }
}
