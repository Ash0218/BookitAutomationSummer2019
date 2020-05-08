# 020720: #1 from here -------------------------------
@delete_student
Feature: Delete Student

    Scenario Outline: Delete student as <role> and verify status code 403 (negative)
      Given authorization token is provided for "<role>"
      And user accepts content type as "application/json"
      When user sends DELETE request to "/api/students/{id}" to exclude student
      And user verifies that response status code is 403
      And user verifies that status line contains "Forbidden"

      Examples: roles
        | role        |
        | team member |
        | tema leader |



# #1 till here -------------------------------------------