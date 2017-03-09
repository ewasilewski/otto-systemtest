package de.otto.systemtest.pages;

import de.otto.systemtest.data.ProductEqualsData;
import de.otto.systemtest.elements.SimilarProductElement;
import de.otto.systemtest.utils.CurrencyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.money.MonetaryAmount;
import java.util.LinkedList;
import java.util.List;

/**
 * Detail page object.
 */
public class DetailPage extends OttoPage{

    @FindBy(xpath = ".//h1[@data-qa='variationName']")
    WebElement name;

    @FindBy(xpath = ".//span[@id='reducedPriceAmount']")
    WebElement price;

    @FindBy(xpath = ".//button[@id='addToBasket']")
    WebElement addToCartButton;

    @FindBy(xpath = "(.//a[@data-qa='goToBasket'])[2]")
    WebElement goToCartButton;

    @FindBy(xpath = "(.//*[@data-qa='continueShopping'])[2]")
    WebElement continueShoppingButton;

    @FindBy(xpath = "(.//*[@class='e_js_productRecommendationText'])")
    List<WebElement> similarProducts;

    /**
     * Gets {@link WebDriver} with already loaded url ({@link WebDriver#get(String)})
     * and runs {@code FactoryPage.initElements(driver, Object)} on it.
     *
     * @param driver Already loaded {@link WebDriver}
     */
    public DetailPage(final WebDriver driver) {
        super(driver);
    }

    /**
     * Gets {@link WebDriver}, loads the {@code url}
     * and runs {@code FactoryPage.initElements(driver, Object)} on it.
     *
     * @param driver Already loaded {@link WebDriver}
     * @param url A url to load
     */
    public DetailPage(final WebDriver driver, final String url) {
        super(driver, url);
    }


    /**
     * Gets name of product.
     *
     * @return Product name
     */
    public String getName() {
        return this.name.getText();
    }

    /**
     * Gets price of product.
     *
     * @return Monetary amout of price
     */
    public MonetaryAmount getPrice() {
        final String priceString = this.price.getText();
        final MonetaryAmount result = CurrencyUtils.parseMonetaryAmount(priceString);

        return result;
    }

    /**
     * Cliks "into cart" - button.
     */
    public void addToCart() {
        // Workaround for explicit waiting
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        this.addToCartButton.click();
    }

    /**
     * Cliks "continue shopping" button (popup).
     */
    public void continueShopping() {
        final WebDriverWait wait = new WebDriverWait(driver, 10);
        final WebElement element = wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        element.click();
    }

    /**
     * Cliks "go to cart" - button (popup).
     */
    public CartPage goToCart() {
        final WebDriverWait wait = new WebDriverWait(driver, 10);
        final WebElement element = wait.until(ExpectedConditions.elementToBeClickable(goToCartButton));
        element.click();

        final CartPage result = new CartPage(driver);

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
     * Gets similar products from the detail page.
     *
     * @return Similar products
     */
    public List<SimilarProductElement> getSimilarProducts() {
        final List<SimilarProductElement> result = new LinkedList<>();

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        for(WebElement product: similarProducts) {
            final SimilarProductElement similarProduct = new SimilarProductElement(driver, product);
            result.add(similarProduct);
        }
        return result;
    }
}
