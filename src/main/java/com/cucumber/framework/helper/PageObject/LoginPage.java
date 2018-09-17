package com.cucumber.framework.helper.PageObject;



import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.framework.configreader.PropertyFileReader;

import com.cucumber.framework.helper.InitializeWebDrive;

import cucumber.api.java.After;

public class LoginPage extends PageBase {

	private final Logger log = Logger.getLogger(LoginPage.class);
	private static WebDriver driver;
	PropertyFileReader a = new PropertyFileReader();
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/header/div/div[2]/div/span/div/a[1]")
	public WebElement loginButton;

	@FindBy(how = How.ID, using = "login_field")
	public WebElement user;

	@FindBy(how = How.ID, using = "password")
	public WebElement password;

	public LoginPage() {
		super();
	}

	public void init() {


		try {

			InitializeWebDrive initi = new InitializeWebDrive();
			driver = initi.setUpDriver(a.getBrowser());
			PageFactory.initElements(driver, this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login() {

		driver.get(a.getWebsite());
		loginButton.click();
		user.sendKeys(a.getUserName());
		password.sendKeys(a.getPassword());
		password.submit();
		log.info("User is logged!");

	}

	@After
	public void tearDown() {

	}

}
