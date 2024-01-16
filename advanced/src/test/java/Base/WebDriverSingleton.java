package Base;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private static WebDriver driver;

    private static void initialize(){
        initializee();
      //   initializeSeleniumServer();
       // initializeHeadLessChrome();
       // driver = new ChromeDriver();
    }



        public static void initializee() {
            // Set the path to the ChromeDriver executable
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

            // Configure Chrome options if needed
            ChromeOptions options = new ChromeOptions();
            // Add any additional options as needed

            // Create a new instance of the ChromeDriver
            driver = new ChromeDriver(options);

            // Configure implicit wait and other settings if needed
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        public static WebDriver getWebDriverInstance() {
            if (driver == null) {
                initialize();
            }
            return driver;
        }

        public static void closeWebDriver() {
            if (driver != null) {
                driver.quit();
                driver = null; // Reset the driver instance
            }
        }

        // Other WebDriver-related methods can be added here

        public static void main(String[] args) {
            WebDriver webDriver = getWebDriverInstance();

            // Perform actions using the WebDriver

            closeWebDriver();
        }


    private static void initializeSeleniumServer() {
        URL url = null;

        try {
            url = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setCapability("timeouts", ImmutableMap.of("commandTimeout", "300"));  // Updated to use W3C syntax

        System.out.println("Before initializing RemoteWebDriver");

        try {
            driver = new RemoteWebDriver(url, options);
            System.out.println("After initializing RemoteWebDriver");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception during RemoteWebDriver initialization: " + e.getMessage());
        }
    }




    private static void initializeHeadLessChrome(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }



}
