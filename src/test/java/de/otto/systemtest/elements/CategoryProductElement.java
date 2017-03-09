package de.otto.systemtest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Category product bean.
 *
 * It contains product information for products displayed on the category page.
 */
public final class CategoryProductElement extends ProductElement {

    /**
     * Gets {@link WebDriver} and loaded product displayed on the category page.
     *
     * @param driver  Already loaded {@code WebDriver}
     * @param element Product from the category page to load
     */
    public CategoryProductElement(final WebDriver driver, final WebElement element) {
        this.driver = driver;
        this.name = element.findElement(By.xpath(".//*[@class='productLink']/span[@class='name']"));
        this.price = element.findElement(By.xpath(".//span[@class='value']"));
    }

}
