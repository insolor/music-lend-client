package MusicLendClient;

import java.util.Collection;
import java.util.LinkedList;

class Shop {
    private Collection<Instrument> availableInstruments;

    Shop() {
        availableInstruments = new LinkedList<>();
    }

    Collection<Instrument> getAvailableInstruments() {
        return availableInstruments;
    }
}
