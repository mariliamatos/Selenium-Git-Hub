package com.cucumber.framework.helper;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;


import com.cucumber.framework.configuration.browser.BrowserType;
import com.cucumber.framework.configuration.browser.ChromeBrowser;
import com.cucumber.framework.configuration.browser.FirefoxBrowser;
import com.cucumber.framework.configuration.browser.IExploreBrowser;
import com.cucumber.framework.exception.NoSutiableDriverFoundException;
import com.cucumber.framework.utility.DateTimeHelper;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class InitializeWebDrive {

	private final Logger log = Logger.getLogger(InitializeWebDrive.class);
	
	private static WebDriver driver;

	public WebDriver standAloneStepUp(BrowserType bType) throws Exception {
		try {
			switch (bType) {

			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				log.info("Chrome was started!");
				return chrome.getChromeDriver();

			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				log.info("Firefox was started!");
				return firefox.getFirefoxDriver(firefox
						.getFirefoxCapabilities());

			case Iexplorer:
				IExploreBrowser iExplore = IExploreBrowser.class.newInstance();
				log.info("Ie was started!");
				return iExplore.getIExplorerDriver(iExplore
						.getIExplorerCapabilities());

			default:
				throw new NoSutiableDriverFoundException(" Driver Not Found ");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public WebDriver setUpDriver(BrowserType bType) throws Exception {
		driver = standAloneStepUp(bType);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	@After
	public void CloseBrowser(Scenario sc) throws WebDriverException,
			IOException {
		String separator = File.separator;
		String path = new File("src"+separator+"main"+separator+"resources"+separator+"screenshots"+separator)
				.getAbsolutePath();

		File destDir = new File(path);
		if (!destDir.exists())
			destDir.mkdir();

		System.out.println(sc.getName()
				+ Calendar.getInstance().getTime().toString());
		File destPath = new File(destDir.getAbsolutePath()
				+ System.getProperty("file.separator") + sc.getName()
				+ DateTimeHelper.getCurrentDateTime() + ".jpg");

		if (sc.isFailed()) {
			FileUtils
					.copyFile(((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE), destPath);
			String path2 = new File("target"+separator+"Reports"+separator+"extent-config.xml")
					.getAbsolutePath();
			File destDir2 = new File(path2);
			Reporter.loadXMLConfig(destDir2);
			//This attach the specified screenshot to the test
			Reporter.addScreenCaptureFromPath(destPath.toString());
	

			log.error(sc.getName() + " does not passed");
		}
		if (!sc.isFailed())
			log.info(sc.getName() + " passed");

		driver.close();
	}

}
