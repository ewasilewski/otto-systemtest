package de.otto.systemtest.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * ChromeDriver instance.
 */
public final class WebDriverFactory {

    /**
     * Gets {@link WebDriver} object.
     *
     * @return A WebDriver.
     */
    public static WebDriver getWebDriver() {
        final WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1424,1000));

        return driver;
    }
}
