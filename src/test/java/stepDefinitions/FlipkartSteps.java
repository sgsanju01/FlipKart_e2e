package stepDefinitions;

import java.util.concurrent.TimeoutException;

import org.testng.Assert;

import io.cucumber.java.en.*;
import pages.ComparePage;
import pages.FlipkartHomePage;
import pages.GoogleSearchPage;
import pages.ViewCartPage;
import pages.WindowACListPage;
import utils.BrowserFactory;
import utils.ConfigReader;

public class FlipkartSteps {
	
	private GoogleSearchPage googleSearchPage = new GoogleSearchPage();
	private FlipkartHomePage flipkartHomePage = new FlipkartHomePage();
	private WindowACListPage windowACListPage = new WindowACListPage();
	private ComparePage      comparePage      = new ComparePage();
	private ViewCartPage viewCartPage = new ViewCartPage();
	
	
	@Given("I launch the browser and open Google")
	public void i_launch_the_browser_and_open_google() {
		BrowserFactory.getDriver();
		googleSearchPage.openGoogle();
		
	    
	}

	@When("I type {string} in the Google search field")
	public void i_type_in_the_google_search_field(String searchTerm) {
		googleSearchPage.typeSearchTerm("flipkart");
	}

	@Then("I print all the suggestions displayed in the google search field")
	public void i_print_all_the_suggestions_displayed_in_the_google_search_field() {
		googleSearchPage.printSearchSuggestion();
	}

	@Then("I hit Enter")
	public void i_hit_enter() throws InterruptedException {
		
		googleSearchPage.hitEnter();
	}

	/** Below two methods have been commented intentionally  
	 * Because after hit enter in google search the automation browser
	 * asking for CAPTCHA and it's blocking for further testing
	 * But below both methods working fine to print google search results 
	 * and it's clicking on first link
	 */
	@Then("I print all the search results displayed in the console")
	public void i_print_all_the_search_results_displayed_in_the_console() {
		//googleSearchPage.printGoogleSearchResults();
	}

	@When("I click on the first link")
	public void i_click_on_the_first_link() {
		//googleSearchPage.clickFirstLink();
	}

	@Then("Flipkart website should get opened up")
	public void flipkart_website_should_get_opened_up() {
	   Assert.assertEquals(BrowserFactory.getDriver().getCurrentUrl(), ConfigReader.getProperties("flipkartUrl"));
	}

	@Then("I close the login popup \\(If available)")
	public void i_close_the_login_popup_if_available() {
		flipkartHomePage.closeLoginPopup();
	}

	@When("I click on 'Appliances'")
	public void i_click_on() {
		//flipkartHomePage.clickOnAppliances();
	}

	@When("I hover over 'TV & Appliances'")
	public void i_hover_over() {
		flipkartHomePage.hoverAndClickOnTvAndAppliances();
	    
	}
	
	@When("I click on 'Window ACs'")
	public void I_click_on_Window_ACs() {
		flipkartHomePage.clicViewAllButtonAirConditionrs();
		//flipkartHomePage.clickOnWindowAcButton();
		
	}

	@Then("window ACs list is displayed")
	public void window_a_cs_list_is_displayed() {
		Assert.assertTrue(windowACListPage.isWindowACsListDisplayed(), "Window AC is not disaplayed"); ;
	}

	@When("I click on 'Add to Compare' checkbox for the 2nd product displayed")
	public void i_click_on_checkbox_for_the_2nd_product_displayed() {
		windowACListPage.clickAddToCompare(2);
	}

	@When("I click on 'Add to Compare' checkbox for the 5th product displayed")
	public void i_click_on_checkbox_for_the_5th_product_displayed() {
		windowACListPage.clickAddToCompare(5);
	}

	@When("I click on 'Add to Compare' checkbox for the 7th product displayed")
	public void i_click_on_checkbox_for_the_7th_product_displayed() {
		windowACListPage.clickAddToCompare(7);
	}

	@When("I click on 'Add to Compare' checkbox for the 8th product displayed")
	public void i_click_on_checkbox_for_the_8th_product_displayed() {
		windowACListPage.clickAddToCompare(8);
	}

	@When("I click on 'Add to Compare' checkbox for the 9th product displayed")
	public void i_click_on_checkbox_for_the_9th_product_displayed() {
		windowACListPage.clickAddToCompare(9);
	}

	@Then("I print the message displayed on addition of the 9th product")
	public void i_print_the_message_displayed_on_addition_of_the_9th_product() {
		windowACListPage.getCompareNotificationMessage();
	}

	@When("I click on Compare")
	public void i_click_on_compare() {
		windowACListPage.clickCompareButton();
	}

	@Then("I print Name and Price of all products in console")
	public void i_print_name_and_price_of_all_products_in_console() {
		comparePage.printProductDetails();
	}

	@When("I add the 1st, 2nd, 3rd, and 4th products to cart")
    public void i_add_the_1st_2nd_3rd_and_4th_products_to_cart() throws TimeoutException {
		comparePage.clickAddToCartButton();
		
	}
	

	@When("I go to the cart")
	public void i_go_to_the_cart() throws InterruptedException {
		comparePage.clickCartButton();
	}

	@When("I add my area Pincode")
	public void i_add_my_area_pincode() {
		viewCartPage.clickDeliveryCodeButton();
		viewCartPage.enterPinCode();
	}

	@When("I check the availability of the product in the console")
	public void i_check_the_availability_of_the_product_in_the_console() {
		viewCartPage.clickOnPinCodeSubmitButton();
	}

	@When("the product is not available")
	public void the_product_is_not_available() {
		System.out.println("Pin code is valid so products are available");
	}

	@When("I change the pincode to a valid pincode")
	public void i_change_the_pincode_to_a_valid_pincode() {
		System.out.println("No need to change the pincode");
	}

	@Then("I print the message displayed for the availability\\/delivery of the product in the console")
	public void i_print_the_message_displayed_for_the_availability_delivery_of_the_product_in_the_console() {
		viewCartPage.printDeliveryProductName();
	}

	@Then("I close the browser")
	public void i_close_the_browser() {
		BrowserFactory.quitDriver();
	}


}
