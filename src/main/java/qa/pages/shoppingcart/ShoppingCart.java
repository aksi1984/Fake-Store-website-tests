package qa.pages.shoppingcart;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import qa.base.BasePage;
import qa.enums.PerformType;
import qa.tobyclass.ByFinder;

import java.util.List;

public class ShoppingCart extends BasePage {

    @Getter
    private final Table table;
    @Getter
    private final Row row;
    @Getter
    private final CouponForm couponForm;
    @Getter
    private final ShoppingCartSummary shoppingCartSummary;

    public ShoppingCart(WebDriver driver) {

        super(driver);

        table = new Table(driver);
        row = new Row(driver);
        couponForm = new CouponForm(driver);
        shoppingCartSummary = new ShoppingCartSummary(driver);
    }

    @FindBy(className = "woocommerce-error")
    WebElement errorMessage;
    @FindBy(css = "div.wc-block-components-notice-banner__content")
    List<WebElement> messages;
    @FindBy(css = "button[name='update_cart']")
    WebElement updateCartButton;
    @FindBy(css = "a[class='checkout-button button alt wc-forward']")
    WebElement checkoutButton;


    public String getErrorMessageText() {

        return errorMessage.getText();
    }

    public String getMessageText() {

        return messages.get(0).getText();
    }

    public String getEmptyCartMessageText() {

        return messages.get(1).getText();
    }

    public void clickUpdateCartButton()  {

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(updateCartButton));
        getInteractions().click(updateCartButton, PerformType.JS_EXECUTOR);
    }

    public void clickCheckoutButton() {

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(checkoutButton));
        getInteractions().click(checkoutButton, PerformType.JS_EXECUTOR);
    }

    public void waitForErrorMessage() throws IllegalAccessException {

        getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(ByFinder.getByFromWebElement(errorMessage)));
    }

    public boolean isMessageListNotEmpty() {

        return !messages.isEmpty();
    }

    public boolean hasMessageListMoreThanOneElement() {

        return messages.size() > 1;
    }
}
