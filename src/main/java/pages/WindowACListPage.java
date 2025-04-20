package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BrowserFactory;

public class WindowACListPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;

	/**
	 * Constructor to initialize the FlipkartHomePage elements.
	 */
	public WindowACListPage() {

		this.driver = BrowserFactory.getDriver();
		this.wait = BrowserFactory.getWait();
		this.actions = new Actions(driver);

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cPHDOP col-12-12']//div[@class='KzDlHZ']")
	private WebElement windowACsTitle;

	@FindBy(xpath = "//div[@data-id][%d]//span[contains(text(),'Add to Compare')]")
	private WebElement addToCompareCheckboxByIndex;

	@FindBy(xpath = "//div[text()='You have already selected 4 products']")
	private WebElement compareNotification;

	@FindBy(xpath = "//span[text()='COMPARE']")
	private WebElement compareButton;

	public boolean isWindowACsListDisplayed() {
		return wait.until(ExpectedConditions.visibilityOf(windowACsTitle)).isDisplayed();
	}

	public void clickAddToCompare(int index) {

		WebElement checkbox = driver.findElement(
				By.xpath(String.format("(//div[@data-id]//span[contains(text(),'Add to Compare')])[%d]", index)));
		wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
	}

	public void getCompareNotificationMessage() {
		System.out.println(wait.until(ExpectedConditions.visibilityOf(compareNotification)).getText());
	}

	public void clickCompareButton() {
		wait.until(ExpectedConditions.visibilityOf(compareButton)).click();
	}

}
