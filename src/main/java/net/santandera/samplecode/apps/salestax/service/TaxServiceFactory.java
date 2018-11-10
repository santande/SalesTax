package net.santandera.samplecode.apps.salestax.service;

import net.santandera.samplecode.apps.salestax.service.impl.InitialTaxService;

public class TaxServiceFactory {

    private TaxServiceFactory() {

    }

    public static TaxService getTaxServiceFor(ItemTaxCalculator taxCalculator) {
        TaxService taxService = null;

        taxService = new InitialTaxService(taxCalculator);

        return taxService;
    }

}
