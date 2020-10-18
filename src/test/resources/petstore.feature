Feature: API Automation of DEMO PET STORE using RestAssured.

  Scenario: Verify available pets in the store
    Given endpoint v2/pet/findByStatus?status=available
    And method get
    Then status 200
    And assert response
    
    Scenario: Add a new pet to the store
      Given endpoint v2/pet
      * header Content-Type = application/json
      And request {"id": 123919,"category": {"id": 11,"name": "labrador"},"name": "tommy","photoUrls": ["C:/Projects/lk_tester/src/main/resources/Bruno.jpg"],"tags": [{"id": 721,"name": "7B"}],"status": "available"}
      And method post
      Then status 200
      And match response.id = 123919

  Scenario: Mark pet as sold in the store
    Given endpoint v2/pet
    * header Content-Type = application/json
    And request {"id": 123919,"category": {"id": 11,"name": "labrador"},"name": "tommy","photoUrls": ["C:/Projects/lk_tester/src/main/resources/Bruno.jpg"],"tags": [{"id": 721,"name": "7B"}],"status": "sold"}
    And method put
    Then status 200
    And match response.status = sold

  Scenario: Delete a pet by petID from the store
    Given endpoint v2/pet/123919
    * header api_key = key123
    And request {"id": 123919,"category": {"id": 11,"name": "labrador"},"name": "tommy","photoUrls": ["C:/Projects/lk_tester/src/main/resources/Bruno.jpg"],"tags": [{"id": 721,"name": "7B"}],"status": "sold"}
    And method delete
    Then status 200
    And match response.message = 123919
