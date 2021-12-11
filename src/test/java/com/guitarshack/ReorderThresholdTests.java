package com.guitarshack;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class ReorderThresholdTests {


    private ReorderThreshold reorderThreshold;
    private SalesHistory salesHistory;
    private Date startDate;
    private Date endDate;
    private Product product;

    @Before
    public void setup() {
        salesHistory = mock(SalesHistory.class);
        when(salesHistory.fetchSalesTotal(any(), any(), any())).thenReturn(new SalesTotal());

        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.APRIL, 25);
        Date currentTime = calendar.getTime();

        calendar.set(2020, Calendar.APRIL, 25);
        startDate = calendar.getTime();
        calendar.set(2020, Calendar.MAY, 25);
        endDate = calendar.getTime();

        reorderThreshold = new ReorderThreshold(salesHistory, () -> currentTime);

        product = new Product(811, 10, 14);
    }

    @Test
    public void salesTotalBeginSameDayLastYear() {
        reorderThreshold.calculate(product);

        verify(salesHistory).fetchSalesTotal(any(), eq(startDate), any());
    }

    @Test
    public void salesTotalEnds30DaysAfterStartDate() {
        reorderThreshold.calculate(product);
        verify(salesHistory).fetchSalesTotal(any(), any(), eq(endDate));

    }

}
