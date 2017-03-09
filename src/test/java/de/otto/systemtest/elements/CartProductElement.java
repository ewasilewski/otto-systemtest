package de.otto.systemtest.elements;

import de.otto.systemtest.data.ProductEqualsData;
import de.otto.systemtest.utils.CurrencyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.money.MonetaryAmount;

/**
 * Cart product bean.
 *
 * It contains product information for products displayed on the cart page.
 */
public class CartProductElement {

    private final WebDriver driver;
    private final WebElement name;
    private final WebElement price;
    private final WebElement quantity;
    private final WebElement totalPrice;
    private final WebElement deleteProductButton;

    /**
     * Gets {@link WebDriver} and loaded product displayed on the cart page.
     *
     * @param driver Already loaded {@code WebDriver}
     * @param element Product from the cart page to load
     */
    public CartProductElement(final WebDriver driver,final WebElement element) {
        this.driver = driver;
        this.name = element.findElement(By.xpath(".//div[@data-qa='articleName']/a"));
        this.price = element.findElement(By.xpath(".//div[@data-qa='unitPrice']"));
        this.quantity = element.findElement(By.xpath(".//option[@selected]"));
        this.totalPrice = element.findElement(By.className("or_basketItem__currentPrice"));
        this.deleteProductButton = element.findElement(By.xpath("(.//button[contains(@title, 'Position löschen')])[2]"));

    }


    /**
     * Gets product name.
     *
     * @return Product name
     */
    public String getName() {
        return this.name.getText();
    }

    /**
     * Gets product price.
     *
     * @return Monetary amount of  price
     */
    public MonetaryAmount getPrice() {
        final String priceString = this.price.getText();
        final MonetaryAmount amount = CurrencyUtils.parseMonetaryAmount(priceString);

        return amount;
    }

    /**
     * Gets quantity.
     *
     * @return Product quantity
     */
    public int getProductQuantity() {
        return Integer.parseInt(this.quantity.getText());
    }

    /**
     * Gets total price of product.
     *
     * @return Monetary amount of total price
     */
    public MonetaryAmount getTotalPrice() {
        final String priceString = this.totalPrice.getText();
        final MonetaryAmount amount = CurrencyUtils.parseMonetaryAmount(priceString);

        return amount;
    }

    /**
     * Click on "x" - delete product button.
     */
    public void deleteProduct() {
        final Actions builder = new Actions(driver);
        builder.moveToElement(deleteProductButton);
        builder.perform();
        deleteProductButton.click();
    }

    /**
     * Gets a data slice for product equality test of products of different types.
     *
     * @return ProductEqualsData to check product equality
     */
    public ProductEqualsData getProductEqualsData() {
        final String name = getName();
        final MonetaryAmount price = getPrice();

        return new ProductEqualsData(name, price);
    }
}
