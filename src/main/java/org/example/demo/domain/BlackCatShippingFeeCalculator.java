package org.example.demo.domain;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class BlackCatShippingFeeCalculator implements ShippingFeeCalculator {
    private final NavigableMap<Integer, Integer> volumeFees = new TreeMap<>();

    public BlackCatShippingFeeCalculator() {
        volumeFees.put(1000, 30);
        volumeFees.put(2000, 50);
        volumeFees.put(Integer.MAX_VALUE, 100);
    }

    @Override
    public int calculate(Box box) {
        return volumeFees.ceilingEntry(box.getVolume()).getValue();
    }

    @Override
    public String getName() {
        return "Black Cat";
    }
}