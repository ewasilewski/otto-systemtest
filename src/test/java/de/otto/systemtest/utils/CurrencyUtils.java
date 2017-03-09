package de.otto.systemtest.utils;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

/**
 * Currency utils.
 */
public class CurrencyUtils {

    final static String CURRENCY = "EUR";

    /**
     * Parses string amount to internal cent representation as {@code long}.
     *
     * Examples of {@code amount}:
     * <ul>
     *     <li>"1.00 €"</li>
     *     <li>"1,00 €"</li>
     *     <li>"- 1.00 €"</li>
     * </ul>
     *
     * @param amount String representation of amount
     * @return Long representation in cents
     */
    public static long parseAmount(final String amount) {
        final String cents = amount.
                replace(" ", "").  // Clear up from spaces.
                replace("€", "").  // Clear up from the euro symbol.
                replace(",", "").  // Clear up from the colon symbol.
                replace(".", "").  // Clear up from the dot symbol.
                replace("ab", ""). // Clear up from the "ab" symbol.
                replace("ab €", ""). // Clear up from the "ab" symbol.
                replace("(", "").   // Clear up from round bracket symbol
                replace(")", "");   //Clear up from round bracket symbol
        // The value is now parsable as long.

        final long result = Long.parseLong(cents);
        return result;
    }

    /**
     * Parses string amount to to monetary amount.
     *
     * @param amount String representation of amount
     * @return Monetary amount
     */
    public static MonetaryAmount parseMonetaryAmount(final String amount) {
        final long amountLong = CurrencyUtils.parseAmount(amount);
        final MonetaryAmount result = Monetary.getDefaultAmountFactory().setNumber(amountLong).setCurrency(CURRENCY).create();

        return result;
    }
}
