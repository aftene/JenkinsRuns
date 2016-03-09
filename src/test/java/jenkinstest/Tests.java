package jenkinstest;

import jenkins.GatherStatistics;
import jenkins.GetAllTests;
import jenkins.GetFailedTests;
import jenkins.GetPassedTests;
import org.junit.Test;

/**
 * Created by P3700509 on 2/27/2016.
 */
public class Tests {
	private GetPassedTests prereqPassedT;

	@SuppressWarnings("deprecation")
	@Test
	public void GetPassedTestsFrom42NProject() throws Exception {
		prereqPassedT = new GetPassedTests("PassedTests42NProjRun33.txt");

		prereqPassedT.LoginToJenkins();

		prereqPassedT.SelectTab("42N");

		
		prereqPassedT.SelectProject("job_42N-WebdriverRun");

		System.out.println("Select build");
		String build = "/view/42N/job/42N-WebdriverRun/33/";
		prereqPassedT.SelectBuild(build);

		System.out.println("Select View testNG Report");
		prereqPassedT.SelectReport("View TestNG Reports");

		System.out.println("Get all the tests");
		prereqPassedT.TestCaseReport();
		
	}

	@Test
	public void GetPassedTestsFrom50NProject() throws Exception {
		prereqPassedT = new GetPassedTests("PassedTests50NProjRun168.txt");

		prereqPassedT.LoginToJenkins();

		System.out.println("Select 50N tab");
		prereqPassedT.SelectTab("50N");

		System.out.println("Select 450N-QAShareRun tab");
		prereqPassedT.SelectProject("job_50N-QAShareRun");

		System.out.println("Select build");
		String build = "/view/50N/job/50N-QAShareRun/168/";
		prereqPassedT.SelectBuild(build);

		System.out.println("Select View testNG Report");
		prereqPassedT.SelectReport("View TestNG Reports");

		System.out.println("Get all the tests");
		prereqPassedT.TestCaseReport();
	}

	@Test
	public void GetPassedTestsFrom50NProject1() throws Exception {
		GetPassedTests prereqPassedT = new GetPassedTests("PassedTests50NProjRun-1-36.txt");

		System.out.println("Starting test!");

		System.out.println("Login to jenkins");
		prereqPassedT.LoginToJenkins();

		System.out.println("Select 50N tab");
		prereqPassedT.SelectTab("50N");

		System.out.println("Select 50N-QAShareRun-1 tab");
		prereqPassedT.SelectProject("job_50N-QAShareRun-1");

		System.out.println("Select build");
		String build = "/view/50N/job/50N-QAShareRun-1/36/";
		prereqPassedT.SelectBuild(build);

		System.out.println("Select View testNG Report");
		prereqPassedT.SelectReport("View TestNG Reports");

		System.out.println("Get all the tests");
		prereqPassedT.TestCaseReport();
	}

	@Test
	public void GetFailedTestsFrom42NProject() throws Exception {
		GetFailedTests prereqFailedT = new GetFailedTests("FailedTests42NProjRun33.xml");

		System.out.println("Starting test!");

		System.out.println("Login to jenkins");
		prereqFailedT.LoginToJenkins();

		System.out.println("Select 42N tab");
		prereqFailedT.SelectTab("42N");

		System.out.println("Select 42N-WebdriverRun tab");
		prereqFailedT.SelectProject("job_42N-WebdriverRun");

		System.out.println("Select build");
		String build = "/view/42N/job/42N-WebdriverRun/33/";
		prereqFailedT.SelectBuild(build);

		System.out.println("Select View testNG Report");
		prereqFailedT.SelectReport("View TestNG Reports");

		System.out.println("Create the file and write the header");
		prereqFailedT.XMLHeader();

		System.out.println("Get all the tests");
		prereqFailedT.XMLContent();

		System.out.println("The footer of the XML file");
		prereqFailedT.XMLFooter();
	}

	@Test
	public void GetFailedTestsFrom50NProject() throws Exception {
		GetFailedTests prereqFailedT = new GetFailedTests("FailedTests50NProjRun168.xml");

		System.out.println("Starting test!");

		System.out.println("Login to jenkins");
		prereqFailedT.LoginToJenkins();

		System.out.println("Select 50N tab");
		prereqFailedT.SelectTab("50N");

		System.out.println("Select 450N-QAShareRun tab");
		prereqFailedT.SelectProject("job_50N-QAShareRun");

		System.out.println("Select build");
		String build = "/view/50N/job/50N-QAShareRun/168/";
		prereqFailedT.SelectBuild(build);

		System.out.println("Select View testNG Report");
		prereqFailedT.SelectReport("View TestNG Reports");

		System.out.println("Create the file and write the header");
		prereqFailedT.XMLHeader();

		System.out.println("Get all the tests");
		prereqFailedT.XMLContent();

		System.out.println("The footer of the XML file");
		prereqFailedT.XMLFooter();
	}

	@Test
	public void GetAllTestsFrom50NProject1() throws Exception {

		GetAllTests prereqGetAllT = new GetAllTests("AllTests50NProjRun-1-36.txt");

		System.out.println("Starting test!");

		System.out.println("Login to jenkins");
		prereqGetAllT.LoginToJenkins();

		System.out.println("Select 50N tab");
		prereqGetAllT.SelectTab("50N");

		System.out.println("Select 50N-QAShareRun-1 tab");
		prereqGetAllT.SelectProject("job_50N-QAShareRun-1");

		System.out.println("Select build");
		String build = "/view/50N/job/50N-QAShareRun-1/36/";
		prereqGetAllT.SelectBuild(build);

		System.out.println("Select View testNG Report");
		prereqGetAllT.SelectReport("View TestNG Reports");

		System.out.println("Get all the tests");
		prereqGetAllT.TestCaseReport();
	}

	@Test
	public void GetTestSuitesFor50NProj() throws Exception {
		GetAllTests prereqGetAllT = new GetAllTests("AllTests50NProjRun-1-36.txt");

		prereqGetAllT.AddTestSuiteNameToFile();

	}

	@Test
	public void GetStatisticsFor50NProj()
	{
		GetAllTests prereqGetAllT = new GetAllTests("AllTests50NProjRun-1-36.txt");
		GatherStatistics statistics = new GatherStatistics();

		statistics.PassedPercentage(prereqGetAllT.GetTXTfile());
		statistics.GetTotalRunTime(prereqGetAllT.GetTXTfile());

	}


	@Test
	public void GetAllTestsFrom42NProject() throws Exception {

		GetAllTests prereqGetAllT = new GetAllTests("AllTests42NProjRun33.txt");

		System.out.println("Starting test!");

		System.out.println("Login to jenkins");
		prereqGetAllT.LoginToJenkins();

		System.out.println("Select 42N tab");
		prereqGetAllT.SelectTab("42N");

		System.out.println("Select 42N-WebdriverRun tab");
		prereqGetAllT.SelectProject("job_42N-WebdriverRun");

		System.out.println("Select build");
		String build = "/view/42N/job/42N-WebdriverRun/33/";
		prereqGetAllT.SelectBuild(build);

		System.out.println("Select View testNG Report");
		prereqGetAllT.SelectReport("View TestNG Reports");

		System.out.println("Get all the tests");
		prereqGetAllT.TestCaseReport();

		prereqGetAllT.driver.close();
	}

	@Test
	public void GetTestSuitesFor42NProj() throws Exception {
		GetAllTests prereqGetAllT = new GetAllTests("AllTests42NProjRun33.txt");

		prereqGetAllT.AddTestSuiteNameToFile();

	}

	@Test
	public void GetStatisticsFor42NProj()
	{
		GetAllTests prereqGetAllT = new GetAllTests("AllTests42NProjRun33.txt");
		GatherStatistics statistics = new GatherStatistics();

		statistics.PassedPercentage(prereqGetAllT.GetTXTfile());
		statistics.GetTotalRunTime(prereqGetAllT.GetTXTfile());

	}


}
