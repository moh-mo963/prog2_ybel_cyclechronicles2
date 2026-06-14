package cyclechronicles;

import java.util.*;

public class Shop {
    private final Queue<Order> pendingOrders = new LinkedList<>();
    private final Set<Order> completedOrders = new HashSet<>();

    public boolean accept(Order o) {
        if (o.getBicycleType() == Type.GRAVEL || o.getBicycleType() == Type.EBIKE) return false;
        if (pendingOrders.stream().anyMatch(x -> x.getCustomer().equals(o.getCustomer()))) return false;
        if (pendingOrders.size() >= 5) return false;

        return pendingOrders.add(o);
    }

    public Optional<Order> repair() {
        if (pendingOrders.isEmpty()) return Optional.empty();
        Order o = pendingOrders.poll();
        completedOrders.add(o);
        return Optional.of(o);
    }

    public Optional<Order> deliver(String c) {
        Optional<Order> orderToDeliver = completedOrders.stream()
            .filter(o -> o.getCustomer().equals(c))
            .findFirst();
        orderToDeliver.ifPresent(completedOrders::remove);
        return orderToDeliver;
    }
}
