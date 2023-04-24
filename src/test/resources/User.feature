Feature: User management

  Scenario: Create new user
    Given Have a user data:
      | name     | email             | password |
      | John Doe | john.doe@test.com | aTr2uc2s |
    When send POST to "/user" with data user
    Then wait 200 code to response

  Scenario: Update user
    Given Have a user data:
      | name     | email             | password |
      | John Doe | john.doe@test.com | aTr2uc2s |
    When send PUT to "/user" with data id "ccbf4523-f84d-40f7-b020-344a00cf9b5a"
    Then wait 200 code to response
