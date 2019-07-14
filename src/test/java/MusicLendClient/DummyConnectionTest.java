package MusicLendClient;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class DummyConnectionTest {
    private DummyConnection connection;

    @Before
    public void setUp() throws Exception {
        connection = new DummyConnection("admin", "");
    }

    @Test
    public void calculateCart() {
        // 2 instruments - no discount
        Collection<Instrument> instruments = new LinkedList<>(Arrays.asList(
                new Instrument(1,"Гитара 1", "Семиструнная", BigDecimal.valueOf(100)),
                new Instrument(2,"Гитара 2", "Шестиструнная", BigDecimal.valueOf(200))
        ));

        Cart cart = new Cart(instruments, "", 2);
        CartCalculationResult result = connection.calculateCart(cart);
        assertEquals(result.getSumToBePaid().intValue(), (100+200)*2);

        // 5% discount for 3 or more instruments
        instruments.add(new Instrument(3,"Гитара 3", "Шестиструнная", BigDecimal.valueOf(400)));
        result = connection.calculateCart(cart);
        assertEquals(result.getSumToBePaid().intValue(),  (700 - 700*5/100)*2);

        // 15% discount on promocode
        cart = new Cart(instruments, "PROMOCODE", 2);
        result = connection.calculateCart(cart);
        assertEquals(result.getSumToBePaid().intValue(),  (700 - 700*15/100)*2);
    }
}