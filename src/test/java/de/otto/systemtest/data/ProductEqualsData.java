package de.otto.systemtest.data;

import javax.money.MonetaryAmount;

/**
 * Data slice for product equality test of products of different types.
 */
public class ProductEqualsData {

    private final String name;
    private final MonetaryAmount price;

    /**
     * Gets name of product.
     *
     * @return Name of product.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets price of product.
     *
     * @return Monetary amount of product
     */
    public MonetaryAmount getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEqualsData that = (ProductEqualsData) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public ProductEqualsData(final String name, final MonetaryAmount price) {
        this.name = name;
        this.price = price;
    }
}
