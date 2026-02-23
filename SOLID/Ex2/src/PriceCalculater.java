import java.util.*;

public class PriceCalculater {

    public List<InvoiceLineItem> buildLineItems(Map<String, MenuItem> menu, List<OrderLine> lines) {
        List<InvoiceLineItem> items = new ArrayList<>();
        for (OrderLine l : lines) {
            MenuItem mi = menu.get(l.itemId);
            double lineTotal = mi.price * l.qty;
            items.add(new InvoiceLineItem(mi.name, l.qty, lineTotal));
        }
        return items;
    }

    public double computeSubtotal(List<InvoiceLineItem> items) {
        double sub = 0.0;
        for (InvoiceLineItem li : items)
            sub += li.lineTotal;
        return sub;
    }
}
