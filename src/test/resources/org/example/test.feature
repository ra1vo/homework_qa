Feature: Contact endpoint accept data

  Scenario: csv data from file is sent to endpoint succesfully
    Given csv file with 5 persons is created
    When each csv row is sent to endpoint
    Then each response code is 201

    @Negative
  Scenario: empty csv data from file is sent to endpoint
    Given csv file with 0 persons is created
    When each csv row is sent to endpoint
    Then each response code is 500


