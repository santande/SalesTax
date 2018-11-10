package net.santandera.samplecode.apps.salestax.service;

import net.santandera.samplecode.apps.salestax.service.impl.InitialTaxService;

public class TaxServiceFactory {

    private TaxServiceFactory() {

    }


    public static TaxService getTaxServiceFor(ItemTaxCalculator taxCalculator) {
/*
        Very simple implementation of Factory Pattern in this method,
        since we have only 1 implementation of the TaxSerivce.
*/
        TaxService taxService = null;

        taxService = new InitialTaxService(taxCalculator);

        return taxService;
    }

}
