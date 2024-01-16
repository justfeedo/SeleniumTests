package Base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {


    public static final String BASE_URL = "http://localhost:50/";

    @Before
    public void setUp(){
        WebDriverSingleton.initializee();
    }

    @After
    public void tearDown(){
       WebDriverSingleton.getWebDriverInstance().close();
        WebDriverSingleton.getWebDriverInstance().quit();
    }

    public WebDriver getDriver() {return WebDriverSingleton.getWebDriverInstance();}
}
