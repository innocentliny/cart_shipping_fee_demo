package org.example.demo.domain;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * 新竹貨運，以重量計算運費。
 */
public class HshinChuShippingFeeCalculator implements ShippingFeeCalculator {
    private NavigableMap<Double,Integer> weightFees =  new TreeMap<>();

    public HshinChuShippingFeeCalculator() {
        weightFees.put(2.0d, 10); // 含 2 公斤以下，運費 10 元。
        weightFees.put(4.0d, 20);
        weightFees.put(Double.MAX_VALUE, 50);
    }

    @Override
    public int calculate(Box box) {
        return weightFees.ceilingEntry(box.weight()).getValue();
    }

    @Override
    public String getName() {
        return "Hshin Chu";
    }
}
