package net.santandera.samplecode.apps.salestax.service.impl;

import net.santandera.samplecode.apps.salestax.service.AbstractTaxService;
import net.santandera.samplecode.apps.salestax.service.ItemTaxCalculator;

/**
 * Initial implementation of the TaxService - just defers to the base functionality.
 */
public class InitialTaxService extends AbstractTaxService {
    public InitialTaxService(ItemTaxCalculator taxCalculator) {
        super(taxCalculator);
    }
}
