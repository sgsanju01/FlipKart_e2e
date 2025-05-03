package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BrowserFactory;
import utils.CommonElementAction;

public class FlipkartHomePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;
	private JavascriptExecutor js;
	private CommonElementAction commonElementAction;
	

	/**
	 * Constructor to initialize the FlipkartHomePage elements.
	 */
	public FlipkartHomePage() {

		this.driver = BrowserFactory.getDriver();
		this.wait = BrowserFactory.getWait();
		this.actions = new Actions(driver);
		this.js = (JavascriptExecutor) driver;
		this.commonElementAction = new CommonElementAction(driver,wait);

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='✕']")
	private WebElement loginCloseButton;

	@FindBy(xpath = "//span[text()='Appliances']")
	private WebElement appliancesLink;

	@FindBy(xpath = "//span[text()='TVs & Appliances']")
	private WebElement tvAndAppliancesMenu;

	@FindBy(xpath = "//a[text()='Window ACs']")
	private WebElement windowAndACsButton;
	
	@FindBy(xpath = "(//span[text()='VIEW ALL'])[1]")
	private WebElement viewAllButtonOfAirConditionrs;

	/**
	 * Closes the login popup if it is displayed on the home page.
	 */
	public void closeLoginPopup() {
		try {
		wait.until(ExpectedConditions.visibilityOf(loginCloseButton)).click();
		} catch (Exception e) {
		System.out.println("Login popup not displayed or could not be closed.");
		}
	}

	
	public void clickOnAppliances() {
		try{
			commonElementAction.visibilityOfElement(appliancesLink);
			js.executeScript("arguments[0].click()", appliancesLink);
		} catch(TimeoutException e) {
			System.out.println("Appliance link was not visible within the timeout");
		}
	}

	
	public void hoverAndClickOnTvAndAppliances() {
		try {
			if(commonElementAction.isElementVisible(appliancesLink)) {
				commonElementAction.visibilityOfElement(appliancesLink);
				js.executeScript("arguments[0].click()", appliancesLink);
				
			}else {
				System.out.println("Appliacne link is not available on flipkart homepage");
			}
			
			commonElementAction.visibilityOfElement(tvAndAppliancesMenu);
			actions.moveToElement(tvAndAppliancesMenu).click().perform();
			//js.executeScript("arguments[0].click()", tvAndAppliancesMenu);
		} catch (TimeoutException e) {
			System.out.println("Tv and Appliance link was not visible within the timeout");
		}
	}

	public void clicViewAllButtonAirConditionrs() {
		try{
			commonElementAction.visibilityOfElement(viewAllButtonOfAirConditionrs).click();
		} catch(TimeoutException e) {
			System.out.println("View all button of Air Conditionrs is not visible within the timeout");
		}
	}
	
	public void clickOnWindowAcButton() {
		try{
			commonElementAction.visibilityOfElement(windowAndACsButton).click();
		} catch(TimeoutException e) {
			System.out.println("windows and ac button not visible within the timeout");
		}
	}
}
