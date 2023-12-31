package qa.pages.productthumbnail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.base.BasePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThumbnailProvider extends BasePage {

    private static final String xpathPart = ".//section[@class='storefront-product-section storefront";
    private static final Map<String, String> categoriesMap = new HashMap<>() {{
            put("Nowości", "-recent-products']");
            put("Popularne", "-popular-products']");
            put("W promocji", "-on-sale-products']");
            put("Bestsellery", "-best-selling-products']");
    }};

    public ThumbnailProvider(WebDriver driver) {

        super(driver);
    }

    public static ProductThumbnail getThumbnail(WebDriver driver, String category, int number) {

        ProductThumbnail productThumbnail = new ProductThumbnail(driver);

        WebElement cat = driver.findElement(By.xpath(xpathPart + categoriesMap.get(category)));
        List<WebElement> products = cat.findElements(By.xpath(".//li[@class]"));

        WebElement product = products.get(number);
        WebElement title = product.findElement(By.xpath(".//h2[@class='woocommerce-loop-product__title']"));
        WebElement price = product.findElement(By.xpath(".//span[@class='price']"));
        WebElement addToCartButton = product.findElement(By.linkText("Dodaj do koszyka"));

        productThumbnail.setProduct(products.get(number));
        productThumbnail.setTitle(title);
        productThumbnail.setPrice(price);
        productThumbnail.setAddToCartButton(addToCartButton);

        return productThumbnail;
    }
}
