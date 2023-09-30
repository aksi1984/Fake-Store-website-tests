package steps.couponRedemption;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import qa.enums.Browser;
import qa.pages.ProductsContainer;
import qa.pages.ShoppingCart;
import qa.pages.SiteHeaderCart;

import static qa.driver.Driver.*;
public class CouponRedemptionStepDefs {

    private ShoppingCart shoppingCart;

    @Before
    public void init() {

        createDriver(Browser.CHROME);
        startDriver();

        shoppingCart = new ShoppingCart(getDriver());
    }

    @Given("The product has been added to the shopping cart")
    public void theProductHasBeenAddedToTheShoppingCart() {

        ProductsContainer productsContainer = new ProductsContainer(getDriver());
        productsContainer.setProductThumbnail("Popularne", 1);
        productsContainer.getProductThumbnail().clickAddToCartButton();
        productsContainer.getProductThumbnail().waitForSeeCartButton();
    }

    @And("The shopping cart page is open")
    public void theShoppingCartPagesIsOpen() {

        SiteHeaderCart siteHeaderCart = new SiteHeaderCart(getDriver());
        siteHeaderCart.clickCartMenu();
    }

    @And("Waits for 2 seconds")
    public void waitsFor2Seconds() throws InterruptedException {

        Thread.sleep(2000);
    }

    @When("An user clicks on the coupon code field")
    @And("Clicks on the coupon code field")
    public void anUserClicksOnTheCouponCodeField() {

        shoppingCart.getCouponForm().clickCouponCodeField();
    }

    @And("Enters the {string} as a coupon code")
    @And("Enters the {string} as a coupon code again")
    public void entersTheStringAsACouponCode(String couponCode) {

        shoppingCart.getCouponForm().setCouponCode(couponCode);
    }

    @And("Clicks the 'Zastosuj kupon' button")
    public void clicksTheButton() {

        shoppingCart.getCouponForm().clickApplyCouponButton();
    }

    @Then("The {string} message has been displayed")
    public void theMessageHasBeenDisplayed(String message) {

        Assert.assertTrue(shoppingCart.isMessageVisible());
        Assert.assertEquals(shoppingCart.getMessageText(), message);
    }

    @Then("The {string} error message has been displayed")
    public void theErrorMessageHasBeenDisplayed(String message) {

        Assert.assertTrue(shoppingCart.isErrorMessageVisible());
        Assert.assertEquals(message, shoppingCart.getErrorMessageText());
    }
}
