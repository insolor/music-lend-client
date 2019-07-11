package MusicLendClient;

import java.util.Collection;

public class Cart {
    Collection<Instrument> instruments;
    String promocode;
    Integer days;

    Cart(Collection<Instrument> instruments, String promocode, Integer days) {
        this.instruments = instruments;
        this.promocode = promocode;
        this.days = days;
    }
}
