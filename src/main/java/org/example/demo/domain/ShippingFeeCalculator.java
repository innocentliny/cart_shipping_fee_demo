package org.example.demo.domain;

public interface ShippingFeeCalculator {
    int calculate(Box box);

    /**
     * @return shipping name
     */
    String getName();
}
