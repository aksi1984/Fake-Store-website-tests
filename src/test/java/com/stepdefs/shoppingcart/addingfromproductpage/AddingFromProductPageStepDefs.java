package com.stepdefs.shoppingcart.addingfromproductpage;

import qa.pages.productpage.ProductPage;
import qa.pages.shoppingcart.ShoppingCart;
import qa.pages.siteheadercart.SiteHeaderCart;
import qa.testutil.TestUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import qa.models.ProductData;


public class AddingFromProductPageStepDefs {

    private final TestUtil testUtil;
    private final ProductPage productPage;
    private final SiteHeaderCart siteHeaderCart;
    private final ShoppingCart shoppingCart;

    public AddingFromProductPageStepDefs(TestUtil testUtil) {

        this.testUtil = testUtil;

        productPage = new ProductPage(testUtil.getDriver());
        siteHeaderCart = new SiteHeaderCart(testUtil.getDriver());
        shoppingCart = new ShoppingCart(testUtil.getDriver());
    }

    @When("The user clicks the \"Dodaj do koszyka\" button")
    public void theUserClicksTheAddToCartButton() {

        productPage.clickAddToCartButton();
        ProductData.setName(productPage.getProductTitle());
        ProductData.setPrice(productPage.getPrice());
    }

    @Then("The product has been added to the shopping cart")
    public void theProductHasBeenAddedToTheShoppingCart() {

        Assert.assertNotEquals(siteHeaderCart.getPrice(), "0,00 zł");
    }

    @And("The message about adding the product to the cart was displayed")
    public void theMessageWasDisplayed() {

        try {
            productPage.waitForMessage();
        } catch (Exception e) {
            Assert.fail("The message is not displayed");
        }
    }
}
