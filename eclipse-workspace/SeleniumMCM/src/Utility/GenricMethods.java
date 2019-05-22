package Utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GenricMethods {

	public static WebDriver driver;
	public static JavascriptExecutor JSdriver;
	public static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
			.format(Calendar.getInstance().getTime());
	
	public static void takeScreenshot(String screenshotName) throws Exception {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./Screenshot"
				+ screenshotName + timeStamp + ".png"));
	}	
	
	public static void switchToNewWindow() {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = (String) itr.next();
		String w2 = (String) itr.next();
		driver.switchTo().window(w2);
	}

	public static void switchToOldWindow() {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = (String) itr.next();
		String w2 = (String) itr.next();
		driver.switchTo().window(w1);
	}


	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	
	public static void switchToFrame_byName(String frameName) {
		driver.switchTo().frame(frameName);
	}


	public static void switchToFrame_byIndex(int frameValue) {
		driver.switchTo().frame(frameValue);
	}

	public static void switchToFrame_byWebElement(String frameName) throws Exception {
		WebElement webelement = driver.findElement(By.tagName(frameName));
		try {
			driver.switchTo().frame(webelement);
		} catch (Exception e) {
			throw e;
		}
	}

	public static void waitForElementToBeClickable(WebElement element){

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	
	public static void clickWebelement(WebElement element) {
		try {
			boolean elementIsClickable = element.isEnabled();
			while (elementIsClickable) {
				element.click();
			}

		} catch (Exception e) {
			System.out.println("Element is not enabled");
			e.printStackTrace();
		}
	}


	public static void dragAndDrop(WebElement fromWebElement,
			WebElement toWebElement) {
		Actions builder = new Actions(driver);
		builder.dragAndDrop(fromWebElement, toWebElement);
	}

	public static void hoverWebelement(WebElement HovertoWebElement)
			throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.moveToElement(HovertoWebElement).perform();
		Thread.sleep(2000);

	}

	public static void verifyExpectedAndActualOptionsInDropdown(
			WebElement element, List<String> listOfOptions) {

		Select ele = new Select(element);
		// need to give list of options like below. You can add values from
		// excel or csv
		/*
		 * List<String> ds = new ArrayList<String>(); ds.add("Asia");
		 * ds.add("Europe"); ds.add("Africa");
		 */

		List<String> expectedOptions = listOfOptions;
		List<String> actualOptions = new ArrayList<String>();
		for (WebElement option : ele.getOptions()) {
			System.out.println("Dropdown options are: " + option.getText());
			actualOptions.add(option.getText());

		}
		System.out.println("Numbers of options present in the dropdown: "
				+ actualOptions.size());

		Assert.assertEquals(expectedOptions.toArray(), actualOptions.toArray());
		System.out.println("test");

	}

	public static void verifyOptionsInDropdownInAphabeticalOrder(
			WebElement element) {

		Select ele = new Select(element);
		List<String> expectedOptions = new ArrayList<String>();
		List<String> actualOptions = new ArrayList<String>();

		for (WebElement option : ele.getOptions()) {
			System.out.println("Dropdown options are: " + option.getText());
			actualOptions.add(option.getText());
			expectedOptions.add(option.getText());
		}

		Collections.sort(actualOptions);
		System.out.println("Numbers of options present in the dropdown: "
				+ actualOptions.size());
		Assert.assertEquals(expectedOptions.toArray(), actualOptions.toArray());

	}

	public static void selectElementByValueMethod(WebElement element,
			String value) {
		Select selectitem = new Select(element);
		selectitem.selectByValue(value);
	}

	public static void deselectElementByValueMethod(WebElement element,
			String value) {
		Select selectitem = new Select(element);
		selectitem.deselectByValue(value);
	}

	public static void selectElementByIndexMethod(WebElement element, int index) {
		Select selectitem = new Select(element);
		selectitem.selectByIndex(index);
	}

	
	public static void refresh() {
		driver.navigate().refresh();
	}

	public static String getToolTip(WebElement toolTipofWebElement)
			throws InterruptedException {
		String tooltip = toolTipofWebElement.getAttribute("title");
		System.out.println("Tool text : " + tooltip);
		return tooltip;
	}

	
	public static void clearTextField(WebElement element) {
		element.clear();

	}

	public static void highlightelement(WebElement element) {
		for (int i = 0; i < 3; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "color: solid red; border: 5px solid blue;");
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "");

		}

	}

	


	public static void scrolltoElement(WebElement ScrolltoThisElement) {
		Coordinates coordinate = ((Locatable) ScrolltoThisElement)
				.getCoordinates();
		coordinate.onPage();
		coordinate.inViewPort();
	}
	

	public static String generateRandomEmail(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890"
				+ "_-.";
		String email = "";
//		String temp = RandomStringUtils.random(length, allowedChars);
		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000);
		email = ("username"+ randomInt +"@yopmail.com").toString();
		return email;
		
	}
	
	public static boolean isElementPresent_webelement(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void verifyCSSvalue(WebElement element, String cssValueOf,
			String expectedCssValue) {
		try {
			element.isDisplayed();
			System.out.println("CSS Value of " + cssValueOf + " is :"
					+ element.getCssValue(cssValueOf));
			Assert.assertEquals(expectedCssValue,
					element.getCssValue(cssValueOf));
		} catch (NoSuchElementException e) {
			throw e;
		}

	}

	
	public static void deleteAllCookies() {
		driver.manage().deleteAllCookies();

	}

	public static long getPageLoadTime() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long loadEventEnd = (Long) js
				.executeScript("return window.performance.timing.loadEventEnd;");
		long navigationStart = (Long) js
				.executeScript("return window.performance.timing.navigationStart;");
		long loadtime = (loadEventEnd - navigationStart) / 1000;
		System.out.println("Page Load Time is " + loadtime + " seconds.");
		return loadtime;
	}

	public static int[] getX_Y_cordinates(WebElement element) {
		int[] xy = null;
		Point p = element.getLocation();
		System.out.println("X Position: " + p.getX());
		System.out.println("Y Position: " + p.getY());
		int x = p.getX();
		int y = p.getY();
		xy = new int[x * y];
		return xy;

	}

	
}
