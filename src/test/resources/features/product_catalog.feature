Feature: Product Catalog

  As a Customer i want to easily search, filter and sort products in the catalog so that
  I can find what I am looking for quickly and efficiently.


  Scenario:  Customer search for a product by name
  #Rule: Customer should be able to search product by name
    Given Sally is on Home page
    When Sally searches for "pliers"
    Then "pliers" should be displayed
#    Examples: The Sally search for an adjustable wrench


#  Scenario: Add a new product to the catalog
#    Given I am on the product catalog page
#    When I add a new product with name "Laptop", price "999.99", and description "High-performance laptop"
#    Then the product "Laptop" should be listed in the catalog

#  Scenario: Edit an existing product in the catalog
#    Given I am on the product catalog page
#    And there is a product named "Laptop" in the catalog
#    When I edit the product "Laptop" to have a new price "899.99"
#    Then the product "Laptop" should have the updated price "899.99"
#
#  Scenario: Delete a product from the catalog
#    Given I am on the product catalog page
#    And there is a product named "Laptop" in the catalog
#    When I delete the product "Laptop"
#    Then the product "Laptop" should no longer be listed in the catalog