Feature: A description

  Scenario Outline: A scenario
    When user open the homepage
    And open <products> different products
    Then products are visible in the history views

    Examples:
      | products |
      | 2        |