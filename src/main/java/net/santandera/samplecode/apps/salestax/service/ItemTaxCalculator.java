package net.santandera.samplecode.apps.salestax.service;

import net.santandera.samplecode.apps.salestax.model.ItemInterface;
import org.joda.money.Money;

/**
 * Interface for implementation for calculating taxes and rounding taxes.
 * We split this functionality out separately to let them be modified independently if so desired.
 */
public interface ItemTaxCalculator {

    Money calculateSalesTax(ItemInterface item);

    Money roundSalesTax(Money rawSalestax);
}
