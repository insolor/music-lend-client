package MusicLendClient;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

class Shop implements Observable {
    private Collection<Instrument> availableInstruments;

    Shop() {
        availableInstruments = new LinkedList<>();
    }

    Collection<Instrument> getAvailableInstruments() {
        return availableInstruments;
    }

    private Collection<InvalidationListener> listeners = new HashSet<>();

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    void invalidate() {
        for (InvalidationListener listener: listeners) {
            listener.invalidated(this);
        }
    }
}
