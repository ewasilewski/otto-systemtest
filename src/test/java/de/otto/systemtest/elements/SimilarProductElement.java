package de.otto.systemtest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Similar product bean.
 *
 * It contains product information for similar products displayed on the detail page.
 */
public final class SimilarProductElement extends ProductElement {

    /**
     * Gets {@link WebDriver} and loaded product displayed on the detail page.
     *
     * @param driver Already loaded {@code WebDriver}
     * @param element Similar product from the detail page to load
     */
    public SimilarProductElement(final WebDriver driver, final WebElement element) {
        this.driver = driver;
        this.name = element.findElement(By.xpath(".//*[@class='e_title e_js_title']"));
        this.price = element.findElement(By.xpath(".//span[@class='e_priceInfoWidget']/*[contains(@class, 'e_price')]"));
    }
}
