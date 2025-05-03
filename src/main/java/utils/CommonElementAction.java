package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonElementAction {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	
	
	public CommonElementAction(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public void elementToBeClickWithText(WebElement webelement, String str) {
		wait.until(ExpectedConditions.elementToBeClickable(webelement)).sendKeys(str);
	}
	
	public WebElement elementToBeClick(WebElement webelement) {
		return wait.until(ExpectedConditions.elementToBeClickable(webelement));
	}
	
	public List<WebElement> visibilityOfAllElements(List<WebElement> webelement) {
		return wait.until(ExpectedConditions.visibilityOfAllElements(webelement));
	}
	
	public WebElement visibilityOfElement(WebElement webelement) {
		return wait.until(ExpectedConditions.visibilityOf(webelement));
	}
	
	public boolean isElementVisible(WebElement webelement) {
		 try {
		        return webelement.isDisplayed();
		    } catch (org.openqa.selenium.NoSuchElementException e) {
		        return false;
		    }
	}

}
