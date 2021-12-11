package com.guitarshack;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class StockMonitorTests {

    private Alert alert;
    private StockMonitor stockMonitor;

    @Test
    public void whenCurrentStockIsBelowThresholdShouldSendAlert() {

        stockMonitor.productSold(811, 2);
        verify(alert).send(any());
    }

    @Before
    public void setup() {
        alert = mock(Alert.class);
        ReorderThreshold reorderThreshold = new ReorderThreshold(new SalesHistory(params -> "{ \"total\": 60}"), new CurrentSystemTime());

        Warehouse warehouse = new Warehouse( params ->  "{ \"id\" : 811, \"leadTime\": 14, \"stock\" :30 }" ) ;

        stockMonitor = new StockMonitor(alert, reorderThreshold, warehouse);
    }

    @Test
    public void whenCurrentStockIsAboveReorderThresholdNoAlertNecessary() {


        stockMonitor.productSold(811, 1);

        verifyNoMoreInteractions(alert);

    }
}
