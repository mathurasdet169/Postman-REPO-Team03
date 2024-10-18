Feature: User API Scenarios

  Scenario Outline: POST Scenarios
    Given User set request body from data file with scenario "<scenario>" and endpoint "<endpoint>"
    When user sends API post request
    Then response body and response code <code> is validated

    Examples: 
      | scenario                     | code | endpoint    |
      | post_postiveAllFields        |  201 | createusers |
      | post_alphanumericFirstName   |  400 | createusers |
      | post_emptyLastName           |  400 | createusers |
      | post_contactNumberMoreThan10 |  400 | createusers |
      | post_sameEmailId             |  409 | createusers |
      | post_postiveAllFields        |  404 | cret        |
      | post_postiveAllFields        |  404 |             |
      | post_contactNumberLessThan10 |  400 | createusers |
      | post_emailastrtswith@        |  400 | createusers |
      | post_emailendswith@          |  400 | createusers |
      | post_emptyEmail              |  400 | createusers |
      | post_emptyContactNo          |  400 | createusers |
      | post_streetempty             |  400 | createusers |
      | post_statempty               |  400 | createusers |
      | post_countryEmpty            |  400 | createusers |

  Scenario Outline: GETAllUsers
    Given User set GET request with endpoint "<endpoint>"
    When User send GET request
    Then response should be validated for statuscode <code>, content-type

    Examples: 
      | endpoint                   | code |
      | users                      |  200 |
      | ght4                       |  404 |
      |                            |  404 |
      | deleteuser/username/Kalyan |  405 |

  Scenario Outline: PUT Scenarios
    Given User set request body  for PUT from data file with scenario "<scenario>" and endpoint "<endpoint>"
    When user sends API PUT request
    Then response body and response code <code> is validated

    Examples: 
      | scenario                    | code | endpoint   |
      | put_alphanumericlastName    |  400 | updateuser |
      | put_emptyfirstName          |  400 | updateuser |
      | put_contactNumberlessThan10 |  400 | updateuser |
      | put_sameContactNo           |  400 | updateuser |
      | put_postiveAllFields        |  200 | updateuser |
      | put_postiveAllFields        |  404 |            |
      | put_postiveAllFields        |  404 | updat      |
      | put_contactNumberMoreThan10 |  400 | updateuser |
      | put_emailastrtswith@        |  400 | updateuser |
      | put_emailendswith@          |  400 | updateuser |
      | put_emptyEmail              |  400 | updateuser |
      | put_emptyContactNo          |  400 | updateuser |
      | put_streetempty             |  400 | updateuser |
      | put_statempty               |  400 | updateuser |
      | put_countryEmpty            |  400 | updateuser |

  Scenario Outline: GETByUserName
    Given User set GET BY USERNAME request for scenario "<scenario>" with endpoint "<endpoint>"
    When User send GET request by username "<type>"
    Then response should be validated for statuscode <code>, message "<message>"

    Examples: 
      | endpoint                   | code | message            | scenario         | type     |
      | users/username/            |  200 | OK                 | positive         | positive |
      | users/username/&^%         |  400 | Bad Request        | Invalid username | negative |
      | users/use/Numpy            |  404 | Not Found          | Invalid endpoint | negative |
      | users/username/            |  404 | Not Found          | Empty username   | negative |
      | /Numpy                     |  404 | Not Found          | Empty endpoint   | negative |
      | deleteuser/username/Kalyan |  405 | Method Not Allowed | invalid method   | negative |

  Scenario Outline: DeleteByUserName
    Given User set DELETE request for scenario "<scenario>" with endpoint "<endpoint>"
    When User send DELETE request by userName
    Then response should be validated for statuscode <code>, message "<message>"

    Examples: 
      | endpoint                | code | message     | scenario         |
      | deleteuser/username/&^% |  400 | Bad Request | Invalid userName |
      | deleteuser/username/    |  400 | OK          | EmptyUsername    |
      | deleteuse/use/Kalyan    |  404 | Not Found   | Invalid endpoint |
      | //Kalyan                |  400 | Not Found   | EmptyEndpoint    |
      |                         |  200 | OK          | positive         |

  Scenario Outline: PostByMandatoryFields
    Given User set request body from data file with scenario "<scenario>" and endpoint "<endpoint>"
    When User sends post mandatory fields request with endpoint
    Then response body for mandatory post and response code <code> is validated

    Examples: 
      | scenario                            | endpoint    | code | message     |
      | post_MandatoryFieldPositive         | createusers |  201 | Created     |
      | post_MandatoryAlphaNumericFirstName | createusers |  400 | Bad Request |
      | post_MandatoryEmptyLastName         | createusers |  400 | Bad Request |
      | post_MandatoryFieldPositive         | crea        |  404 | Not Found   |
      | post_MandatoryFieldPositive         |             |  404 | Not Found   |

  Scenario Outline: GETByUserId
    Given User set GET request for scenario "<scenario>" with endpoint "<endpoint>"
    When User send GET request by userID
    Then response should be validated for statuscode <code>, message "<message>"

    Examples: 
      | endpoint     | code | message            | scenario         |
      |              |  200 | OK                 | positive         |
      | user/&^%     |  400 | Bad Request        | Invalid userID   |
      | us/1         |  404 | Not Found          | Invalid endpoint |
      | user/        |  404 | Not Found          | EmptyUserID      |
      | /1           |  404 | NotFound           | EmptyEndpoint    |
      | updateuser/1 |  405 | Method Not Allowed | Invalid method   |

  Scenario Outline: DeleteByUserID
    Given User set DELETE BY USERID request for scenario "<scenario>" with endpoint "<endpoint>"
    When User send DELETE request by userID
    Then response should be validated for statuscode <code>, message "<message>"

    Examples: 
      | endpoint       | code | message            | scenario         |
      | deleteuser/hgt |  400 | Bad Request        | Invalid userID   |
      | us/1           |  404 | Not Found          | Invalid endpoint |
      | user/          |  404 | Not Found          | EmptyUserID      |
      | /1             |  404 | NotFound           | EmptyEndpoint    |
      |                |  200 | OK                 | positive         |
      | updateuser/    |  405 | Method Not Allowed | negative         |
