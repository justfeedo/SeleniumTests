// Importování potřebných tříd
package tests;

import Base.TestBase;
import helpers.ExcelReader;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.time.Duration;

public class PrimeTest extends TestBase {
    // Cesta k souboru s testovacími daty
    private static final String TEST_DATA_PATH = "src/test/resources/data.xlsx";
    // Název listu v excelu, kde jsou testovací data
    private static final String SHEET = "prime";





    // Testovací metoda pro ověření prvočísel
    @Test
    public void testPrime() throws IOException {
        getDriver().get(BASE_URL + "primenumber.php");
        // Najití vstupního pole pro číslo
        WebElement numberInput = getDriver().findElement(By.xpath("//input[@type='number']"));
        // Najití tlačítka
        WebElement button = getDriver().findElement(By.cssSelector("button.btn"));
        // Inicializace objektu pro čtení testovacích dat z Excelu
        ExcelReader primeExcelReader = new ExcelReader(TEST_DATA_PATH);
        // Načtení listu s testovacími daty
        Sheet sheet = primeExcelReader.getSheetByName(SHEET);
        // Procházení jednotlivých řádků s testovacími daty
        for (Row cells : sheet) {
            // Pokud je to první řádek, přeskočit ho
            if (cells.getRowNum() == 0) {
                continue;
            }
            // Načtení čísla z buňky
            int number = (int) cells.getCell(0).getNumericCellValue();
            // Očekávaný výsledek (prvočíslo nebo neprvočíslo) z buňky
            boolean expectedPrime = cells.getCell(1).getBooleanCellValue();

            // Vyčištění vstupního pole a zadání testovaného čísla
            numberInput.clear();
            numberInput.sendKeys(String.valueOf(number));
            // Kliknutí na tlačítko
            button.click();

            // Kontrola výsledku
            checkResult(expectedPrime);
        }
    }

    // Metoda pro kontrolu očekávaného výsledku
    private void checkResult(boolean expectedPrime) {
        // Pokud se očekává prvočíslo
        if (expectedPrime) {
            // Počkejte, dokud se nezobrazí text "Optimus approves"
            new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus approves']")));
        } else { // Pokud se nečeká prvočíslo
            // Počkejte, dokud se nezobrazí text "Optimus is sad"
            new WebDriverWait(getDriver() , Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus is sad']")));
        }
    }

}