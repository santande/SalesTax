package net.santandera.samplecode.apps.salestax;

import net.santandera.samplecode.apps.salestax.model.TaxedItemsSummary;
import net.santandera.samplecode.apps.salestax.service.ItemTaxCalculator;
import net.santandera.samplecode.apps.salestax.service.TaxServiceFactory;
import net.santandera.samplecode.apps.salestax.service.impl.InitialItemTaxCalculator;
import net.santandera.samplecode.apps.salestax.service.TaxService;

/**
 * Hello world!
 *
 */
public class CalculateSalesTax
{

    //this kinda "simulates" dependency injection.
    private static ItemTaxCalculator taxCalculator = new InitialItemTaxCalculator();


    public static void main( String[] args )
    {
        System.out.println( "Beginning CalculateSalesTax..." );
        try {
            TaxService taxService = TaxServiceFactory.getTaxServiceFor(taxCalculator);
            TaxedItemsSummary taxedItemsSummary = taxService.calculateTaxes(args);
            System.out.println(taxedItemsSummary);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Finished CalculateSalesTax.");
    }

}
