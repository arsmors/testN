Feature: A description

  Scenario Outline: A scenario
    When user open the homepage
#    And open product
    And open <products> different products
#    And open different products
#    Then products are visible in the history views

    Examples:
      | products |
      | 3        |