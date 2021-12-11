package com.guitarshack;

public interface Alert {

    // could be an email, sms, ....
    void send(Product product);
}
