
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by p3700471 on 17/02/16.
 */
public class Utils {

    public String login_link = ".login>a>b";

    public String j_username = "#j_username";
    public String j_password = ".//input[@name='j_password']";
    public String j_login = "#yui-gen1-button";

    protected static final String timeout = "50000";
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public String GetProperty(String prop_name)
    {
        String returnedProp = null;
        try {
            FileReader propertiesFile = new FileReader("src/test/resources/credentials.properties");
            Properties property = new Properties();
            property.load(propertiesFile);
            returnedProp = property.getProperty(prop_name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnedProp;
    }

    protected void LoginToJenkins() throws Exception
    {
        try
        {
            Properties properties = new Properties();
            properties.setProperty("username","eprisacaru");
            properties.setProperty("password","alfresco");
            properties.setProperty("url","http://172.29.101.40:8080/");
            FileWriter writer = new FileWriter("src/test/resources/credentials.properties");
            properties.store(writer,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        driver = new FirefoxDriver();
        driver.navigate().to(GetProperty("url"));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Integer.parseInt(timeout) / 1000);

        String userName = GetProperty("username");
        String password = GetProperty("password");

        WebElement loginLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(login_link)));
        loginLink.click();

        WebElement userNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(j_username)));
        TypeText(userNameInput, userName);

        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(j_password)));
        TypeText(passwordInput,password);

        WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(j_login)));
        loginButton.click();
    }

    protected void SelectReport(String report) {

        WebElement reportLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//a[contains(text(),'" + report +"')]")));
        reportLink.click();

    }

    protected void SelectProject(String project) {

        WebElement projectName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='" + project + "']/td[3]/a")));
        projectName.click();
    }

    protected void SelectBuild(String build) {

        WebElement buildNo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='pane build-name']/a[@href='" + build + "']")));
        buildNo.click();
    }

    protected void SelectTab(String text)
    {
        WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*='/view/" + text + "/']")));
        tab.click();
    }

    protected void TypeText(WebElement webElement, String text)
    {
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected File CreateFile(String FileName)
    {
        String path= "C:\\selenium\\";
        File FileToCreate = new File(path.concat(FileName));
        try
        {
            if(!FileToCreate.isDirectory())
            {
                FileToCreate.getParentFile().mkdirs();
            }
            if (!FileToCreate.exists())
            {
                FileToCreate.createNewFile();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return FileToCreate;
    }

    protected void writeIntoFile(File myXMLfile,String content )
    {
        try
        {
            FileWriter writer = new FileWriter(myXMLfile.getAbsoluteFile(),true);
            writer.write(content);
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    protected void LoginToTestLink()
    {
        driver = new FirefoxDriver();

        driver.get("https://testlink.alfresco.com/login.php");
        WebElement user = driver.findElement(By.id("login"));
        TypeText(user,"iaftene");
        WebElement pass = driver.findElement(By.name("tl_password"));
        TypeText(pass,"nFEhb^@w983h");
        driver.findElement(By.name("login_submit")).click();

    }

    protected void OpenTestCase(String TestCaseID)
    {
        wait = new WebDriverWait(driver,10);
        driver.switchTo().defaultContent();
        //driver.switchTo().frame(driver.findElement(By.name("titlebar")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("titlebar"));
        //WebElement div = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("menu_bar")));
        WebElement div = driver.findElement(By.className("menu_bar"));
        WebElement form = div.findElement(By.id("searchTC"));
        WebElement userName = form.findElement(By.name("targetTestCase"));

        TypeText(userName , TestCaseID);
        form.findElement(By.className("clickable")).click();
    }
}
