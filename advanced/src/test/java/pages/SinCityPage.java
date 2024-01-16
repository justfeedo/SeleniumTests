package pages;

import Base.WebDriverSingleton;
import enumerators.SinType;
import models.Sin;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static Base.TestBase.BASE_URL;


public class SinCityPage {
    private static WebDriver driver;
    private static final String PAGE_URL = "sincity.php";
    @FindBy(name = "title")
    private WebElement titleInput;
    @FindBy(name = "author")
    private WebElement authorInput;
    @FindBy(name = "message")
    private WebElement messageInput;
    @FindBy(xpath="//button[@type='submit']")
    private WebElement confessButton;
    @FindBy(css= "div.sinsListArea")
    private WebElement sinListSection;
    @FindBy(xpath = "//p[@class='pending']")
    private WebElement statusOfSinPending;
    public SinCityPage(){
        driver = WebDriverSingleton.getWebDriverInstance();
        PageFactory.initElements(driver,this);
    }



    public void openPage(){
        driver.get(BASE_URL + PAGE_URL);
    }
    public void fillSinInformation(Sin sin) {
        titleInput.sendKeys(sin.getTitle());
        authorInput.sendKeys(sin.getAuthor());
        messageInput.sendKeys(sin.getMessage());
    }

    public void markTag(List<SinType> tags) {
        for (SinType tag : tags) {
            driver.findElement(By.xpath("//input[@value='" + tag.getXpathValue() + "']")).click();
        }
    }
    public void confessSin(){
        confessButton.click();
    }

    public void openSinDetail(Sin spiderSin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        listOfSins.findElement(By.xpath("./li[contains(text(),'" + spiderSin.getTitle()+"')]")).click();

    }


    public void isPending(Sin spiderSin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        listOfSins.findElement(By.xpath("./li[contains(text(),'" + spiderSin.getTitle()+"')]"));
        Assert.assertTrue("Nesedi",driver.findElement(By.xpath( "//p[@class='pending']")).isDisplayed());
    }
    public static void waitForTextInElement(By locator, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }
    public static boolean verifyTextInElement(By locator, String expectedText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void autorVerify(String author) {

        SinCityPage.waitForTextInElement(By.xpath("/html/body/div/div[2]/article/h4"), author);
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[2]/article/h4")).getText().contains(author));
        System.out.println("AUTOR BYL ZKONTROLOVAN");
    }
    public static void titleVerify(String title) {
        SinCityPage.waitForTextInElement(By.xpath("/html/body/div/div[2]/article/h4"), title);
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[2]/article/h4")).getText().contains(title));
        System.out.println("TITLE BYL ZKONTROLOVAN");
    }

    public static void tagVerify(String crime){

        Assert.assertTrue(driver.findElement(By.xpath("//div[2]/article/div/ul")).getText().contains(crime));
        System.out.println("TAGY BYLY ZKONTROLOVANE");

    }


    public void checkSinStatus(Sin spiderSin) {
        // 1.chci najit element hrichu
       WebElement listOfSins = driver.findElement(By.cssSelector("ul.list-of-sins"));
       WebElement actualSin = listOfSins.findElement(By.xpath("./li[contains(text(),'"+spiderSin.getTitle()+"')]"));
       //2. chci z neho vytahnout text
        String actualText = actualSin.findElement(By.cssSelector("p")).getText().trim();
        //3. chci porovnat ocekavane a actual
        if (spiderSin.isForgiven()){
            Assert.assertEquals("forgiven",actualText);
        }else{
            Assert.assertEquals("pending",actualText);
        }
    }

    public void isForgiven(Sin spiderSin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        listOfSins.findElement(By.xpath("./li[contains(text(),'" + spiderSin.getTitle()+"')]"));
        Assert.assertTrue("Nesedi",driver.findElement(By.xpath( "//p[@class='']")).isDisplayed());
    }
}
