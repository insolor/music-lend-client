package MusicLendClient;

import java.util.Collection;
import java.util.LinkedList;

class User {
    private Boolean _isAdmin;
    private Collection<Instrument> instrumentsInUse;
    private Collection<Instrument> instrumentsInCart;

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
}
