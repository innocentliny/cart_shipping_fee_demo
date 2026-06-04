package org.example.demo;

import org.example.demo.domain.BlackCatShippingFeeCalculator;
import org.example.demo.domain.Box;
import org.example.demo.domain.HshinChuShippingFeeCalculator;
import org.example.demo.domain.ShippingNotFoundException;
import org.example.demo.service.ShippingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShippingFeeTest {

    private ShippingService shippingService =  new ShippingService(List.of(new BlackCatShippingFeeCalculator(), new HshinChuShippingFeeCalculator()));
    private int fee;
    private Box box;

    @BeforeEach
    void setUp() {
        fee = 0;
        box = null;
    }

    @Test
    void test_black_cat_volume_equal_1000_and_fee_30() throws ShippingNotFoundException {
        given_box(10, 10, 10, 10);

        when_calculate_fee(box, "Black Cat");

        then_fee_is(30);
    }

    @Test
    void test_black_cat_volume_lt_2000_and_fee_50() throws ShippingNotFoundException {
        given_box(10, 10, 11, 10);

        when_calculate_fee(box, "Black Cat");

        then_fee_is(50);
    }

    private void then_fee_is(int expected) {
        Assertions.assertEquals(expected, fee);
    }

    private void when_calculate_fee(Box box, String shippingName) throws ShippingNotFoundException {
        fee = shippingService.getFee(shippingName, box);
    }

    @Test
    void test_black_cat_volume_lt_1000_and_fee_30() throws ShippingNotFoundException {
        given_box(9, 10, 10, 10);

        when_calculate_fee(box, "Black Cat");

        then_fee_is(30);
    }

    private void given_box(int length, int width, int height, int weight) {
        box = new Box(length, width, height, weight);
    }

    @Test
    void test_shipping_name_not_found() throws ShippingNotFoundException {
        given_box(10, 10, 10, 10);

        when_calculate_fee_then_shipping_not_found();
    }

    private void when_calculate_fee_then_shipping_not_found() {
        Assertions.assertThrows(ShippingNotFoundException.class, () -> when_calculate_fee(box, "unknown"));
    }
}
