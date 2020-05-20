Feature: C description

  Scenario Outline: C scenario
    When user open the homepage
    And add <products> different products to the cart
    Then total sum is correct
    When user remove any <items> different products
#    Then products are removed from the cart
#    And total sum is correct

    Examples:
      | products | items |
      | 3        | 2     |