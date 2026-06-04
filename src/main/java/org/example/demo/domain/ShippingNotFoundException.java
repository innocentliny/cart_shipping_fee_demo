package org.example.demo.domain;

public class ShippingNotFoundException extends Exception {
    public ShippingNotFoundException(String shippingName) {
        super(shippingName);
    }
}
