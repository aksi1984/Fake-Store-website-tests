package com.commonstepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import qa.enums.AddressFormMethods;
import qa.enums.URLs;
import qa.support.AddressFormHandler;
import qa.support.CreditCardFormFiller;
import qa.pages.checkout.CheckoutPage;
import qa.testutil.TestUtil;

import java.lang.reflect.InvocationTargetException;

public class CheckoutCommonSteps {

    private final TestUtil testUtil;

    public CheckoutCommonSteps(TestUtil testUtil) {

        this.testUtil = testUtil;
    }

    @When("The user clicks the \"Kupuję i płacę\" button")
    @And("Clicks the \"Kupuję i płacę\" button")
    public void clicksTheSubmitButton() throws InterruptedException {

        CheckoutPage checkoutPage = new CheckoutPage(testUtil.getDriver());
        checkoutPage.clickSubmitButton();

        Thread.sleep(6000);
    }

    @Then("The message about blank 'Imię' field on the checkout page has been displayed")
    @Then("The message about blank 'Nazwisko' field on the checkout page has been displayed")
    @Then("The message about blank 'Ulica' field on the checkout page has been displayed")
    @Then("The message about blank 'Kod pocztowy' field on the checkout page has been displayed")
    @Then("The message about blank 'Miasto' field on the checkout page has been displayed")
    @Then("The message about blank 'Telefon' field on the checkout page has been displayed")
    @Then("The message about blank 'Adres email' field on the checkout page has been displayed")
    @Then("A message about not accepting the terms was displayed")
    public void messageOnTheCheckoutPageHasBeenDisplayed() {

        try {
            CheckoutPage checkoutPage = new CheckoutPage(testUtil.getDriver());
            checkoutPage.waitForErrorMessageLocator();
        } catch (IllegalAccessException e) {
            Assert.fail("The message is not displayed");
        }
    }


    @And("The payment details form is filled")
    public void paymentDetailsFormIsFilled() throws IllegalAccessException, InvocationTargetException {

        AddressFormHandler.fill(testUtil.getDriver(), AddressFormMethods.ALL);
    }

    @And("The credit card form is filled")
    public void creditCardFormIsFilled() {

        CreditCardFormFiller.fill(testUtil.getDriver());
    }

    @And("The checkout page is open")
    public void theCheckoutPageIsOpen() {

        testUtil.goToUrl(URLs.CHECKOUT_PAGE.getName());
    }

    @And("Fills the credit card form")
    public void fillsTheCreditCardForm() {

        CreditCardFormFiller.fill(testUtil.getDriver());
    }

    @And("Accepts terms")
    public void acceptsTerms() {

        CheckoutPage checkoutPage = new CheckoutPage(testUtil.getDriver());
        checkoutPage.clickTermsCheckbox();
    }

    @And("The error message on the checkout page is {string}")
    public void errorMessageOnTheCheckoutPageIs(String message) {

        CheckoutPage checkoutPage = new CheckoutPage(testUtil.getDriver());
        Assert.assertEquals(checkoutPage.getErrorMessageText(), message);
    }
}
