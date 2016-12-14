package com.qizaodian.spider;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HuabanSpider {
	public static String url = "http://huaban.com/all/";

	public static void main(String[] args) {
		new HuabanSpider().spider();
	}

	public void spider() {
		System.setProperty("webdriver.chrome.driver", "phantomjs-1.9.7-windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		waitForPageLoaded(driver);
		scroll(driver);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scroll(driver);
		
		driver.close();
	}

	/***
	 * 滚动到网页最下边
	 * 
	 * @param driver
	 */
	public void scroll(WebDriver driver) {
		((JavascriptExecutor) driver)
				.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/***
	 * 等待网页加载完成
	 * 
	 * @param driver
	 */
	public void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, 5000);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			error.printStackTrace();
		}
	}

}
