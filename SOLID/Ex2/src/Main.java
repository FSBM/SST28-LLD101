import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        PriceCalculater pricer = new PriceCalculater();
        TaxRule tax = new StandardTaxRule();
        DiscountRule disc = new StandardDiscountRule();
        InvoiceFormatter fmt = new InvoiceFormatter();
        InvoiceRepository store = new FileStore();

        CafeteriaSystem sys = new CafeteriaSystem(pricer, tax, disc, fmt, store);
        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1));

        sys.checkout("student", order);
    }
}
