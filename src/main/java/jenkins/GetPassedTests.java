package jenkins;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;

/**
 * Created by P3700509 on 2/27/2016.
 */
public class GetPassedTests extends Utils
{
    private File myTXTfile ;

    public GetPassedTests(String filename)
    {
        myTXTfile = CreateFile(filename);
    }

    public void TestCaseReport() {
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("table")));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for(int i = 0;i < rows.size(); i++)
        {
            table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("table")));
            rows = table.findElements(By.tagName("tr"));
            WebElement item = rows.get(i);

            if (item.getAttribute("class").equals("success text-center") || item.getAttribute("class").equals("danger text-center"))
            {
                WebElement td = item.findElement(By.tagName("td"));
                WebElement href = td.findElement(By.tagName("a"));
                href.click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("panel-body")));
                List<WebElement> div = driver.findElements(By.className("nada"));
                for (WebElement item2 : div) {
                    WebElement li = item2.findElement(By.tagName("li"));
                    if (li.getAttribute("data-original-title").equals("This test has passed"))
                    {
                        WebElement span = li.findElement(By.tagName("span"));
                        System.out.println(span.getText());
                        writeIntoFile(myTXTfile,span.getText()+"\n");
                    }
                }
            }
            if (!driver.getTitle().equals("Tests by class overview report"))
                driver.navigate().back();
        }
    }
}
