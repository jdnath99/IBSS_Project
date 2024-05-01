Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

    Scenario: Creating New Password successfully
      Given User is on the Change Password screen
      When Enter existing valid username and password
      And Enter a new password of length 3 characters and one digit in the New Password field
      And Enter same new password in the Verify Password Field
      And Click on Submit button
      Then Password should be changed successfully

     Scenario Outline: Validating the Change Password functionality
      Given User is on the Change Password screen
      When Enter existing valid username and password
      And Enter a new password in "<New Password>" and "<Verify Password>" field
      And Click on Submit button
      Then Error message  should be shown
      Examples:
        |New Password  |Verify Password|
        |ABCD123        |      ABCD     |
        |AB            |AB             |
        |Testing      |Testing         |
        |2323       |2323              |