package net.santandera.samplecode.apps.salestax.model;

import org.joda.money.Money;

public class TaxedItem extends Item {

    protected Money taxes;

    public TaxedItem() {
        super();
    }

    public TaxedItem(Item item, Money taxes) {
        this.taxes = taxes;
        this.name = item.name;
        this.price = item.price;
        this.quantity = item.quantity;
        this.source = item.source;
        this.type = item.type;
    }

    public Money getTaxes() {
        return taxes;
    }

    public void setTaxes(Money taxes) {
        this.taxes = taxes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(quantity).append(")").append(" ");
        sb.append(name).append(": ");
        sb.append(price.multipliedBy(quantity).plus(taxes).getAmount().toPlainString());
        return sb.toString();
    }
}
