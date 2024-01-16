package tests;

import Base.TestBase;
import categories.SmokeTest;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class WaitForItTest extends TestBase {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();



    @Category(SmokeTest.class)
    @Test
    public void CheckTitle() {
        getDriver().get(BASE_URL + "/waitforit.php");
        expectedException.expect(ComparisonFailure.class);
        expectedException.expectMessage("nesedi title");


        Assert.assertEquals("nesedi title","WAIT FOR IT ! XD",
                getDriver().findElement(By.xpath("//h1")).getText());

    }

    @Test

    public void checkLegendary() {
        getDriver().get(BASE_URL + "/waitforit.php");
        getDriver().findElement(By.xpath("//button[@id='startWaitForText']")).click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p/span")));
        Assert.assertEquals("dary !!!", getDriver().findElement(By.id("waitForTextInput")).getAttribute("value"));
    }


}
