
package com.cucumber.framework.configuration.browser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.cucumber.framework.utility.DateTimeHelper;


public class ChromeBrowser {

	public Capabilities getChromeCapabilities() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		chrome.setCapability(ChromeOptions.CAPABILITY, option);
		return chrome;
	}

	public WebDriver getChromeDriver() {
		
		String separator = File.separator;
		String path = "";
		if(separator.equals("\\"))
		 path = new File(
				"src"+separator+"main"+separator+"resources"+separator+"driver"+separator+"chromedriver-c.exe")
				.getAbsolutePath();
		else
			path = new File(
					"src"+separator+"main"+separator+"resources"+separator+"driver"+separator+"chromedriver")
					.getAbsolutePath();
			
		System.setProperty("webdriver.chrome.driver", path);

		String pathLog = new File("src"+separator+"main"+separator+"resources"+separator+"logs"+separator)
				.getAbsolutePath();

		pathLog = pathLog+separator+"chromelog" + DateTimeHelper.getCurrentDateTime()
				+ ".log";
		System.setProperty("webdriver.chrome.logfile", pathLog);

		return new ChromeDriver();

	}

	public WebDriver getChromeDriver(String hubUrl, Capabilities cap)
			throws MalformedURLException {
		return new RemoteWebDriver(new URL(hubUrl), cap);
	}

}
