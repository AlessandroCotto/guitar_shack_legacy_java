package com.guitarshack;

public class Program {

    private static final Service salesService = new RESTService("https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/sales");

    private static final ReorderThreshold reorderThreshold = new ReorderThreshold(new SalesHistory(salesService), new CurrentSystemTime());
    private static Warehouse warehouse;
    private static StockMonitor monitor;

    static {
        Alert alert = product -> {
            // We are faking this for now
            System.out.println(
                    "You need to reorder product " + product.getId() +
                            ". Only " + product.getStock() + " remaining in stock");
        };
        monitor = new StockMonitor(alert, reorderThreshold, warehouse);
    }

    public Program() {
        warehouse = new Warehouse(new RESTService("https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/product"));
    }

    public static void main(String[] args) {
        int productId = 811; // Integer.parseInt(args[0]);
        int quantity = 100; //  Integer.parseInt(args[1]);

        monitor.productSold(productId, quantity);
    }
}
