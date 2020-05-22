#Feature: C description
#
#  Scenario Outline: C scenario
#    When user open the homepage
#    And add <products> different products to the cart
#    Then total sum is correct
#    When user remove any <items> different products
#    Then products qty is <qty> in the cart
#    And total sum is correct
#
#    Examples:
#      | products | items | qty |
#      | 5        | 2     | 3   |