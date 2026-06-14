package cyclechronicles;

public class Order {
    private final Type type;
    private final String customer;

    public Order(Type type, String customer) {
        this.type = type;
        this.customer = customer;
    }

    public Type getBicycleType() {
        return type;
    }

    public String getCustomer() {
        return customer;
    }
}
