package net.santandera.samplecode.apps.salestax.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.santandera.samplecode.apps.salestax.exception.InputFileException;
import net.santandera.samplecode.apps.salestax.exception.TaxServiceException;
import net.santandera.samplecode.apps.salestax.model.Item;
import net.santandera.samplecode.apps.salestax.model.TaxedItem;
import net.santandera.samplecode.apps.salestax.model.TaxedItemsSummary;
import org.joda.money.Money;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class AbstractTaxService implements TaxService {

    private ItemTaxCalculator taxCalculator;

    private ObjectMapper objectMapper;

    Validator validator;

    public AbstractTaxService(ItemTaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
        objectMapper = new ObjectMapper();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public TaxedItemsSummary calculateTaxes(String[] filenames) throws TaxServiceException {
        TaxedItemsSummary summary = null;
        try {
            File file = getFile(filenames);
            List<Item> items = objectMapper.readValue(file, new TypeReference<List<Item>>(){});
            validate(items);
            summary = createTaxesSummaryFor(items);
        }
        catch (Exception e) {
            throw new TaxServiceException(e.getMessage());
        }
        return summary;
    }

    private TaxedItemsSummary createTaxesSummaryFor(List<Item> items)  {
        List<TaxedItem> taxedItems = new ArrayList<>();
        for (Item item: items) {
            Money itemTaxes = taxCalculator.calculateSalesTax(item);
            itemTaxes = taxCalculator.roundSalesTax(itemTaxes);
            TaxedItem taxedItem = new TaxedItem(item, itemTaxes);
            taxedItems.add(taxedItem);
        }
        return new TaxedItemsSummary(taxedItems);
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

    private void validate(List<Item> items) throws InputFileException {
        for (Item item: items) {
            Set<ConstraintViolation<Item>> violations = validator.validate(item);
            if (violations != null  && violations.size() > 0) {
                //just get 1st one for now.
                ConstraintViolation<Item> violation = violations.iterator().next();
                String propertyName = violation.getPropertyPath().toString().toLowerCase().equals("name") ?
                        "<unknown name>" : item.getName();
                throw new InputFileException("Item " + propertyName + " is in ERROR: " +
                        violation.getMessage());
            }

        }


    }

}
