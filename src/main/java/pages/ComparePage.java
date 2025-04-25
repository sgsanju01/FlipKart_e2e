package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BrowserFactory;

public class ComparePage {

	private WebDriver driver;
	private WebDriverWait wait;

	/**
	 * Constructor to initialize the FlipkartHomePage elements.
	 */
	public ComparePage() {

		this.driver = BrowserFactory.getDriver();
		this.wait = BrowserFactory.getWait();

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//div[@class='row']//div[a[@class='NKi0M6']])[position() > last() - 4]/a")
	private List<WebElement> ACsProductName;

	@FindBy(xpath = "(//div[@class='row'][div/div/div[@class='Nx9bqj']])[last()]//div[contains(@class,'col-1-5')]//div[@class='Nx9bqj']")
	private List<WebElement> ACsProductPrice;

	@FindBy(xpath = ".//button[contains(text(),'Add to cart')]")
	private List<WebElement> addToCartButtons;

	@FindBy(xpath = "//button[contains(text(),'GO TO CART') and not (@disabled)]")
	private WebElement goToCartButton;

	@FindBy(xpath = "//span[text()='Place Order']")
	private WebElement placeOrderButton;

	@FindBy(xpath = "//span[text()='Cart']")
	private WebElement cartLogoButton;

	public void printProductDetails() {
		wait.until(ExpectedConditions.visibilityOfAllElements(ACsProductName));
		wait.until(ExpectedConditions.visibilityOfAllElements(ACsProductPrice));
		System.out.println("\n--- Products on Compare Page ---");

		for (int i = 0; i < ACsProductName.size(); i++) {

			System.out.println(
					i + 1 + ": " + ACsProductName.get(i).getText() + ", Price: " + ACsProductPrice.get(i).getText());
		}

	}

	/**
	 * Clicks on the Add to Cart buttons sequentially, handles navigation to cart,
	 * and navigates back.
	 */
	public void clickAddToCartButton() {
		for (int i = 1; i <= 4; i++) {

			List<WebElement> addToCartButtons = driver.findElements(By.cssSelector(".col.col-1-5.PK-Chh > button"));
			if (addToCartButtons.size() >= i) { // Check if there are enough buttons
				WebElement addButton = addToCartButtons.get(i - 1); // 0-based index
				//Skip if the button is not enabled (disabled/sponsored)
				if (!addButton.isEnabled()) {
					System.out.println("Add to cart button is disabled for index: " + i + " (Skipping sponsored?)");
					continue;
				}
				wait.until(ExpectedConditions.elementToBeClickable(addButton)); // Wait for it to be clickable
				System.out.println("adding product for --> " + i);
				addButton.click();

				// Wait for navigation to cart or Place Order button
				try {
					wait.until(ExpectedConditions.or(ExpectedConditions.urlContains("/viewcart/"),
							ExpectedConditions.visibilityOf(placeOrderButton)));

					if (driver.getCurrentUrl().contains("/viewcart/")) {
						// System.out.println("Navigated to Cart. Navigating back...");
						driver.navigate().back();
						wait.until(ExpectedConditions.urlContains("/compare")); // Wait to be back on compare
					} else if (placeOrderButton.isDisplayed()) {
						System.out.println("Navigated to Place Order. Navigating back...");
						driver.navigate().back();
						wait.until(ExpectedConditions.urlContains("/compare"));
					} else {
						System.out.println(
								"Neither Cart nor Place Order page detected after clicking Add to Cart for product "
										+ i);
						driver.navigate().back();
						wait.until(ExpectedConditions.urlContains("/compare"));
					}

				} catch (org.openqa.selenium.TimeoutException e) {
					System.out.println("Timeout waiting for Cart or Place Order page after adding product " + i);
					driver.navigate().back();
					wait.until(ExpectedConditions.urlContains("/compare"));
				}

			}
			System.out.println("Finished adding products to cart...");
		}

	}

	public void clickCartButton() {
		wait.until(ExpectedConditions.visibilityOf(cartLogoButton)).click();

	}

}
