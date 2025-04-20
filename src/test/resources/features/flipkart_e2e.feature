Feature: Flipkart e2e Automation Flow

  Scenario: Browse and Compare Window ACs and Check Availability

    Given I launch the browser and open Google
    When I type 'Flipkart' in the Google search field
    Then I print all the suggestions displayed in the google search field
    And I hit Enter
    Then I print all the search results displayed in the console
    When I click on the first link
    Then Flipkart website should get opened up
    And I close the login popup (If available)
    When I click on 'Appliances'
    And I hover over 'TV & Appliances'
    And I click on 'Window ACs'
    Then window ACs list is displayed
    When I click on 'Add to Compare' checkbox for the 2nd product displayed
    And I click on 'Add to Compare' checkbox for the 5th product displayed
    And I click on 'Add to Compare' checkbox for the 7th product displayed
    And I click on 'Add to Compare' checkbox for the 8th product displayed
    And I click on 'Add to Compare' checkbox for the 9th product displayed
    Then I print the message displayed on addition of the 9th product
    When I click on Compare
    Then I print Name and Price of all products in console
    When I add the 1st, 2nd, 3rd, and 4th products to cart
  	And I go to the cart
    And I add my area Pincode
    And I check the availability of the product in the console
    When the product is not available
    And I change the pincode to a valid pincode
    Then I print the message displayed for the availability/delivery of the product in the console
    And I close the browser