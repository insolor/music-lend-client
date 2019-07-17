package MusicLendClient;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

class User implements Observable {
    private Boolean _isAdmin;
    private Collection<Instrument> instrumentsInUse;
    private Collection<InvalidationListener> listeners = new HashSet<>();
    private Cart cart;

    User() {
        this(Boolean.FALSE, new LinkedList<>(), new Cart());
    }

    User(Boolean isAdmin) {
        this(isAdmin, new LinkedList<>(), new Cart());
    }

    User(Boolean isAdmin, Collection<Instrument> instrumentsInUse, Cart cart) {
        this._isAdmin = isAdmin;
        this.instrumentsInUse = instrumentsInUse;
        this.cart = cart;
    }

    Boolean isAdmin() {
        return _isAdmin;
    }

    Collection<Instrument> getInstrumentsInUse() {
        return instrumentsInUse;
    }

    Cart getCart() {
        return cart;
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
