package MusicLendClient;

import java.util.Collection;
import java.util.LinkedList;

public class User {
    private Boolean _isAdmin;
    private Collection<Instrument> instrumentsInUse;
    private Collection<Instrument> instrumentsInCart;

    User() {
        _isAdmin = Boolean.FALSE;
        instrumentsInUse = new LinkedList<>();
    }

    User(Boolean isAdmin) {
        this._isAdmin = isAdmin;
        instrumentsInUse = new LinkedList<>();
    }

    public Boolean isAdmin() { return _isAdmin; }

    public Collection<Instrument> getInstrumentsInUse() {
        return instrumentsInUse;
    }

    public Collection<Instrument> getInstrumentsInCart() { return instrumentsInCart; }
}
