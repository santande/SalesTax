package net.santandera.samplecode.apps.salestax.service;

import net.santandera.samplecode.apps.salestax.model.ItemInterface;
import org.joda.money.Money;

public interface ItemTaxCalculator {

    Money calculateSalesTax(ItemInterface item);

    Money roundSalesTax(Money rawSalestax);
}
