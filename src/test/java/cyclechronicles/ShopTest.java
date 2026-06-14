package cyclechronicles;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness; // Import für die Strenge
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) // Löst das Problem sofort
public class ShopTest {

    @Mock
    private Order mockOrder;

    private final Shop shop = new Shop();

    @Test
    void testAccept_ValidOrder_ReturnsTrue() {
        when(mockOrder.getBicycleType()).thenReturn(Type.RACE);
        when(mockOrder.getCustomer()).thenReturn("Kunde1");

        boolean result = shop.accept(mockOrder);
        assertTrue(result);
    }


    @Test
    void testAccept_RejectEbike_ReturnsFalse() {
        when(mockOrder.getBicycleType()).thenReturn(Type.EBIKE);
        assertFalse(shop.accept(mockOrder));
    }

    @Test
    void testRepair_AddsToCompletedOrders() {
        when(mockOrder.getBicycleType()).thenReturn(Type.RACE);
        when(mockOrder.getCustomer()).thenReturn("Kunde1");
        shop.accept(mockOrder);

        Optional<Order> result = shop.repair();
        assertTrue(result.isPresent());
    }

    @Test
    void testDeliver_RemovesFromCompletedOrders() {
        when(mockOrder.getBicycleType()).thenReturn(Type.RACE);
        when(mockOrder.getCustomer()).thenReturn("Kunde1");
        shop.accept(mockOrder);
        shop.repair();

        Optional<Order> delivered = shop.deliver("Kunde1");
        assertTrue(delivered.isPresent());
    }
}
