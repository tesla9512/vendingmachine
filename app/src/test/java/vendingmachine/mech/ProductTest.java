package vendingmachine.mech;

import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    void setProductTest() {
        Product product1 = new Product(2000, 20);

        System.out.println("price = " + product1.getPrice() + " qty = " + product1.getQuantity());

        product1.decreaseQuantity();

        System.out.println("1 qty decrease after qty = " + product1.getQuantity());
    }
}
