Feature: B description

  Scenario Outline: B scenario
    When user open the homepage
    And add <products> different products to the cart
#    Then total sum is correct

    Examples:
      | products |
      | 5        |