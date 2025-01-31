package com.guitarshack;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalesHistory {
    private final Service salesService;

    public SalesHistory(Service salesService) {
        this.salesService = salesService;
    }

    SalesTotal fetchSalesTotal(Product product, Date startDate, Date endDate) {

        DateFormat format = new SimpleDateFormat("M/d/yyyy");
        Map<String, Object> params1 = new HashMap<>() {{
            put("productId", product.getId());
            put("startDate", format.format(startDate));
            put("endDate", format.format(endDate));
            put("action", "total");
        }};

        String result1 = salesService.sendRequest(params1);
        SalesTotal total = new Gson().fromJson(result1, SalesTotal.class);
        return total;
    }
}
