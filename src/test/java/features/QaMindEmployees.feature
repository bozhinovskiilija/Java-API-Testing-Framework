Feature: Employees Card

  Scenario: List all employees
    Given There are employees
    When I fetch all employees
    Then The employees are listed

  Scenario: Create an employee
    Given The POST endpoint and the request payload
    When I send a POST request for creating an employee
    Then The employee is successfully created