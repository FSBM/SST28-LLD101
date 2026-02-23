import java.util.*;

public class Invoice {
    public final String id;
    public final String customerType;
    public final List<InvoiceLineItem> items;
    public final double subtotal;
    public final double taxPct;
    public final double tax;
    public final double discount;
    public final double total;

    public Invoice(String id, String customerType, List<InvoiceLineItem> items,
            double subtotal, double taxPct, double tax, double discount, double total) {
        this.id = id;
        this.customerType = customerType;
        this.items = items;
        this.subtotal = subtotal;
        this.taxPct = taxPct;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}
