package jenkins;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;

/**
 * Created by P3700509 on 2/27/2016.
 */
public class GetFailedTests extends Utils
{
    private File myXMLfile ;

    public GetFailedTests(String filename)
    {
        myXMLfile = CreateFile(filename);
    }

    public void XMLHeader()
    {
        writeIntoFile(myXMLfile,"<?xml version=\"1.0\" encoding=\"UTF-8\"?><!--Generated by TestNgXml.(From Belarus with love.)--><suite name=\"Automated tests\" preserve-order=\"true\" verbose=\"1\">"+"\n");
        writeIntoFile(myXMLfile,"<test name=\"AONE\" parallel=\"classes\" thread-count=\"1\" verbose=\"1\">"+"\n");
        writeIntoFile(myXMLfile,"<classes>"+"\n");
    }

    public void XMLContent()
    {


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
                writeIntoFile(myXMLfile,"<class name=\""+href.getText()+"\">"+"\n" );
                writeIntoFile(myXMLfile,"<methods>"+"\n" );
                href.click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("panel-body")));
                List<WebElement> div = driver.findElements(By.className("nada"));

                for (WebElement item2 : div)
                {
                    WebElement li = item2.findElement(By.tagName("li"));
                    if (li.getAttribute("data-original-title").equals("This test has failed") || li.getAttribute("data-original-title").equals("This test has been skipped"))
                    {
                        WebElement span = li.findElement(By.tagName("span"));
                        System.out.println(span.getText());
                        writeIntoFile(myXMLfile,"<include name=\""+span.getText()+"\">"+"\n");
                    }
                }

                writeIntoFile(myXMLfile,"<exclude name=\".*\"/>"+"\n");
                writeIntoFile(myXMLfile,"</methods>"+"\n");
                writeIntoFile(myXMLfile,"</class>"+"\n"+"\n");
            }
            if (!driver.getTitle().equals("Tests by class overview report"))
                driver.navigate().back();
        }
    }

    public void XMLFooter()
    {
        writeIntoFile(myXMLfile,"</classes>"+"\n");
        writeIntoFile(myXMLfile,"</test>"+"\n");
        writeIntoFile(myXMLfile,"</suite>");
    }
}
