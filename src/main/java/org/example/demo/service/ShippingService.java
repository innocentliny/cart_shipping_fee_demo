package org.example.demo.service;

import org.example.demo.domain.BlackCatShippingFeeCalculator;
import org.example.demo.domain.Box;
import org.example.demo.domain.ShippingFeeCalculator;
import org.example.demo.domain.ShippingNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    private final Map<String, ShippingFeeCalculator> shippingFeeCalculators = new HashMap<>();

    public ShippingService(List<ShippingFeeCalculator> shippingFeeCalculators) {
        shippingFeeCalculators.forEach(calculator -> this.shippingFeeCalculators.put(calculator.getName(), calculator));
    }

    public int getFee(String shippingName, Box box) throws ShippingNotFoundException {
        ShippingFeeCalculator calculator = this.shippingFeeCalculators.get(shippingName);
        if (calculator == null) {
            throw new ShippingNotFoundException(shippingName);
        }
        return calculator.calculate(box);
    }
}
