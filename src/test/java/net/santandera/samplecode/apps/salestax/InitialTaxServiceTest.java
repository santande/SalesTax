package net.santandera.samplecode.apps.salestax;

import junit.framework.Assert;
import net.santandera.samplecode.apps.salestax.exception.InputFileException;
import net.santandera.samplecode.apps.salestax.exception.TaxServiceException;
import net.santandera.samplecode.apps.salestax.model.TaxedItemsSummary;
import net.santandera.samplecode.apps.salestax.service.impl.InitialItemTaxCalculator;
import net.santandera.samplecode.apps.salestax.service.impl.InitialTaxService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.io.File;

public class InitialTaxServiceTest {

    @Test
    public void testCalculateTaxes_validInputFile() throws TaxServiceException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testinput1.json").getFile());
        String[] filenames = {file.getAbsolutePath()};
        Money salesTaxTotal = Money.of(CurrencyUnit.USD, 1.50);
        Money orderTotal = Money.of(CurrencyUnit.USD, 29.83);
        InitialTaxService taxService = new InitialTaxService(new InitialItemTaxCalculator());
        TaxedItemsSummary summary = taxService.calculateTaxes(filenames);
        Assert.assertTrue("Total sales tax is incorrect",
                summary.getSalesTaxes().equals(salesTaxTotal));

        Assert.assertTrue("Order total price is incorrect",
                summary.getTotal().equals(orderTotal));
    }

    @Test(expected = InputFileException.class)
    public void testCalculateTaxes_InvalidInputFile() throws TaxServiceException {
        String[] filenames = {"invalid-file"};
        InitialTaxService taxService = new InitialTaxService(new InitialItemTaxCalculator());
        TaxedItemsSummary summary = taxService.calculateTaxes(filenames);
    }
}
