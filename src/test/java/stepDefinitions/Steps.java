package stepDefinitions;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import com.cucumber.framework.helper.PageObject.LoginPage;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class Steps {

	WebDriver driver; //webdriver selenium class 
	Scenario sc;  
	Logger log = Logger.getLogger(Steps.class);


	@Before
	public void inititesting(Scenario s) {

		this.sc = s;
		String separator = File.separator;
		
		String path = new File(
				"src"+separator+"main"+separator+"resources"+separator+"configfile"+separator+"log4j.properties")  
				.getAbsolutePath();  
		PropertyConfigurator.configure(path); // configuring the log of the project  - > find it on C:\eclipse\Selenium - Git Hub\target\Selenium.log
		log.info("Scenario  " + s.getName() + " has started"); // filling the log

	}

	@Given("^user is trying to login$")
	public void user_is_trying_to_login() throws Throwable {
		LoginPage login = new LoginPage();
		login.init(); //getting the settingd for the brownser
		login.login(); // it will log the user in
		
	}


	

}