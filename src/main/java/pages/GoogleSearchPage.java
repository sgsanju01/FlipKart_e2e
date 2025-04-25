package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BrowserFactory;
import utils.CommonElementAction;
import utils.ConfigReader;

public class GoogleSearchPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private CommonElementAction commonElementAction;
	

	/**
	 * Constructor to initialize the GoogleSearchPage elements.
	 */
	public GoogleSearchPage() {

		this.driver = BrowserFactory.getDriver();
		this.wait = BrowserFactory.getWait();
		PageFactory.initElements(driver, this);
		this.js = (JavascriptExecutor) driver;
		this.commonElementAction = new CommonElementAction(driver,wait);
	}

	@FindBy(xpath = "//textarea[@name='q']")
	private WebElement searchBox;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	private List<WebElement> searchSuggestions;

	@FindBy(xpath = "//div[@id='search']//h3/a")
	private List<WebElement> googleSearchResults;
	
	@FindBy(css = "div.pXnwx > promo-button-text:nth-of-type(1) div[role='button'] span.YrSbJc")
	private WebElement staySignOutButton;

	/*
	 * Opens the google.com
	 */
	public void openGoogle() {
		driver.get(ConfigReader.getProperties("googleUrl"));
		
		try {
			WebElement signOutBtn = commonElementAction.elementToBeClick(staySignOutButton);
			System.out.println("Signout pop up is visiable ");
			js.executeScript("arguments[0].click()", signOutBtn);
			//signOutBtn.click();
			System.out.println("clicked on it");
		}catch(TimeoutException e) {
			
			System.out.println("Signout pop up was not visible or clickable within the timeout.");
			
		}
	}

	/*
	 * Enter customise search term into the google search box
	 */
	public void typeSearchTerm(String searchTerm) {
		commonElementAction.elementToBeClickWithText(searchBox, searchTerm);
	}

	/*
	 * Print the all suggestions for specify keyword
	 */
	public void printSearchSuggestion() {
		
		wait.until(ExpectedConditions.visibilityOfAllElements(searchSuggestions));
		System.out.println("--- Google Search Suggestions for 'Flipkart' ---");
		for (WebElement suggestion : searchSuggestions) {
			System.out.println(suggestion.getText());
		}
	}

	/*
	 * Hit enter to navigate Search result page
	 */

	public void hitEnter() throws InterruptedException {

		// searchBox.sendKeys(Keys.ENTER);
		driver.get(ConfigReader.getProperties("flipkartUrl"));
	}

	/*
	 * Print google search results
	 */

	public void printGoogleSearchResults() {
		commonElementAction.visibilityOfAllElements(googleSearchResults);
		System.out.println("Google search results are : ");
		for (WebElement searchResult : googleSearchResults) {
			System.out.println(searchResult.getText());
		}
	}

	/*
	 * Click first search result link
	 */

	public void clickFirstLink() {
		wait.until(ExpectedConditions.elementToBeClickable(googleSearchResults.get(0))).click();
	}

}
