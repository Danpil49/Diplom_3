package praktikum.autotests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;

public class BaseTestClass {
    protected WebDriver driver;
    protected String homePageUrl;
    protected String registerPageUrl;
    protected String loginPageUrl;
    protected String forgotPassUrl;
    protected String accountProfileURL;
    protected String browser;
    protected String name;
    protected String email;
    protected String password;
    protected String wrongPassword;
    protected String bun;
    protected String sauce;
    protected String filling;

    public void startUp() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/test/resources/config.json"));
            JSONObject jsonObject = (JSONObject)obj;
            browser = (String)jsonObject.get("browser");
            homePageUrl = (String)jsonObject.get("homePageUrl");
            registerPageUrl = (String)jsonObject.get("registerPageUrl");
            loginPageUrl = (String)jsonObject.get("loginPageUrl");
            forgotPassUrl = (String)jsonObject.get("forgotPassUrl");
            accountProfileURL = (String)jsonObject.get("accountProfileURL");
            name = (String)jsonObject.get("name");
            email = (String)jsonObject.get("email");
            password = (String)jsonObject.get("password");
            wrongPassword = (String)jsonObject.get("wrongPassword");
            bun = ((Long)jsonObject.get("bun")).toString();
            sauce = ((Long)jsonObject.get("sauce")).toString();
            filling = ((Long)jsonObject.get("filling")).toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        switch(browser) {
//            case("chrome"):
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//            case("firefox"):
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//            case("yandex"):
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//            default:
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//        }
    }
}
