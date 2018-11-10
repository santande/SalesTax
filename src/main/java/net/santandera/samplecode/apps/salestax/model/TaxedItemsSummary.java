package net.santandera.samplecode.apps.salestax.model;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.List;

public class TaxedItemsSummary {

    private List<TaxedItem> taxedItems;

    private Money salesTaxes = Money.zero(CurrencyUnit.USD);

    private Money total = Money.zero(CurrencyUnit.USD);


    public TaxedItemsSummary(List<TaxedItem> taxedItems) {
        this.taxedItems = taxedItems;
        for (TaxedItem taxedItem : this.taxedItems) {
            salesTaxes = salesTaxes.plus(taxedItem.taxes);
            total = total.plus(taxedItem.taxes).plus(taxedItem.getPrice().multipliedBy(taxedItem.quantity));
        }
    }

    public List<TaxedItem> getTaxedItems() {
        return taxedItems;
    }

    public void setTaxedItems(List<TaxedItem> taxedItems) {
        this.taxedItems = taxedItems;
    }

    public Money getSalesTaxes() {
        return salesTaxes;
    }

    public void setSalesTaxes(Money salesTaxes) {
        this.salesTaxes = salesTaxes;
    }

    public Money getTotal() {
        return total;
    }

    public void setTotal(Money total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Summary:\r\n");
        for (TaxedItem taxedItem : this.taxedItems) {
            sb.append(taxedItem.toString());
            sb.append("\r\n");
        }
        sb.append("Sales Tax: ");
        sb.append(salesTaxes.getAmount().toPlainString());
        sb.append("\r\n");
        sb.append("Total: ");
        sb.append(total.getAmount().toPlainString());
        return sb.toString();
    }
}
