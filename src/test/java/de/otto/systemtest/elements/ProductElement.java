package de.otto.systemtest.elements;

import de.otto.systemtest.data.ProductEqualsData;
import de.otto.systemtest.pages.DetailPage;
import de.otto.systemtest.utils.CurrencyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.money.MonetaryAmount;

/**
 * Abstract product element object.
 */
public abstract class ProductElement {

    protected WebDriver driver;
    protected WebElement name;
    protected WebElement price;

    /**
     * Gets name of product.
     *
     * @return Name of product
     */
    public String getName() {
        return this.name.getText();
    }

    /**
     * Gets price of product.
     *
     * @return Monetary amount of price
     */
    public MonetaryAmount getPrice() {
        final String priceString = this.price.getText();
        final MonetaryAmount result = CurrencyUtils.parseMonetaryAmount(priceString);

        return result;
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

    /**
     * Gets a detail page of category product.
     *
     * @return Detail page of category product
     */
    public DetailPage goToDetailPage() {
        this.name.click();
        final DetailPage result = new DetailPage(driver);

        return result;
    }
}
