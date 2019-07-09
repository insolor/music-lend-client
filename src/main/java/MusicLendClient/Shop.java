package MusicLendClient;

import java.util.Collection;
import java.util.LinkedList;

public class Shop {
    private Collection<Instrument> availableInstruments;

    Shop() {
        availableInstruments = new LinkedList<>();
    }

    public Collection<Instrument> getAvailableInstruments() {
        return availableInstruments;
    }
}
