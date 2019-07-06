package MusicLendClient;

import java.util.Collection;
import java.util.LinkedList;

public class User {
    private Boolean _isAdmin;
    private Collection instrumentsInUse;

    User() {
        _isAdmin = Boolean.FALSE;
        instrumentsInUse = new LinkedList();
    }

    public Boolean isAdmin() {
        return _isAdmin;
    }

    public Collection getInstrumentsInUse() {
        return instrumentsInUse;
    }
}
