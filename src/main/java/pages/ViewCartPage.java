package pages;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BrowserFactory;
import utils.ConfigReader;

public class ViewCartPage {


	private WebDriver driver;
	private WebDriverWait wait;
	

	/**
	 * Constructor to initialize the FlipkartHomePage elements.
	 */
	public ViewCartPage() {

		this.driver = BrowserFactory.getDriver();
		this.wait = BrowserFactory.getWait();
		

		PageFactory.initElements(driver, this);
	}

	

	@FindBy(xpath = "//button[text()='Enter Delivery Pincode']")
	private WebElement enterDeliveryPinCode;
	
	@FindBy(xpath = "(//button[text()='Change']")
	private WebElement pinCodeButton;
	
	@FindBy(xpath = "//input[@placeholder='Enter pincode']")
	private WebElement enterPincodePlaceholder;
	
	@FindBy(xpath = "//div[text()='Submit']")
	private WebElement clickPinSubmitButton;
	
	@FindBy(xpath = "//div[contains(@class, 'cPHDOP')][.//div[contains(text(), 'Delivery by')]]")
	private List<WebElement> availableProductForDelivery;
	
	public void clickDeliveryCodeButton() {
		wait.until(ExpectedConditions.visibilityOf(enterDeliveryPinCode)).click();
	}


	public void enterPinCode() {
		wait.until(ExpectedConditions.visibilityOf(enterPincodePlaceholder)).click();
		enterPincodePlaceholder.sendKeys(ConfigReader.getProperties("defaultPincode"));
	}
	
	public void clickOnPinCodeSubmitButton() {
		wait.until(ExpectedConditions.visibilityOf(clickPinSubmitButton)).click();
	}
	
	public void printDeliveryProductName() {
		
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(availableProductForDelivery));
			
			for (WebElement productName : availableProductForDelivery ) {
				String fullProductDetails = productName.getText();
				String productInfo = fullProductDetails.split("Seller:")[0].trim();
				System.out.println("Available product names : " + productInfo);
			}
		}catch (StaleElementReferenceException e) {
	        System.out.println("Element went stale...");

		}
		
		
	}

	
































}
