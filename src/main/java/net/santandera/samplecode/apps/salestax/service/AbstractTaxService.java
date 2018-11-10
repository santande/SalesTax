package net.santandera.samplecode.apps.salestax.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.santandera.samplecode.apps.salestax.exception.InputFileException;
import net.santandera.samplecode.apps.salestax.exception.TaxServiceException;
import net.santandera.samplecode.apps.salestax.model.Item;
import net.santandera.samplecode.apps.salestax.model.TaxedItem;
import net.santandera.samplecode.apps.salestax.model.TaxedItemsSummary;
import org.joda.money.Money;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTaxService implements TaxService {

    private ItemTaxCalculator taxCalculator;

    private ObjectMapper objectMapper;

    public AbstractTaxService(ItemTaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
        objectMapper = new ObjectMapper();
    }

    @Override
    public TaxedItemsSummary calculateTaxes(String[] filenames) throws TaxServiceException {
        TaxedItemsSummary summary = null;
        try {
            File file = getFile(filenames);
            List<Item> items = objectMapper.readValue(file, new TypeReference<List<Item>>(){});
            List<TaxedItem> taxedItems = new ArrayList<>();
            for (Item item: items) {
                Money itemTaxes = taxCalculator.calculateSalesTax(item);
                itemTaxes = taxCalculator.roundSalesTax(itemTaxes);
                TaxedItem taxedItem = new TaxedItem(item, itemTaxes);
                taxedItems.add(taxedItem);
            }
            summary = new TaxedItemsSummary(taxedItems);

        }
        catch (Exception e) {
            throw new TaxServiceException(e.getMessage());
        }
        return summary;
    }

    private static File getFile(String[] fileName) throws InputFileException {
        File file;
        if (fileName == null || fileName.length != 1) {
            throw new InputFileException("A single filename must be specified as an argument.");
        }

        Path filePath;
        try {
            filePath = Paths.get(fileName[0]);
            if (!Files.isRegularFile(filePath) || !Files.exists(filePath)) {
                throw new InputFileException("File name must be a regular file that exists on the filesystem.");
            }

            file = filePath.toFile();
        }
        catch (InvalidPathException ipe) {
            throw new InputFileException(ipe.getMessage());
        }
        return file;
    }

}
