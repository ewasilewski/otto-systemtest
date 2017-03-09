package de.otto.systemtest.pages;

import de.otto.systemtest.elements.CartProductElement;
import de.otto.systemtest.utils.CurrencyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.money.MonetaryAmount;
import java.util.LinkedList;
import java.util.List;

/**
 * Cart page object
 */
public class CartPage extends OttoPage {

    @FindBy(xpath = ".//div[@data-qa='item']")
    List<WebElement> products;

    @FindBy(xpath = ".//div[@data-qa='subTotal']")
    WebElement subtotalPrice;

    @FindBy(xpath = ".//div[@data-qa='shippingCosts']")
    WebElement shippingCharges;

    @FindBy(className = "or_grandTotal__amount")
    WebElement totalAmount;

    /**
     * Gets a {@link WebDriver} with already loaded url ({@link WebDriver#get(String)}
     * and runs {@code FactoryPage(driver, Object)} on it.
     *
     * @param driver Already loaded {@link WebDriver}
     */
    public CartPage(final WebDriver driver) {
        super(driver);
    }

    /**
     * Gets a {@link WebDriver}, loads a {@code url}
     * and runs {@code FactoryPage.initElements(driver, Object)} on it.
     *
     * @param driver Already loaded {@link WebDriver}
     * @param url A url to load.
     */
    public CartPage(final WebDriver driver, final String url) {
        super(driver, url);
    }


    /**
     * Gets products form cart page.
     *
     * @return Cart products
     */
    public List<CartProductElement> getProducts() {
        final List<CartProductElement> result = new LinkedList<>();
        for(WebElement product: products) {
            final CartProductElement cartProduct = new CartProductElement(this.driver, product);
            result.add(cartProduct);
        }
        return result;
    }

    /**
     * Gets subtotal price.
     *
     * @return Monetary amount of subtotal price
     */
    public MonetaryAmount getSubtotalPrice() {
        final String subtotalPriceString = this.subtotalPrice.getText();
        final MonetaryAmount subTotalPrice = CurrencyUtils.parseMonetaryAmount(subtotalPriceString);

        return subTotalPrice;
    }

    /**
     * Gets shipping charges.
     *
     * @return Shipping charges
     */
    public MonetaryAmount getShippingCharges() {
        final String shippingChargesString = this.shippingCharges.getText();
        final MonetaryAmount shippingCharges = CurrencyUtils.parseMonetaryAmount(shippingChargesString);

        return shippingCharges;
    }

    /**
     * Gets total amount.
     *
     * @return Monetary amount of total price
     */
    public MonetaryAmount getTotalAmount() {
        final String totalAmountString = this.totalAmount.getText();
        final MonetaryAmount totalAmount = CurrencyUtils.parseMonetaryAmount(totalAmountString);

        return totalAmount;
    }
}
