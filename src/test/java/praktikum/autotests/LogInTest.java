package praktikum.autotests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page_object_model.ForgotPassPage;
import page_object_model.HomePage;
import page_object_model.LoginPage;
import page_object_model.RegisterPage;

import static org.junit.Assert.assertTrue;
import static praktikum.CleanUp.delete;

public class LogInTest extends BaseTestClass{

    RegisterPage registerPage;
    HomePage homePage;
    LoginPage loginPage;
    ForgotPassPage forgotPassPage;


    @Before
    public void setUp() {
        startUp();
        homePage = new HomePage(driver, 3);
        loginPage = new LoginPage(driver, 3);
        forgotPassPage = new ForgotPassPage(driver, 3);

        driver.get(registerPageUrl);
        registerPage = new RegisterPage(driver, 3);
        registerPage.registerUser(name, email, password);
    }

    @Test
    @DisplayName("Проверка логина на сайт через кнопку 'Войти в аккаунт' на домашней странице")
    @Description("Проверка перехода на форму логина и дальнейшая авторизация через кнопку 'Войти в аккаунт' на главной странице")
    public void logInFromButtonOnHomePage() {
        driver.get(homePageUrl);
        homePage.pressLogInButton();
        loginPage.login(email, password);

        assertTrue("Log in failed", loginPage.checkLogin());
    }

    @Test
    @DisplayName("Проверка логина через кнопку 'Личный кабинет'")
    @Description("Проверка перехода на форму логина и дальнейшей авторизации при клике на кнопку 'Личный кабинет'")
    public void logInFromAccountProfileButton() {
        driver.get(homePageUrl);
        homePage.pressAccountProfileButton();
        loginPage.login(email, password);

        assertTrue("Log in failed", loginPage.checkLogin());
    }

    @Test
    @DisplayName("Проверка логина на сайт через кнопку 'Войти' на форме регистрации")
    @Description("Проверка перехода на форму логина и дальнейшая авторизация через кнопку 'Войти' на форме регистрации")
    public void logInFromRegistrationForm() {
        driver.get(registerPageUrl);
        registerPage.pressLogInLink();
        loginPage.login(email, password);

        assertTrue("Log in failed", loginPage.checkLogin());
    }

    @Test
    @DisplayName("Проверка логина на сайт через кнопку 'Войти' на форме восстановления пароля")
    @Description("Проверка перехода на форму логина и дальнейшая авторизация через кнопку 'Войти' на форме восстановления пароля")
    public void logInFromForgotPassPageLink() {
        driver.get(forgotPassUrl);
        forgotPassPage.pressLogInLink();
        loginPage.login(email, password);

        assertTrue("Log in failed", loginPage.checkLogin());
    }

    @After
    public void cleanUp() {
        delete(email, password);
        driver.quit();
    }
}
