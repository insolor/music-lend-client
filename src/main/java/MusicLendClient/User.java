package MusicLendClient;

import java.util.Collection;
import java.util.LinkedList;

public class User {
    private int passwordHash;
    private Boolean _isAdmin;
    private Collection instrumentsInUse;
    private Collection instrumentsInCart;

    User() {
        passwordHash = 0;
        _isAdmin = Boolean.FALSE;
        instrumentsInUse = new LinkedList();
    }

    User(String password, Boolean isAdmin) {
        this.passwordHash = password.hashCode();
        this._isAdmin = isAdmin;
        instrumentsInUse = new LinkedList();
    }

    public Boolean isAdmin() { return _isAdmin; }

    public Boolean checkPassword(String password) { return password.hashCode() == passwordHash; }

    public Collection getInstrumentsInUse() {
        return instrumentsInUse;
    }

    public Collection getInstrumentsInCart() { return instrumentsInCart; }
}
