package entity;

public class OrderDetails {

    private int orderId;
    private String sku;
    private int quantity;
    private float discount;
    private int price;

    // Default constructor
    public OrderDetails() {
    }

    // Parameterized constructor
    public OrderDetails(int orderId, String sku, int quantity, float discount, int price) {
        this.orderId = orderId;
        this.sku = sku;
        this.quantity = quantity;
        this.discount = discount;
        this.price = price;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // toString method for easy printing/debugging
    @Override
    public String toString() {
        return "OrderDetails{"
                + "orderId=" + orderId
                + ", sku='" + sku + '\''
                + ", quantity=" + quantity
                + ", discount=" + discount
                + ", price=" + price
                + '}';
    }
}
