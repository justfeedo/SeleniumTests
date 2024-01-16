package tests;

import Base.TestBase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterTest extends TestBase {
    int number;
    boolean expectedPrime;

    @Parameterized.Parameters // Díky tomuhle bude @Test brát jedno číslo po druhým
    public static List<Object[]> getData() {
        return Arrays.asList(new Object[][]{{1,true}, {2,true}, {482,false}});
    }

    public ParameterTest(int number, boolean expectedPrime) {
        this.number = number;
        this.expectedPrime = expectedPrime;
    }


    @Test
    public void testOptimusUsingParameters(){
        getDriver().get(BASE_URL + "primenumber.php");
        System.out.println(number);
       // Najití vstupního pole pro číslo
        WebElement numberInput = getDriver().findElement(By.xpath("//input[@type='number']"));
        // Najití tlačítka
        WebElement button = getDriver().findElement(By.cssSelector("button.btn"));

            // Vyčištění vstupního pole a zadání testovaného čísla
            numberInput.clear();
            numberInput.sendKeys(String.valueOf(number));
            // Kliknutí na tlačítko
            button.click();

            // Kontrola výsledku
         //  checkResult(expectedPrime);
 }
      private void checkResult(boolean expectedPrime){


        // Pokud se očekává prvočíslo
        if (expectedPrime) {
            // Počkejte, dokud se nezobrazí text "Optimus approves"
            new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus approves']")));
        } else { // Pokud se nečeká prvočíslo
            // Počkejte, dokud se nezobrazí text "Optimus is sad"
            new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus is sad']")));
        }

        }


}
