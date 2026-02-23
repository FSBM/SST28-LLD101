import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final PriceCalculater pricer;
    private final TaxRule taxRule;
    private final DiscountRule discountRule;
    private final InvoiceFormatter formatter;
    private final InvoiceRepository repo;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(PriceCalculater pricer, TaxRule taxRule,
            DiscountRule discountRule, InvoiceFormatter formatter, InvoiceRepository repo) {
        this.pricer = pricer;
        this.taxRule = taxRule;
        this.discountRule = discountRule;
        this.formatter = formatter;
        this.repo = repo;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        List<InvoiceLineItem> items = pricer.buildLineItems(menu, lines);
        double subtotal = pricer.computeSubtotal(items);

        double taxPct = taxRule.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountRule.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        Invoice inv = new Invoice(invId, customerType, items, subtotal, taxPct, tax, discount, total);

        String printable = formatter.format(inv);
        System.out.print(printable);

        repo.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + repo.countLines(invId) + ")");
    }
}
