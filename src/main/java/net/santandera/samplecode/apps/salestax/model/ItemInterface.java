package net.santandera.samplecode.apps.salestax.model;

import org.joda.money.Money;

public interface ItemInterface {

    Money getPrice();

    Long getQuantity();

    SourceType getSource();

    ProductType getType ();

}
