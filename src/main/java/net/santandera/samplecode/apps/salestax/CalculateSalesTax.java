package net.santandera.samplecode.apps.salestax;

import net.santandera.samplecode.apps.salestax.exception.TaxServiceException;
import net.santandera.samplecode.apps.salestax.model.TaxedItemsSummary;
import net.santandera.samplecode.apps.salestax.service.ItemTaxCalculator;
import net.santandera.samplecode.apps.salestax.service.TaxService;
import net.santandera.samplecode.apps.salestax.service.TaxServiceFactory;
import net.santandera.samplecode.apps.salestax.service.impl.InitialItemTaxCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CalculateSalesTax {

    private static Logger log = LoggerFactory.getLogger(CalculateSalesTax.class);

    //this kinda "simulates" dependency injection.
    private static ItemTaxCalculator taxCalculator = new InitialItemTaxCalculator();


    public static void main(String[] args) {
        log.info("Beginning CalculateSalesTax...");
        try {
            TaxService taxService = TaxServiceFactory.getTaxServiceFor(taxCalculator);
            TaxedItemsSummary taxedItemsSummary = taxService.calculateTaxes(args);
            log.info("-----Writing Final Results-----");
            log.info(taxedItemsSummary.toString());
        } catch (TaxServiceException e) {
            log.error(e.getMessage());
        }
        log.info("Finished CalculateSalesTax.");
    }

}
