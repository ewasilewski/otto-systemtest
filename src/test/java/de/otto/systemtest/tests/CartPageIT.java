package test.java.de.otto.systemtest.tests;

import de.otto.systemtest.data.ProductEqualsData;
import de.otto.systemtest.elements.CartProductElement;
import de.otto.systemtest.elements.ProductElement;
import de.otto.systemtest.elements.SimilarProductElement;
import de.otto.systemtest.pages.CartPage;
import de.otto.systemtest.pages.CategoryPage;
import de.otto.systemtest.pages.DetailPage;
import de.otto.systemtest.utils.OttoConfig;
import de.otto.systemtest.utils.WebDriverFactory;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import javax.money.MonetaryAmount;
import java.util.List;

/**
 * Test cart page.
 */
public class CartPageIT {

    private WebDriver driver;
    private String categoryURL;
    private CategoryPage categoryPage;
    private List<ProductElement> categoryProductsList;
    private ProductElement categoryProduct;
    private DetailPage detailPage;
    private CartPage cartPage;
    private SimilarProductElement similarProduct;

    @Before
    public void before() throws Exception {
        driver = WebDriverFactory.getWebDriver();
        categoryURL = OttoConfig.getCategoryURL();
        categoryPage = new CategoryPage(driver, categoryURL);
        categoryProductsList = categoryPage.getProducts();
        categoryProduct = categoryProductsList.get(0);
        detailPage = new DetailPage(driver);
        cartPage = new CartPage(driver);
    }

    @After
    public void after() throws Exception {
        driver.close();
    }

    /**
     * Compare added category product to cart with the product in the cart.
     * Is product- name and price equal.
     */
    @Test
    public void testCompareCategoryProductWithCartProduct() {
        final ProductEqualsData categoryProductElementProductEqualsData = categoryProduct.getProductEqualsData();

        // Go to detail page
        categoryProduct.goToDetailPage();

        // Add product to cart
        detailPage.addToCart();

        // Go to cart
        detailPage.goToCart();

        final CartProductElement cartProduct = cartPage.getProducts().get(0);
        final ProductEqualsData cartProductElementProductEqualsData = cartProduct.getProductEqualsData();

        Assert.assertEquals(categoryProductElementProductEqualsData, cartProductElementProductEqualsData);
    }

    /**
     * Quantity of products in the cart.
     * Added the same products to cart.
     */
    @Test
    public void testQuantityOfProducts() {
        // Go to detail page
        categoryProduct.goToDetailPage();

        // Add product to cart
        detailPage.addToCart();

        // Click on continue shopping button
        detailPage.continueShopping();

        // Add product to cart
        detailPage.addToCart();

        // Go to cart
        detailPage.goToCart();

        final CartProductElement cartProduct = cartPage.getProducts().get(0);
        final int cartProductQuantity = cartProduct.getProductQuantity();

        Assert.assertEquals(2, cartProductQuantity);
    }

    /**
     * Check price sum of two same products in the cart.
     */
    @Test
    public void testPriceSumOfTwoProducts() {
        final MonetaryAmount productPrice = categoryProduct.getPrice();

        // Go to detail page
        categoryProduct.goToDetailPage();

        // Add product to cart
        detailPage.addToCart();

        // Click on continue shopping button
        detailPage.continueShopping();

        // Add product to cart
        detailPage.addToCart();

        // Go to cart
        detailPage.goToCart();

        final CartProductElement cartProduct = cartPage.getProducts().get(0);
        final MonetaryAmount priceSum = cartProduct.getTotalPrice();

        Assert.assertEquals(productPrice.multiply(2), priceSum);
    }

    /**
     * Check subtotal of two products in the cart.
     * Added two different products.
     */
    @Test
    public void testSubtotal() {
        // Go to detail page
        categoryProduct.goToDetailPage();

        // Add product to cart
        detailPage.addToCart();

        // Click on continue shopping button
        detailPage.continueShopping();

        similarProduct = detailPage.getSimilarProducts().get(0);

        // Click on similar product
        similarProduct.goToDetailPage();

        // Add product to cart
        detailPage.addToCart();

        // Go to cart
        detailPage.goToCart();

        final MonetaryAmount totalPriceOfProduct1 = cartPage.getProducts().get(0).getTotalPrice();
        final MonetaryAmount totalPriceOfProduct2 = cartPage.getProducts().get(1).getTotalPrice();
        final MonetaryAmount totalPrice = totalPriceOfProduct1.add(totalPriceOfProduct2);

        final MonetaryAmount subtotal = cartPage.getSubtotalPrice();

        Assert.assertEquals(totalPrice, subtotal);
    }

    /**
     * Deleted one product from cart with "x" - Button.
     */
    @Test
    public void deleteProductFromCart() {
        // Go to detail page
        categoryProduct.goToDetailPage();

        // Add product to cart
        detailPage.addToCart();

        // Click on continue shopping button
        detailPage.continueShopping();

        similarProduct = detailPage.getSimilarProducts().get(0);

        // Click on similar product
        similarProduct.goToDetailPage();

        // Add similar product to cart
        detailPage.addToCart();

        // Go to cart
        detailPage.goToCart();

        // Delete one product from cart
        cartPage.getProducts().get(0).deleteProduct();

        // Workoraund for implicit/explicit waiting
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

       Assert.assertEquals(1, cartPage.getProducts().size());
    }
}
