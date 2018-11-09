package net.santandera.samplecode.apps.salestax;

import net.santandera.samplecode.apps.salestax.model.Item;
import net.santandera.samplecode.apps.salestax.model.ProductType;
import net.santandera.samplecode.apps.salestax.model.SourceType;
import net.santandera.samplecode.apps.salestax.service.ItemTaxCalculator;
import net.santandera.samplecode.apps.salestax.service.impl.InitialItemTaxCalculator;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;

public class InitialItemTaxCalculatorTest {

    ItemTaxCalculator taxCalculator = new InitialItemTaxCalculator();

    @Test
    public void testCalculateItemTax_NoTaxes() {
        Item item = new Item();
        item.setName("book");
        Money price = Money.of(CurrencyUnit.USD, 10.50d);
        item.setPrice(price);
        item.setQuantity(1l);
        item.setSource(SourceType.Domestic);
        item.setType(ProductType.Book);

        Money tax = taxCalculator.calculateSalesTax(item);

        Assert.assertTrue("Taxes aren't 0!", tax.isZero());
    }

    @Test
    public void testCalculateItemTax_10Percent() {
        Item item =  new Item();
        item.setName("CD");
        Money price = Money.of(CurrencyUnit.USD, 10.00d);
        item.setPrice(price);
        item.setQuantity(1l);
        item.setSource(SourceType.Domestic);
        item.setType(ProductType.Other);

        Money tax = taxCalculator.calculateSalesTax(item);

        Assert.assertTrue("Taxes aren't 1 dollar!", tax.equals(Money.ofMajor(CurrencyUnit.USD, 1l)));
    }

    @Test
    public void testCalculateItemTax_10PercentAnd5Percent() {
        Item item = new Item();
        item.setName("CD");
        Money price = Money.of(CurrencyUnit.USD, 10.00d);
        item.setPrice(price);
        item.setQuantity(1l);
        item.setSource(SourceType.Import);
        item.setType(ProductType.Other);

        Money tax = taxCalculator.calculateSalesTax(item);

        Assert.assertTrue("Taxes aren't 1.50!",
                tax.equals(Money.of(CurrencyUnit.USD, 1.50)));
    }

    @Test
    public void testRoundedSalesTax_NoRounding0Cents() {
        double amt = 1.50d;
        Money currSalesTax = Money.of(CurrencyUnit.USD, amt);
        Money roundedSalesTax = taxCalculator.roundSalesTax(currSalesTax);
        Assert.assertTrue("Rounding is in error!", roundedSalesTax.equals(currSalesTax));
    }

    @Test
    public void testRoundedSalesTax_NoRounding5Cents() {
        double amt = 1.55d;
        Money currSalesTax = Money.of(CurrencyUnit.USD, amt);
        Money roundedSalesTax = taxCalculator.roundSalesTax(currSalesTax);
        Assert.assertTrue("Rounding is in error!", roundedSalesTax.equals(currSalesTax));
    }


    @Test
    public void testRoundedSalesTax_RoundToNearest5() {
        double amt = 1.52d;
        Money currSalesTax = Money.of(CurrencyUnit.USD, amt);
        Money roundedSalesTax = taxCalculator.roundSalesTax(currSalesTax);
        Assert.assertTrue("Rounding is in error!",
                roundedSalesTax.equals(Money.of(CurrencyUnit.USD, 1.55d)));
    }

    @Test
    public void testRoundedSalesTax_RoundToNearest10() {
        double amt = 1.58d;
        Money currSalesTax = Money.of(CurrencyUnit.USD, amt);
        Money roundedSalesTax = taxCalculator.roundSalesTax(currSalesTax);
        Assert.assertTrue("Rounding is in error!",
                roundedSalesTax.equals(Money.of(CurrencyUnit.USD, 1.60d)));
    }

    @Test
    public void testRoundedSalesTax_RoundToNearest100() {
        double amt = 1.98d;
        Money currSalesTax = Money.of(CurrencyUnit.USD, amt);
        Money roundedSalesTax = taxCalculator.roundSalesTax(currSalesTax);
        Assert.assertTrue("Rounding is in error!",
                roundedSalesTax.equals(Money.of(CurrencyUnit.USD,  2.00d)));
    }

}
