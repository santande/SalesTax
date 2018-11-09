package net.santandera.samplecode.apps.salestax.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.money.Money;

import java.io.IOException;

public class MoneySerializer extends StdSerializer<Money> {

   public MoneySerializer() {
        this(null);
    }

    public MoneySerializer(Class<Money> t) {
        super(t);
    }

    @Override
    public void serialize(
            Money value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        gen.writeString(value.getAmount().toString());
    }
}