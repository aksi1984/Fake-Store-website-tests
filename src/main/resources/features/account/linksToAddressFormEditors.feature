Feature: Billing address form

  Background:
    Given The user is logged in with email: "my_email@gmail.com" and password: "secret_password"
    And The address editing section is open

  Scenario Outline: Link to the billing address form editor
    When An user clicks the symbol in the <columnName> column
    Then The page with url <url> has been opened

    Examples:
    |columnName           |url                                                                   |
    |"Adres rozliczeniowy"|"https://fakestore.testelka.pl/moje-konto/edytuj-adres/rozliczeniowy/"|
    |"Adres do wysyłki"   |"https://fakestore.testelka.pl/moje-konto/edytuj-adres/przesylki/"    |

