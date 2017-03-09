package de.otto.systemtest.pages;

import de.otto.systemtest.elements.CategoryProductElement;
import de.otto.systemtest.elements.ProductElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;
import java.util.List;

/**
 * Category page object.
 */
public class CategoryPage extends OttoPage {

    @FindBy(xpath = ".//*[@class='product small']")
    private List<WebElement> products;

    /**
     * Gets a {@link WebDriver} with already loaded url ({@link WebDriver#get(String)})
     * and runs {@code FactoryPage.initElements(driver, Object)} on it.
     *
     * @param driver Already loaded {@link WebDriver}
     */
    public CategoryPage(final WebDriver driver) {
        super(driver);
    }

    /**
     * Gets a {@link WebDriver}, loads the {@code url} and runs
     * {@code FactoryPage.initElements(driver, Object)} on it.
     *
     * @param driver Already loaded {@link WebDriver}
     * @param url A url to load
     */
    public CategoryPage(final WebDriver driver, final String url) {
        super(driver, url);
    }


    /**
     * Gets category page products.
     *
     * @return Category page products
     */
    public List<ProductElement> getProducts() {
        final JavascriptExecutor jse = (JavascriptExecutor)driver;
        final List<ProductElement> result = new LinkedList<>();
        for(WebElement product: products) {
            // Lazy loading
            jse.executeScript("window.scrollBy(0,500)", "");
            final CategoryProductElement categoryProductElement = new CategoryProductElement(this.driver, product);
            result.add(categoryProductElement);
        }
        return result;
    }
}
