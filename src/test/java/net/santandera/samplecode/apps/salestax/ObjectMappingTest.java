package net.santandera.samplecode.apps.salestax;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.santandera.samplecode.apps.salestax.model.Item;
import net.santandera.samplecode.apps.salestax.model.ProductType;
import net.santandera.samplecode.apps.salestax.model.SourceType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

/**
 * Unit test for simple CalculateSalesTax.
 */
public class ObjectMappingTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSerializeItems() throws JsonProcessingException {
        Item item = new Item();
        item.setName("book");
        item.setPrice(Money.of(CurrencyUnit.USD, 10.50d));
        item.setQuantity(1l);
        item.setSource(SourceType.Import);
        item.setType(ProductType.Book);

        String prettyItem = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item);
        //log.info(prettyItem);

        assertThat(prettyItem, containsString("10.50"));
    }

    @Test
    public void testDeserializeItems() throws IOException, JsonProcessingException {

        InputStream is = getClass().getClassLoader().getResourceAsStream("testinput1.json");

        List<Item> items = objectMapper.readValue(is, new TypeReference<List<Item>>() {
        });
/*
        for (Item item: items) {
            log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
        }
*/
        assertTrue("Did not deserialize Items correctly", items != null && items.size() == 3);

    }
}
