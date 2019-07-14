package MusicLendClient;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

class User implements Observable {
    private Boolean _isAdmin;
    private Collection<Instrument> instrumentsInUse;
    private Collection<Instrument> instrumentsInCart;
    private Collection<InvalidationListener> listeners = new HashSet<>();

    User() {
        this(Boolean.FALSE, new LinkedList<>(), new LinkedList<>());
    }

    User(Boolean isAdmin) {
        this(isAdmin, new LinkedList<>(), new LinkedList<>());
    }

    User(Boolean isAdmin, Collection<Instrument> instrumentsInUse, Collection<Instrument> instrumentsInCart) {
        this._isAdmin = isAdmin;
        this.instrumentsInUse = instrumentsInUse;
        this.instrumentsInCart = instrumentsInCart;
    }

    Boolean isAdmin() {
        return _isAdmin;
    }

    Collection<Instrument> getInstrumentsInUse() {
        return instrumentsInUse;
    }

    Collection<Instrument> getInstrumentsInCart() {
        return instrumentsInCart;
    }

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
