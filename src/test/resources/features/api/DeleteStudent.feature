# 020720: #1 from here -------------------------------
@api @delete_student
Feature: Delete Student

    Scenario Outline: Delete student as <role> and verify status code 403 (negative)
      Given authorization token is provided for "<role>"
      And user accepts content type as "application/json"
      When user sends DELETE request to "/api/students/5743" to exclude student
      And user verifies that response status code is 403
      And user verifies that status line contains "Forbidden"

      Examples: roles
        | role        |
        | team member |
        | tema leader |

# #1 till here -------------------------------------------

# #2 stars -------------------------------------------------
 # pay attention on student's ids bc you can delete only once.
  # So, the id you want to delete should be changed
  @ignore
  Scenario: Delete student as teacher and verify status code 204 (positive, it fails unless you provide valid id)
    Given authorization token is provided for "teacher"
    And user accepts content type as "application/json"
    When user sends DELETE request to "/api/students/1840" to exclude student
    And user verifies that response status code is 204

# #2 till here -------------------------------------------

# #3 starts ------------------------------------------------
  Scenario: Try to delete student with incorrect id and verify that status code 422 (negative)
    Given authorization token is provided for "teacher"
    And user accepts content type as "application/json"
    When user sends DELETE request to "/api/students/0" to exclude student
    And user verifies that response status code is 422
    And user verifies that payload contains "requested student-id resource was not found." message

# #3 till here -------------------------------------------