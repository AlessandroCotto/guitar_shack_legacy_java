package com.guitarshack;

import java.util.Calendar;

public class StockMonitor {
    private final Alert alert;
    private final ReorderThreshold reorderThreshold;
    private final Warehouse warehouse;

    public StockMonitor(Alert alert, ReorderThreshold reorderThreshold, Warehouse warehouse) {
        this.alert = alert;
        this.warehouse = warehouse;
        this.reorderThreshold = reorderThreshold;
    }




    public void productSold(int productId, int quantity/*, ProductService productService*/) {

        // * split the 2 sub-jobs of StockMonitor
        // 1) get product info and its reorder level
        // 2) get  prodcut


        Product product = warehouse.getProduct(productId);

        int unsoldStock = product.getStock() - quantity;
        if(unsoldStock <= reorderThreshold.calculate(product))
            alert.send(product);
    }

}
