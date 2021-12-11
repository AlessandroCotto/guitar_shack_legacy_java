package com.guitarshack;

import java.util.Calendar;
import java.util.Date;

public class ReorderThreshold {

    private final SalesHistory salesHistory;
    private final CurrentTime currentTime;

    public ReorderThreshold(SalesHistory salesHistory, CurrentTime currentTime) {
        this.salesHistory = salesHistory;
        this.currentTime = currentTime;
    }

    int calculate(Product product) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime.get());
        calendar.add(Calendar.YEAR, -1);

        Date startDate = calendar.getTime();

        calendar.add(Calendar.DATE, 30);

        Date endDate = calendar.getTime();

        SalesTotal total = salesHistory.fetchSalesTotal(product, startDate, endDate);
        return (int) ((double) (total.getTotal() / 30) * product.getLeadTime());
    }

}