package com.yara.assignment.newcorp;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class LeafTest {

	private AndroidDriver<WebElement> driver;
	private AppiumDriverLocalService appiumService;

	private static String appiumServiceUrl;

	@BeforeTest
	public void Android_LaunchApp() throws InterruptedException {
		appiumService = AppiumDriverLocalService.buildDefaultService();
		appiumService.start();
		appiumServiceUrl = appiumService.getUrl().toString();
		System.out.println("Appium Service Address : - " + appiumServiceUrl);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "ZY322P28WF");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("appPackage", "com.yara.lcc.qa");
		capabilities.setCapability("appActivity", "com.yara.lcc.MainActivity");

		try {
			driver = new AndroidDriver<WebElement>(new URL(appiumServiceUrl), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test_LeafTest_001() throws InterruptedException {
		System.out.print("Add new Corp Test has begin");
		Thread.sleep(10000);
		String selectLang = "english";

		switch (selectLang) {
		case "english":
			driver.findElementByAndroidUIAutomator("UiSelector().description(\"btnText_Select_select_language_en\")")
					.click();
			break;
		case "hindi":
			driver.findElementByAndroidUIAutomator("UiSelector().description(\"btnText_Select_select_language_hi\")")
					.click();
			break;
		case "tamil":
			driver.findElementByAndroidUIAutomator("UiSelector().description(\"btnText_Select_select_language_ta\")")
					.click();
			break;
		default:
			break;
		}

		Thread.sleep(2000);
		// verify "Leaf color chart" heading is displayed
		String expLeafTextHeading = "Leaf Colour Chart";
		String leafColorHeading = driver
				.findElementByAndroidUIAutomator("UiSelector().description(\"txtLeafColorChart\")").getText();
		Assert.assertEquals(expLeafTextHeading, leafColorHeading);
		// Click on agree and continue btnText_welcome_screen_continue
		driver.findElementByAndroidUIAutomator("UiSelector().description(\"btnText_welcome_screen_continue\")").click();

		// Verify Add Button
		driver.findElementByAndroidUIAutomator("UiSelector().description(\"btnText_add_crop\")").click();
		Thread.sleep(5000);

		// Verify "Add corp" Page has displayed;
		String addCorpTitle = "Add Crop";
		AndroidElement addCropElement = (AndroidElement) driver
				.findElementByAndroidUIAutomator("UiSelector().description(\"title_nbNavBar\")");
		if (addCropElement.isDisplayed() && addCropElement.getText().contentEquals(addCorpTitle)) {
			System.out.println("Add crop page is dispalyed");
		}
		// Enter name of the farm
		WebElement farmTextField = driver
				.findElement(By.xpath("//android.widget.TextView[@text = \"Name of your farm\"]"));
		farmTextField.click();
		driver.getKeyboard().sendKeys("Rice");
		driver.pressKey(new KeyEvent(AndroidKey.BACK));

		Thread.sleep(2000);
		// Select rice
		String ricetype = "non basmati";
		if (ricetype.equals("non basmati")) {
			WebElement nonBasmatiRice = driver
					.findElementByAndroidUIAutomator("new UiSelector().text(\"Non-basmatic\")");
			nonBasmatiRice.click();

		} else {
			WebElement basmatiRice = driver.findElement(By.xpath("//*[contains(text(),\"Non-basmatic\"]"));
			basmatiRice.click();
		}

		// open calendar select date 15
		driver.findElementByAndroidUIAutomator("new UiSelector().description(\"img_dtSowingdate\")").click();
		// 15
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"15\")").click();
		// select save
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Save\")").click();

		this.scrollDown();
		this.scrollDown();

		// select Long
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Long\")").click();
		// select 1–5 acres
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Long\")").click();

		this.scrollUp();

		// select savebutton on corp page
		driver.findElementByAndroidUIAutomator("new UiSelector().description(\"roundedButtonText_nbNavBar\")").click();

		// Skip
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Skip\")").click();
		String leaf1 = "new UiSelector().description(\"circledCheck_mockLeafColorScreen_2\")";
		String leaf2 = "new UiSelector().description(\"circledCheck_mockLeafColorScreen_3\")";
		String leaf3 = "new UiSelector().description(\"circledCheck_mockLeafColorScreen_4\")";
		String leaf4 = "new UiSelector().description(\"circledCheck_mockLeafColorScreen_5\")";
		String[] leaf = { leaf1, leaf2, leaf3, leaf4 };
		for (int i = 0; i < 10; i++) {

			driver.findElementByAndroidUIAutomator(LeafTest.getRandom(leaf)).click();
			if (i == 9) {
				// Done
				driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Done\")").click();
				break;
			}
			// Next Leaf
			driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Next Leaf\")").click();
		}

		// verify thank you

		String actResult = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Thank You\")").getText();
		assertEquals(actResult, "Thank You", "Thank you message has dispalyed");

	}

	public static String getRandom(String[] array) {
		int rnd = new Random().nextInt(array.length - 1);
		return array[rnd];
	}

	public void scrollUp() {

		Dimension dimension = driver.manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.5);
		int scrollEnd = (int) (dimension.getHeight() * 0.8);

		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(0, scrollEnd))
				.release().perform();
	}

	public void scrollDown() {

		Dimension dimension = driver.manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.5);
		int scrollEnd = (int) (dimension.getHeight() * 0.2);

		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(0, scrollEnd))
				.release().perform();
	}

	@AfterTest
	public void End() {
		System.out.println("Stop driver");
		driver.quit();
		System.out.println("Stop appium service");
		appiumService.stop();
	}
}
