
package com.cucumber.framework.configreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.cucumber.framework.configuration.browser.BrowserType;

public class PropertyFileReader  {

	private Properties prop = null;

	public PropertyFileReader() {
		prop = new Properties();
		try {
			String separator = File.separator;
			
			String path = new File(
					"src"+separator+"main"+separator+"resources"+separator+"configfile"+separator+"config.properties")
					.getAbsolutePath();
			InputStream is = new FileInputStream(path);
			prop.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String getUserName() {
		return prop.getProperty("Username");
	}

	public String getPassword() {
		return prop.getProperty("Password");
	}

	public String getWebsite() {
		return prop.getProperty("Website");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("ExplicitWait"));
	}

	public BrowserType getBrowser() {
		return BrowserType.valueOf(prop.getProperty("Browser"));
	}

}
