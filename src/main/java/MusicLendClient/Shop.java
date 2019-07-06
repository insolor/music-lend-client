package MusicLendClient;

import java.util.Collection;
import java.util.LinkedList;

public class Shop {
    private Collection availableInstruments;

    Shop() {
        availableInstruments = new LinkedList<String>();
        availableInstruments.add("Test");
    }

    public Collection getAvailableInstruments() {
        return availableInstruments;
    }
}
