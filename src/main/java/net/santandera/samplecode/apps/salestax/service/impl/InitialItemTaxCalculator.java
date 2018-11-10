package net.santandera.samplecode.apps.salestax.service.impl;

import net.santandera.samplecode.apps.salestax.model.ItemInterface;
import net.santandera.samplecode.apps.salestax.model.ProductType;
import net.santandera.samplecode.apps.salestax.model.SourceType;
import net.santandera.samplecode.apps.salestax.service.ItemTaxCalculator;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InitialItemTaxCalculator implements ItemTaxCalculator {
    @Override
    public Money calculateSalesTax(ItemInterface item) {
        Money taxes = Money.zero(CurrencyUnit.USD);
        //tax is 10% for all "Other" items.
        if (item.getType() == ProductType.Other) {
            taxes = item.getPrice()
                    .multipliedBy(item.getQuantity())
                    .multipliedBy(new BigDecimal(".1"), RoundingMode.HALF_UP);
        }

        if (item.getSource() == SourceType.Import) {
            //add addtl 5% tax for import items.
            taxes = taxes.plus(item.getPrice()
                    .multipliedBy(item.getQuantity())
                    .multipliedBy(new BigDecimal(".05"), RoundingMode.HALF_UP));
        }


        return taxes;

    }

    @Override
    public Money roundSalesTax(Money rawSalestax) {
        int cents = rawSalestax.getMinorPart();
        String centsStr = Integer.toString(cents);
        int lastCentsPosition = cents > 9 ? 1 : 0;
        int lastCentsDigit = Character.getNumericValue(centsStr.charAt(lastCentsPosition));
        if (lastCentsDigit != 0 && lastCentsDigit != 5) {
            if (lastCentsDigit > 5) {
                return rawSalestax.plusMinor(10 - lastCentsDigit);
            } else {
                return rawSalestax.plusMinor(5 - lastCentsDigit);
            }
        }
        return rawSalestax;
    }
}
