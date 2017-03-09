package de.otto.systemtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Abstract otto page object.
 */
public abstract class OttoPage {

    protected WebDriver driver;

    @FindBy(xpath = "(.//*[@class='navItem'])[2]")
    private WebElement mainManuItem2;

    @FindBy(xpath = "(.//*[@class='flyOutCategory']/li[4]/a)[1]")
    private WebElement mainMenuItem2Subitem4;

    /**
     * Gets a {@link WebDriver} with already loaded URL ({@code driver.get(url)})
     * and runs {@code PageFactory.initElements(driver, this)} on it.
     *
     * @param driver Already loaded {@code WebDriver}
     */
    public OttoPage(final WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Gets a {@link WebDriver}, loads the {@code url} and runs
     * {@code PageFactory.initElements(driver, this)} on it.
     *
     * @param driver A {@code WebDriver}
     * @param url A URL to load
     */
    public OttoPage(final WebDriver driver, final String url) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.driver = driver;
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    /**
     * Gets the main menu item on position 2 (Damen).
     *
     * @return Main menu item.
     */
    private WebElement getMainMenuItem2() {
        return this.mainManuItem2;
    }

    /**
     * Gets the subitem on position four (Hosen).
     *
     * @return Menu subitem
     */
    private WebElement getMainMenuItem2Subitem4() {
        return this.mainMenuItem2Subitem4;
    }

    /**
     * Performs a mouseover aktion on main menu on position two (Damen).
     */
    public void mainMenuItemMouseOver() {
        final Actions builder = new Actions(this.driver);
        final WebElement mainMenuItem2 = getMainMenuItem2();
        builder.moveToElement(mainMenuItem2);
        builder.perform();
    }

    /**
     * Clicks the main manu item on position two (Damen) subitem on position 4 (Hosen).
     *
     * @return Category page of Damen-Hosen
     */
    public CategoryPage mainMenuItem2Subitem4Click() {
        mainMenuItemMouseOver();
        final WebElement mainMenuItem2Subitem4 = getMainMenuItem2Subitem4();
        mainMenuItem2Subitem4.click();
        final CategoryPage result = new CategoryPage(this.driver);

        return result;
    }
}
