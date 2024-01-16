package tests;

import Base.TestBase;
import enumerators.SinType;
import models.Sin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SinCityPage;
import Base.WebDriverSingleton;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



public class SinCityUlohyTest extends TestBase{
    WebDriver driver = WebDriverSingleton.getWebDriverInstance();

    @Test
    public void verifySinPending(){
        SinCityPage sinCityPage = new SinCityPage();

        Sin spiderSin = new Sin("zabil som dva pavuky", "filip", "zabol som je loptou");
        List<SinType> spiderSinTags = new ArrayList<SinType>();
        spiderSinTags.add(SinType.MURDER);
        spiderSinTags.add(SinType.ROBBERY);
        spiderSinTags.add(SinType.CAR_ACCIDENT);
        spiderSin.setTags(spiderSinTags);

        sinCityPage.openPage();
        sinCityPage.fillSinInformation(spiderSin);
        sinCityPage.markTag(spiderSin.getTags());
        sinCityPage.confessSin();

        sinCityPage.isPending(spiderSin);

    }

    @Test
    public void verifySinProperties(){
        SinCityPage sinCityPage = new SinCityPage();
        SinCityPage openSinDetail = new SinCityPage();
        driver.manage().window().maximize();

        Sin testSin = new Sin("Pokazil som ctvrty test", "Ja", "Pouhe koukani je snazsi");
        List<SinType> testSinTags = new ArrayList<SinType>();
        testSinTags.add(SinType.HIJACK);
        testSinTags.add(SinType.BLACKMAIL);
        testSin.setTags(testSinTags);

        sinCityPage.openPage();
        sinCityPage.fillSinInformation(testSin);
        sinCityPage.markTag(testSin.getTags());
        sinCityPage.confessSin();
        sinCityPage.openSinDetail(testSin);

        SinCityPage.autorVerify("Ja");
        SinCityPage.titleVerify("Pokazil som ctvrty test");
        SinCityPage.tagVerify("hijack");
        SinCityPage.tagVerify("blackmail");
    }

    @Test
    public void forgiveSin(){
        Sin testSin = new Sin("Pokazil som ctvrty test", "Ja", "Pouhe koukani je snazsi");
        List<SinType> testSinTags = new ArrayList<SinType>();
        testSinTags.add(SinType.HIJACK);
        testSinTags.add(SinType.BLACKMAIL);
        testSin.setTags(testSinTags);
        SinCityPage sinCityPage = new SinCityPage();

        sinCityPage.openPage();
        sinCityPage.fillSinInformation(testSin);
        sinCityPage.markTag(testSin.getTags());
        sinCityPage.confessSin();
        // rozjeti stranky
        SinCityPage openSinDetail = new SinCityPage();
        driver.manage().window().maximize();
        sinCityPage.openPage();
        driver.findElement(By.xpath("/html/body/nav/div/ul/li[8]/a")).click();

        // najdi konkretni sin obsahujici article a odpust ho
        String title = "Pokazil";
        WebElement sinArticle = driver.findElement(By.xpath("//div/article[contains(.,'"+ title +"')]"));
        sinArticle.findElement(By.xpath(".//button")).click();
      // driver.getWindowHandles();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id("confirm")));

        driver.findElement(By.id("confirm")).click();

        //zpět na SinCity a over jestli je odpusten
        driver.findElement(By.xpath("/html/body/nav/div/ul/li[7]/a")).click();
        Sin spiderSin = new Sin("zabil som dva pavuky", "filip", "zabol som je loptou");
        sinCityPage.isForgiven(spiderSin);



    }



    /** public void openSinDetail(Sin spiderSin) {
 WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
 listOfSins.findElement(By.xpath("./li[contains(text(),'" + spiderSin.getTitle()+"')]")).click();

 } **/

}
