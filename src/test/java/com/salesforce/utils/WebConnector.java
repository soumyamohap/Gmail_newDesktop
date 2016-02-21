package com.salesforce.utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.salesforce.constants.ConstantsDeclaration;

public class WebConnector {
	Properties or = null;
	Properties config = null;
	WebDriver driver = null;

	public WebConnector() {

		if (or == null) {
			try {
				or = new Properties();
				String propFilePath = System.getProperty("user.dir")
						+ "\\src\\test\\java\\com\\salesforce\\configs\\OR.properties";
				FileInputStream fs = new FileInputStream(propFilePath);
				or.load(fs);
				config = new Properties();
				String enfPath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\salesforce\\configs\\"
						+ or.getProperty("Env") + "_env.properties";
				fs = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\java\\com\\salesforce\\configs\\" + or.getProperty("Env") + "_env.properties");

				config.load(fs);

			}

			catch (Exception e) {
				System.out.println("File not loaded correctly");
			}
		}
	}

	public void openBrowser(String browser) {
		if ("Chrome".equals(browser)) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//DriverServers//chromedriver.exe");
			driver = new ChromeDriver();

		}

	}

	public void navigate(String path) {

		String currenturl = driver.getCurrentUrl();
		if (!currenturl.contains(config.getProperty(ConstantsDeclaration.URL)))

		{

			driver.get(config.getProperty(path));
		}
	}

	public void inputData(String locatortype, String locator) {
		String value = config.getProperty(locator);
		WebElement we = findElement(locatortype, locator);
		we.clear();
		we.sendKeys(value);
		;

	}

	public void click(String locatortype, String locator) {

		WebElement we = findElement(locatortype, locator);
		we.click();

	}

	public WebElement findElement(String locatortype, String locator) {
		WebElement we = null;
		String locatorname = or.getProperty(locator);
		if (ConstantsDeclaration.LOCATOR_TYPE_ID.equals(locatortype)) {
			we = driver.findElement(By.id(locatorname));
		} else if ("xpath".equals(locatortype)) {
			we = driver.findElement(By.xpath(locatorname));

		} else if ("cssSelector".equals(locatortype)) {
			we = driver.findElement(By.cssSelector(locatorname));
		}
		return we;
	}
}
