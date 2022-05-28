package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.clickSubmit();

		Thread.sleep(1000);

		AddCustomerPage addcust = new AddCustomerPage(driver);

		addcust.clickAddNewCustomer();

		Thread.sleep(300);

		if (isAdPresent() == true) {

			driver.findElement(By.xpath("//span[text()='Close']")).click();
		}
		Thread.sleep(300);
		logger.info("providing customer details....");

		addcust.custName("Pavwean");
		addcust.custgender("male");
		addcust.custdob("11", "09", "1985");
		Thread.sleep(1000);
		addcust.custaddress("INDIA");
		addcust.custcity("HYPD");
		addcust.custstate("MH");
		addcust.custpinno("5000234");
		addcust.custtelephoneno("9878390091");

		String email = randomestring() + "@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abc23def");
		addcust.custsubmit();

		Thread.sleep(1000);

		logger.info("validation started....");

		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

		if (res == true) {
			Assert.assertTrue(true);
			logger.info("test case passed....");

		} else {
			logger.info("test case failed....");
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}

		addcust.homePage();		
		
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//a[@href='Logout.php']")));
		 */
		driver.findElement(By.xpath("//a[@href='Logout.php']")).click();

		logger.info("User click on Logout Button");

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept();// close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(true);
			logger.warn("Test Passed");
		}

	}

}
