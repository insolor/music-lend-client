package MusicLendClient;

import java.util.Collection;
import java.util.LinkedList;

public class User {
    private Boolean _isAdmin;
    private Collection instrumentsInUse;
    private Collection instrumentsInCart;

    User() {
        _isAdmin = Boolean.FALSE;
        instrumentsInUse = new LinkedList();
    }

    User(Boolean isAdmin) {
        this._isAdmin = isAdmin;
        instrumentsInUse = new LinkedList();
    }

    public Boolean isAdmin() { return _isAdmin; }

    public Collection getInstrumentsInUse() {
        return instrumentsInUse;
    }

    public Collection getInstrumentsInCart() { return instrumentsInCart; }
}

class DummyUser extends User {
    private int passwordHash;

    DummyUser(String password, Boolean isAdmin) {
        super(isAdmin);
        this.passwordHash = password.hashCode();
    }

    public Boolean checkPassword(String password) { return password.hashCode() == passwordHash; }
}