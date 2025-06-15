Feature: Pet End to End flow

  Scenario: Add a new pet to the Store
    Given the following data for the pet:
      | id | name  | categoryId | categoryName | photoUrl   | tagId | tagName | status    |
      | 10 | David | 10         | Dogs         | TestString | 10    | string  | available |
    When I record the pet
    Then the response message should have a valid body

  Scenario: find a pet by id
    Given the following data for searching the pet:
      | id |
      | 10 |
    Then the response message should have a valid body for the query
  Scenario: find a pet by tag
    Given the following data for searching the pet byTag:
      | tag  |
      | tag1 |
    Then the response message should have a valid body for the query Parameter

  Scenario: find a pet by status
    Given the following data for searching the pet byStatus:
      | status    |
      | available |
    Then the response message should have a valid body for the query Parameter

  Scenario: update an existent pet by id
    Given the following data for the pet edition:
      | id | name    | categoryId | categoryName | photoUrl   | tagId | tagName | status    |
      | 10 | Daniela | 10         | Dogs         | TestString | 10    | string  | available |
  When I edit the data of the pet
  Then the response should include the following details:
    | id | name    | categoryId | categoryName | photoUrl   | tagId | tagName | status    |
    | 10 | Daniela | 10         | Dogs         | TestString | 10    | string  | available |

  Scenario: update an existent pet based on form data
    Given I update the the pet with edition on form data:
      | id | name   | status    |
      | 10 | Juanes | available |
    Then the response should include the following details:
      | id | name   | status    |
      | 10 | Juanes | available |

  @ApiTest
  Scenario: Upload image of the pet
    Given the id for uploading the image
      | id |
      | 1  |
    When I send the image "cat.jpg"
    Then the response message should have a valid body for the image upload

  Scenario: delete a pet by ID
    Given the following data for deleting the pet:
      | id |
      | 10 |
    Then the response message should be "Pet deleted"




