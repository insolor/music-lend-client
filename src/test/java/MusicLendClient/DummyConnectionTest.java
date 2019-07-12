package MusicLendClient;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class DummyConnectionTest {
    private DummyConnection connection;

    @Before
    public void setUp() throws Exception {
        connection = new DummyConnection("", "admin", "");
    }

    @Test
    public void calculateCart() {
        Collection<Instrument> instruments = new LinkedList<>();
        instruments.add(new Instrument(1,"Гитара 1", "Семиструнная", BigDecimal.valueOf(100)));
        instruments.add(new Instrument(2,"Гитара 2", "Шестиструнная", BigDecimal.valueOf(200)));
        Cart cart = new Cart(instruments, null, 1);
        CartCalculationResult result = connection.calculateCart(cart);
        assertEquals(result.getSumToBePaid(), BigDecimal.valueOf(300));
    }
}