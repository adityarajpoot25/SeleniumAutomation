@Smoke
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario: Customer place an order by purchasing an item from search
    Given user is on Home Page
    When he search for "bags"
    And choose to buy the first item
    And moves to checkout from mini cart
    And enter personal details on checkout page
    And select same delivery address
    And place the order
