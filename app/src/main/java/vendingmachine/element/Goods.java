package vendingmachine.element;

public class Goods {
    private int price;
    private int quantity;

    public Goods(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }
}
