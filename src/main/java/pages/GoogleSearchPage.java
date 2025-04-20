package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BrowserFactory;
import utils.ConfigReader;

public class GoogleSearchPage {

	private WebDriver driver;
	private WebDriverWait wait;

	/**
	 * Constructor to initialize the GoogleSearchPage elements.
	 */
	public GoogleSearchPage() {

		this.driver = BrowserFactory.getDriver();
		this.wait = BrowserFactory.getWait();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//textarea[@name='q']")
	private WebElement searchBox;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	private List<WebElement> searchSuggestions;

	@FindBy(xpath = "//div[@id='search']//h3/a")
	private List<WebElement> googleSearchResults;

	/*
	 * Opens the google.com
	 */
	public void openGoogle() {
		driver.get(ConfigReader.getProperties("googleUrl"));
	}

	/*
	 * Enter customise search term into the google search box
	 */
	public void typeSearchTerm(String searchTerm) {
		wait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(searchTerm);
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
		wait.until(ExpectedConditions.visibilityOfAllElements(googleSearchResults));

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
