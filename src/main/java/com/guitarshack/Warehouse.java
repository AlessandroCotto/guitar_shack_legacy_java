package com.guitarshack;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private final Service productService;

    public Warehouse(Service productService) {
        this.productService = productService;
    }

    Product getProduct(int productId) {
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("id", productId);
        }};

        String result = productService.sendRequest(params);
        return new Gson().fromJson(result, Product.class);
    }
}