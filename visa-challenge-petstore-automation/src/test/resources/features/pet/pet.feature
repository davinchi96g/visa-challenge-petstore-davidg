Feature: Pet End to End flow

  Scenario: Add a new pet to the Store
    Given the following data for the pet:
      | id | name  | categoryId | categoryName | photoUrl   | tagId | tagName | status    |
      | 10 | David | 10         | Dogs         | TestString | 10    | string  | available |
    When I record the pet
    Then the response message should have a valid body
  @ApiTest
  Scenario: find a pet by id
    Given the following data for searching the pet:
      | id |
      | 10 |
    Then the response message should have a valid body for the query

  Scenario: find a pet by tag
    Given the following data for searching the pet byTag:
      | tag  |
      | tag1 |
    Then the response message should have a valid body for the query byTag



