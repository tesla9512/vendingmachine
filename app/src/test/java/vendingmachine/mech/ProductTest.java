package vendingmachine.mech;

import org.junit.jupiter.api.Test;

import vendingmachine.element.Goods;

public class ProductTest {
    @Test
    void setProductTest() {
        Goods product1 = new Goods(2000, 20);

        System.out.println("price = " + product1.getPrice() + " qty = " + product1.getQuantity());

        product1.decreaseQuantity();

        System.out.println("1 qty decrease after qty = " + product1.getQuantity());
    }
}
