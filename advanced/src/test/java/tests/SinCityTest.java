package tests;

import Base.TestBase;
import enumerators.SinType;
import models.Sin;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.SinCityPage;

import java.util.ArrayList;
import java.util.List;

public class SinCityTest extends TestBase {


    @Test
    public void testNewSin() {

        SinCityPage sinCityPage = new SinCityPage();

        Sin spiderSin = new Sin("zabil som pavuka", "filip", "zabol som ho loptou");
        List<SinType> spiderSinTags = new ArrayList<SinType>();
        spiderSinTags.add(SinType.MURDER);
        spiderSinTags.add(SinType.ROBBERY);
        spiderSinTags.add(SinType.CAR_ACCIDENT);
        spiderSin.setTags(spiderSinTags);

        sinCityPage.openPage();
        sinCityPage.fillSinInformation(spiderSin);
        sinCityPage.markTag(spiderSin.getTags());
        sinCityPage.confessSin();
        sinCityPage.checkSinStatus(spiderSin);



    }
}
