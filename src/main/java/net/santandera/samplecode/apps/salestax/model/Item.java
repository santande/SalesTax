package net.santandera.samplecode.apps.salestax.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.santandera.samplecode.apps.salestax.converter.MoneyDeserializer;
import net.santandera.samplecode.apps.salestax.converter.MoneySerializer;
import org.joda.money.Money;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Item implements ItemInterface {


    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    protected String name;

    @NotNull(message = "type cannot be null")
    protected ProductType type;

    @NotNull(message = "price cannot be null")
    @JsonSerialize(using = MoneySerializer.class)
    @JsonDeserialize(using = MoneyDeserializer.class)
    protected Money price;

    @NotNull(message = "quantity cannot be null")
    @Min(value = 1, message = "Quantity should be greater than 0")
    @Max(value = 150, message = "Quantity should not be greater than 150")
    protected Long quantity;

    @NotNull(message = "source cannot be null")
    protected SourceType source;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    @Override
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public SourceType getSource() {
        return source;
    }

    public void setSource(SourceType source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", quantity=" + quantity +
                ", source=" + source +
                '}';
    }
}
