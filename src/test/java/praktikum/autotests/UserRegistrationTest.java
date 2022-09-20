package praktikum.autotests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page_object_model.LoginPage;
import page_object_model.RegisterPage;

import static org.junit.Assert.assertFalse;
import static praktikum.CleanUp.delete;
import static org.junit.Assert.assertTrue;

public class UserRegistrationTest extends BaseTestClass{
    Boolean needCleanUp;
    RegisterPage register;
    LoginPage login;


    @Before
    public void setUp() {
        startUp();
        register = new RegisterPage(driver, 3);
        login = new LoginPage(driver, 3);
        needCleanUp = true;
    }

    @Test
    @DisplayName("Проверка регистрации пользователя")
    @Description("Проверка регистрации пользователя с правильно указанными полями")
    public void correctRegistrationTest() {
        driver.get(registerPageUrl);
        register.registerUser(name, email, password);

        assertTrue("Registration failed", register.checkRegister(email, password));
    }

    @Test
    @DisplayName("Проверка регистрации пользователя. Проверка валидации пароля")
    @Description("Проверка регистрации пользователя с паролем менее 6 символов")
    public void wrongPassRegisterTest() {
        needCleanUp = false;
        driver.get(registerPageUrl);
        register.registerUser(name, email, wrongPassword);
        register.pressLogInLink();

        assertFalse("Registration failed", register.checkRegister(email, password));

    }

    @After
    public void cleanUp() {
        if (needCleanUp) {
            delete(email, password);
            System.out.println("Cleaned!");
        }
       driver.quit();
    }
}
