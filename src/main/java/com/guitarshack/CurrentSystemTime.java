package com.guitarshack;

import java.util.Calendar;
import java.util.Date;

public class CurrentSystemTime implements CurrentTime {
    public CurrentSystemTime() {
    }

    public Date get() {
        return Calendar.getInstance().getTime();
    }
}