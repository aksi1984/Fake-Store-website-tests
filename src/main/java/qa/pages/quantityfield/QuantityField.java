package qa.pages.quantityfield;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import qa.base.BasePage;
import qa.enums.PerformType;


public class QuantityField extends BasePage {

    private final WebElement field;

    public QuantityField(WebDriver driver, WebElement field) {

        super(driver);

        this.field = field;
    }

    public void setQuantity(String quantity) {

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(field));
        field.clear();
        getInteractions().fill(field, PerformType.JS_EXECUTOR, quantity);
    }

    public String getValue() {

        return field.getAttribute("value");
    }

    public String getMin() {

        return field.getAttribute("min");
    }

    public String getMax() {

        return field.getAttribute("max");
    }

    public void waitForValidationMessage() {

        getWebDriverWait().until(ExpectedConditions.attributeToBeNotEmpty(field, "validationMessage"));
    }

    public String getValidationMessageText() {

        return field.getAttribute("validationMessage");
    }
}
