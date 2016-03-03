package jenkins;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by P3700509 on 2/27/2016.
 */
public class GetAllTests extends Utils {
	private File TXTfile;

	public GetAllTests(String filename) {
		TXTfile = CreateFile(filename);
	}

	public void TestCaseReport() {
		WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("table")));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		for (int i = 0; i < rows.size(); i++) {
			table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("table")));
			rows = table.findElements(By.tagName("tr"));
			WebElement item = rows.get(i);

			if (item.getAttribute("class").equals("success text-center")
					|| item.getAttribute("class").equals("danger text-center")) {
				WebElement td = item.findElement(By.tagName("td"));
				WebElement href = td.findElement(By.tagName("a"));
				href.click();

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("panel-body")));
				List<WebElement> div = driver.findElements(By.className("nada"));
				for (WebElement item2 : div) {
					WebElement li = item2.findElement(By.tagName("li"));
					WebElement testCaseID = li.findElement(By.className("forDrop"));
					WebElement testCaseRunTime = li.findElement(By.className("label"));
					writeIntoFile(TXTfile, testCaseID.getText() + "\t\t" + testCaseRunTime.getText() + "\t\t");

					if (li.getAttribute("data-original-title").equals("This test has failed"))
						writeIntoFile(TXTfile, "FAILED" + "\n");
					else if (li.getAttribute("data-original-title").equals("This test has passed"))
						writeIntoFile(TXTfile, "PASSED" + "\n");
					else
						writeIntoFile(TXTfile, "SKIPPED" + "\n");

				}
			}
			if (!driver.getTitle().equals("Tests by class overview report"))
				driver.navigate().back();
		}
	}

	protected String GetSuiteName(String TestCaseID) {

		OpenTestCase(TestCaseID);

		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));

		WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[1]/h2")));

		return elem.getText();
	}

	public void AddTestSuiteNameToFile() {
		LoginToTestLink();
		String suitefilename = TXTfile.getName().replace(".txt", "SuiteNameAdded.txt");
		File Suitefile = CreateFile(suitefilename);
		try {
			BufferedReader br = new BufferedReader(new FileReader(TXTfile));
			String fileLine;

			while ((fileLine = br.readLine()) != null) {
				String[] words = fileLine.split("\t");
				String testSuitename = GetSuiteName(words[0].replace("_", "-"));
				String newline = fileLine + "\t\t" + testSuitename + "\n";
				writeIntoFile(Suitefile, newline);
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		driver.close();
	}
}
