Feature: Login Feature

  Scenario: Check display of Cho tot logo
    Given I go to Login page
    Then I should see logo

  Scenario: Check display of title
    Given I go to Login page
    Then I should see title

  Scenario: Check display of phone field
    Given I go to Login page
    Then I should see phone field

  Scenario: Check display of password field
    Given I go to Login page
    Then I should see password field

  Scenario: Check content of phone field
    Given I go to Login page
    Then I should see text "Số điện thoại" in phone label

  Scenario: Check content of password field
    Given I go to Login page
    Then I should see text "Mật khẩu fail" in password label

  Scenario Outline: Click Login button without phone and password
    Given I go to Login page
    When I enter phone <phone> and password <password>
    And I click login button
    Then I should see the required phone message
    And I should see the required password message
    Examples:
      | phone | password |
      | ""    | ""       |

  Scenario Outline: Click Login button without phone and password
    Given I go to Login page
    When I enter phone <phone> and password <password>
    And I click login button
    Then I should see the wrong message
    Examples:
      | phone        | password |
      | "0942741230" | "123456" |

  Scenario Outline: Successful Login
    Given I go to Login page
    When I enter phone <phone> and password <password>
    And I click login button
    Then I should see a get Otp message
    Examples:
      | phone        | password    |
      | "0942741230" | "Abcd@1234" |