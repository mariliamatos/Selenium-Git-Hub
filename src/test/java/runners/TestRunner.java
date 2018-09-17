package runners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/functionalTests", glue = "", plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/Reports/report.html" }, monochrome = true)
public class TestRunner {

	@AfterClass
	public static void writeExtentReport() {

		String separator = File.separator;
		String path = new File("target"+separator+"Reports"+separator+"extent-config.xml")
				.getAbsolutePath();
		File destDir = new File(path);
		Reporter.loadXMLConfig(destDir);
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("System", System.getProperty("os.name"));
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");
		Reporter.setSystemInfo("Java Version", "1.8.0_151");
		Reporter.assignAuthor("ToolsQA "+ "  - Marilia Matos");
		
	}

}
