Feature: Web Automation for Demo Online shop called demoblaze.com
  Description: This contains End to End scenario for placing an order on https://www.demoblaze.com/index.html

  Background: Navigating to Home Page
    Given user is on Home Page

  @web
  Scenario: Verify laptops purchase on demoblaze
    When user navigates to Laptops under categories
    And user navigates to Sony vaio i5 product
    Then user clicks on Add to cart on product page
    And user navigates to HomePage
    When user navigates to Laptops under categories
    And user navigates to Dell i7 8gb product
    Then user clicks on Add to cart on product page
    And user navigates to Cart Page
    And user clicks on Dell i7 8gb delete link
    Then user clicks on Place Order button
    And fill all the form fields
      | Lakshay | India | Delhi | 1234567890123456 | October | 2020 |
    Then user clicks on Purchase button
    And user get purchase id and amount
    Then verify 790 as purchase amount
    And user clicks on OK button