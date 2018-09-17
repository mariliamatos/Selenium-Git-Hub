

package com.cucumber.framework.helper.PageObject;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.helper.InitializeWebDrive;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;


public abstract class PageBase{
	
	private final Logger log = Logger.getLogger(PageBase.class);
	private WebDriver driver;
	
	

	
	public void init(WebDriver driver) {
		PropertyFileReader a = new PropertyFileReader();

		try {

			InitializeWebDrive initi = new InitializeWebDrive();
			driver = initi.standAloneStepUp(a.getBrowser());
			// driver.manage().window().maximize();
			// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public PageBase() {
		
	}

	public void waitForElement(WebElement element,int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotFoundException.class);
		wait.pollingEvery(250,TimeUnit.MILLISECONDS);
		wait.until(elementLocated(element));
	}
	
	private Function<WebDriver, Boolean> elementLocated(final WebElement element) {
		return new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				log.debug("Waiting for Element : " + element);
				return element.isDisplayed();
			}
		};
	}
	
	

		

	
}
