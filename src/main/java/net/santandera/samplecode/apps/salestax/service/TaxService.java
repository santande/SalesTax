package net.santandera.samplecode.apps.salestax.service;

import net.santandera.samplecode.apps.salestax.exception.TaxServiceException;
import net.santandera.samplecode.apps.salestax.model.TaxedItemsSummary;

public interface TaxService {
    TaxedItemsSummary calculateTaxes(String[] filenames) throws TaxServiceException;
}
